package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateAdminDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateLibrarianDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateMemberDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UnauthorizedUserException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("User test")
class UserServiceTest {
    private final static String ADMIN_ID = "1";
    UserService userService = new UserService(new UserRepository(), new UserMapper());

    @Test
    void createMember_returnsUserDtoWithCorrectData() {
        CreateMemberDto createMemberDto = new CreateMemberDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0);
        UserDto created = userService.createNewMember(createMemberDto);

        assertNotNull(created.getId());
        assertEquals("From the block", created.getLastName());
        assertEquals("1324564877", created.getInss());
        assertEquals("bobby.fromdablock@test.be", created.getMail());
        assertEquals("Harlem", created.getCity());
    }

    @Test
    void ifUserIsNotAdmin_WhenGettingMembers_ThrowException(){
        UserDto member = userService.createNewMember(new CreateMemberDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0));

        assertThrows(UnauthorizedUserException.class, () -> userService.getAllMembers(member.getId()));
    }

    @Test
    void ifUserIsAdmin_getMembers_returnsAllMembers() {
        UserDto member = userService.createNewMember(new CreateMemberDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0));

        List<UserDto> members = userService.getAllMembers(ADMIN_ID);

        assertEquals(member.getId(), members.get(0).getId());
    }

    @Test
    void ifUserIsNotAdmin_whenCreateAdmin_thenThrowException() {
        UserDto member = userService.createNewMember(new CreateMemberDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0));
        assertThrows(UnauthorizedUserException.class, () ->
                userService.createNewAdmin(new CreateAdminDto("874654", null, "From the block",
                        "bobby.fromdablock@test.be", "Harlem", null, null, 0), member.getId()));
    }

    @Test
    void ifUserIsAdmin_whenCreateAdmin_returnsNewAdmin() {
        UserDto admin = userService.createNewAdmin(new CreateAdminDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0), ADMIN_ID);

            assertSame(admin.getRole(), User.Role.ADMIN);
        }
    }

    @Nested
    @DisplayName("Create Librarian test")
    class RegisterLibrarian {
        @DisplayName("Member can not register a Librarian")
        @Test
        void ifUserIsMember_whenCreateLibrarian_thenThrowException() {
            UserDto member = userService.createNewMember(new CreateMemberDto("1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0));
            assertThrows(UnauthorizedUserException.class, () ->
                    userService.createNewLibrarian(new CreateLibrarianDto("874654", null, "From the block",
                            "bobby.fromdablock@test.be", "Harlem", null, null, 0), member.getId()));
        }

        @DisplayName("Librarian can not register a Librarian")
        @Test
        void ifUserIsLibrarian_whenCreateLibrarian_thenThrowException() {
            UserDto librarian = userService.createNewLibrarian(new CreateLibrarianDto("1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0), ADMIN_ID);
            assertThrows(UnauthorizedUserException.class, () ->
                    userService.createNewLibrarian(new CreateLibrarianDto("874654", null, "From the block",
                            "bobby.fromdablock@test.be", "Harlem", null, null, 0), librarian.getId()));
        }

        @DisplayName("Admin can register a Librarian")
        @Test
        void ifUserIsAdmin_whenCreateLibrarian_returnsNewAdmin() {
            UserDto librarian = userService.createNewLibrarian(new CreateLibrarianDto("1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0), ADMIN_ID);

            assertSame(librarian.getRole(), User.Role.LIBRARIAN);
        }
    }
}