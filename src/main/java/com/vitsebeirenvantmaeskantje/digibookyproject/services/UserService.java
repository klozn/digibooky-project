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

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public UserDto createNewMember(CreateMemberDto createMemberDto) {
        User member = new User(createMemberDto.getInss(), createMemberDto.getLastName(), createMemberDto.getMail(), createMemberDto.getCity());
        member.setFirstName(createMemberDto.getFirstName());
        member.setStreet(createMemberDto.getStreet());
        member.setStreetNumber(createMemberDto.getStreetNumber());
        member.setPostalCode(createMemberDto.getPostalCode());
        repository.save(member);
        return UserMapper.toDto(member);
    }
}
