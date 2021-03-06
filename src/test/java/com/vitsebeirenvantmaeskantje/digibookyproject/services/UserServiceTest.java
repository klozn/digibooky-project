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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    void ifUserIsNotAdmin_WhenGettingMembers_ThrowException() {
        UserDto member = userService.createNewMember(new CreateMemberDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0));

        assertThrows(UnauthorizedUserException.class, () -> userService.getAllMembers(member.getId()));
    }

    @Test
    void ifUserIsAdmin_getMembers_returnsAllMembers() {
        UserDto member = userService.createNewMember(new CreateMemberDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0));

        List<UserDto> members = userService.getAllMembers(ADMIN_ID);
        assertThat(members).contains(member);
    }

    @Nested
    @DisplayName("create admin tests")
    class RegisterAdmin {
        @Test
        @DisplayName("Member can't register admin")
        void ifUserIsMember_whenCreateAdmin_thenThrowException() {
            UserDto member = userService.createNewMember(new CreateMemberDto("1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0));
            assertThrows(UnauthorizedUserException.class, () ->
                    userService.createNewAdmin(new CreateAdminDto(member.getId(), "874654", null, "From the block",
                            "bobby.fromdablock@test.be", "Harlem", null, null, 0)));
        }

        @Test
        @DisplayName("Librarian can't register admin")
        void ifUserIsLibrarian_whenCreateAdmin_thenThrowException() {
            UserDto librarian = userService.createNewLibrarian(new CreateLibrarianDto("1", "1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0));
            assertThrows(UnauthorizedUserException.class, () ->
                    userService.createNewAdmin(new CreateAdminDto(librarian.getId(), "874654", null, "From the block",
                            "bobby.fromdablock@test.be", "Harlem", null, null, 0)));
        }

        @Test
        @DisplayName("Admin can register admin")
        void ifUserIsAdmin_whenCreateAdmin_returnsNewAdmin() {
            UserDto admin = userService.createNewAdmin(new CreateAdminDto(ADMIN_ID, "1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0));

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
                    userService.createNewLibrarian(new CreateLibrarianDto(member.getId(), "874654", null, "From the block",
                            "bobby.fromdablock@test.be", "Harlem", null, null, 0)));
        }

        @DisplayName("Librarian can not register a Librarian")
        @Test
        void ifUserIsLibrarian_whenCreateLibrarian_thenThrowException() {
            UserDto librarian = userService.createNewLibrarian(new CreateLibrarianDto(ADMIN_ID, "1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0));
            assertThrows(UnauthorizedUserException.class, () ->
                    userService.createNewLibrarian(new CreateLibrarianDto(librarian.getId(), "874654", null, "From the block",
                            "bobby.fromdablock@test.be", "Harlem", null, null, 0)));
        }

        @DisplayName("Admin can register a Librarian")
        @Test
        void ifUserIsAdmin_whenCreateLibrarian_returnsNewAdmin() {
            UserDto librarian = userService.createNewLibrarian(new CreateLibrarianDto(ADMIN_ID, "1324564877", null, "From the block",
                    "bobby.fromdablock@test.be", "Harlem", null, null, 0));

            assertSame(librarian.getRole(), User.Role.LIBRARIAN);
        }
    }
}


