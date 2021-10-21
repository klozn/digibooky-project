package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateMemberDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
