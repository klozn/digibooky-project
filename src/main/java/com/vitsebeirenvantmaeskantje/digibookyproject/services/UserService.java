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
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDto createNewMember(CreateMemberDto createMemberDto) {
        User member = new User(createMemberDto.getInss(), createMemberDto.getFirstName(), createMemberDto.getLastName(),
                createMemberDto.getMail(), createMemberDto.getCity(), createMemberDto.getStreet(),
                createMemberDto.getStreetNumber(), createMemberDto.getPostalCode());

        repository.save(member);
        return mapper.toDto(member);
    }

    public UserDto createNewAdmin(CreateAdminDto createAdminDto) {
        assertAdminId(createAdminDto.getAdminId());
        User admin = new User(createAdminDto.getInss(), createAdminDto.getFirstName(), createAdminDto.getLastName(),
                createAdminDto.getMail(), createAdminDto.getCity(), createAdminDto.getStreet(),
                createAdminDto.getStreetNumber(), createAdminDto.getPostalCode(), User.Role.ADMIN);
        repository.save(admin);
        return mapper.toDto(admin);
    }

    public UserDto createNewLibrarian(CreateLibrarianDto createLibrarianDto, String adminId) {
        assertAdminId(adminId);
        User librarian = new User(createLibrarianDto.getInss(), createLibrarianDto.getFirstName(), createLibrarianDto.getLastName(),
                createLibrarianDto.getMail(), createLibrarianDto.getCity(), createLibrarianDto.getStreet(),
                createLibrarianDto.getStreetNumber(), createLibrarianDto.getPostalCode(), User.Role.LIBRARIAN);
        repository.save(librarian);
        return mapper.toDto(librarian);
    }


    public List<UserDto> getAllMembers(String userId) {
        assertAdminId(userId);
        return repository.getAll().stream()
                .filter(u -> u.getRole() == User.Role.MEMBER)
                .map(mapper::toDto)
                .sorted(Comparator.comparing(UserDto::getLastName).thenComparing(UserDto::getFirstName))
                .collect(Collectors.toList());
    }

    private void assertAdminId(String id) {
        User user = fetchUserIfExistElseThrowException(id);
        if (user.getRole() != User.Role.ADMIN) {
            throw new UnauthorizedUserException("No admin rights!");
        }
    }

    protected void assertLibrarianId(String id) {
        User user = fetchUserIfExistElseThrowException(id);
        if (user.getRole() != User.Role.LIBRARIAN) {
            throw new UnauthorizedUserException("No librarian rights!");
        }
    }

    public boolean assertUserIdExistsABoolean(String memberId)
    {
        return repository.assertUserIdExists(memberId);
    }

    protected User fetchUserIfExistElseThrowException(String id) {
        User user = repository.fetchUser(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
        return user;
    }
}
