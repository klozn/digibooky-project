package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateAdminDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateLibrarianDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateMemberDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UnauthorizedUserException;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UserNotFoundException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    // CODEREVIEW your code allows people to be admins, librarians and members
    // however, if I am all three, and I change address, i will have to change it three times

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDto createNewMember(CreateMemberDto createMemberDto) {
        // CODEREVIEW Why not use mapper?
        User member = new User(createMemberDto.getInss(), createMemberDto.getFirstName(), createMemberDto.getLastName(),
                createMemberDto.getMail(), createMemberDto.getCity(), createMemberDto.getStreet(),
                createMemberDto.getStreetNumber(), createMemberDto.getPostalCode());

        repository.save(member);
        return mapper.toDto(member);
    }

    public UserDto createNewAdmin(CreateAdminDto createAdminDto) {
        assertAdminId(createAdminDto.getAdminId());
        // CODEREVIEW Why not use mapper?
        User admin = new User(createAdminDto.getInss(), createAdminDto.getFirstName(), createAdminDto.getLastName(),
                createAdminDto.getMail(), createAdminDto.getCity(), createAdminDto.getStreet(),
                createAdminDto.getStreetNumber(), createAdminDto.getPostalCode(), User.Role.ADMIN);
        repository.save(admin);
        return mapper.toDto(admin);
    }

    public UserDto createNewLibrarian(CreateLibrarianDto createLibrarianDto) {
        assertAdminId(createLibrarianDto.getAdminId());
        // CODEREVIEW Why not use mapper?
        User librarian = new User(createLibrarianDto.getInss(), createLibrarianDto.getFirstName(), createLibrarianDto.getLastName(),
                createLibrarianDto.getMail(), createLibrarianDto.getCity(), createLibrarianDto.getStreet(),
                createLibrarianDto.getStreetNumber(), createLibrarianDto.getPostalCode(), User.Role.LIBRARIAN);
        repository.save(librarian);
        return mapper.toDto(librarian);
    }


    public List<UserDto> getAllMembers(String userId) {
        assertAdminId(userId);
        return repository.getAll().stream()
                // CODEREVIEW improve variable name (`u`)
                .filter(u -> u.getRole() == User.Role.MEMBER)
                .map(mapper::toDto)
                .sorted(Comparator.comparing(UserDto::getLastName).thenComparing(UserDto::getFirstName))
                .collect(Collectors.toUnmodifiableList()); // or simply .toList(); since Java16
        //.collect(Collectors.toList());
        // CODEREVIEW Optional: Return UnmodifiableList
    }

    private void assertAdminId(String id) {
        // CODEREVIEW Improve methodname
        User user = fetchUserIfExistElseThrowException(id);
        if (user.getRole() != User.Role.ADMIN) {
            throw new UnauthorizedUserException("No admin rights!");
        }
    }

    protected void assertLibrarianId(String id) {
        // CODEREVIEW Improve methodname
        User user = fetchUserIfExistElseThrowException(id);
        if (user.getRole() != User.Role.LIBRARIAN) {
            throw new UnauthorizedUserException("No librarian rights!");
        }
    }

    public boolean assertUserIdExistsABoolean(String memberId) {
        // CODEREVIEW: Improve method name, as it does throw an exception
        // CODEREVIEW: Ubiquitous language is important => UserId vs MemberId => be consistent
        // CODEREVIEW: Improve method name, as it will not throw an exception
        // CODEREVIEW: Improve method name, remove *Boolean from name
        return repository.assertUserIdExists(memberId);
    }

    protected User fetchUserIfExistElseThrowException(String id) {
//        User user = repository.fetchUser(id);
//        if (user == null) {
//            throw new UserNotFoundException("User with id " + id + " not found.");
//        }
//        return user;
        // CODEREVIEW Optional; Rewrite using Optional :D
        return Optional
                .ofNullable(repository.fetchUser(id))
                .orElseThrow(UserNotFoundException::new);
    }
}
