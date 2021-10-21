package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateAdminDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateMemberDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UnauthorizedUserException;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UserNotFoundException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserDto createNewAdmin(CreateAdminDto createAdminDto, String adminId){
        User current = fetchUserIfExistElseThrowException(adminId);
        assertAdmin(current);
        User admin = new User(createAdminDto.getInss(), createAdminDto.getFirstName(), createAdminDto.getLastName(),
                createAdminDto.getMail(), createAdminDto.getCity(), createAdminDto.getStreet(),
                createAdminDto.getStreetNumber(), createAdminDto.getPostalCode(), createAdminDto.getRole());
        repository.save(admin);
        return mapper.toDto(admin);
    }

    public List<UserDto> getAllMembers(String userId) {
        User current = fetchUserIfExistElseThrowException(userId);
        assertAdmin(current);
        return repository.getAll().stream()
                .filter(u -> u.getRole() == User.Role.MEMBER)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    private void assertAdmin(User user) {
        if (user.getRole() != User.Role.ADMIN) {
            throw new UnauthorizedUserException("No admin rights!");
        }
    }

    private User fetchUserIfExistElseThrowException(String id){
        User user = repository.fetchUser(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
        return user;
    }
}
