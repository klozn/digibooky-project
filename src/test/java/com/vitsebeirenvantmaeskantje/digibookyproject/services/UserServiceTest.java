package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.CreateMemberDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService = new UserService(new UserRepository(), new UserMapper());

    @Test
    void createMember_returnsUserDtoWithCorrectData() {
        CreateMemberDto createMemberDto = new CreateMemberDto().setLastName("From the block").setInss("1324564877")
                .setMail("bobby.fromdablock@test.be").setCity("Harlem");
        UserDto created = userService.createNewMember(createMemberDto);

        assertNotNull(created.getId());
        assertEquals("From the block", created.getLastName());
        assertEquals("1324564877", created.getInss());
        assertEquals("bobby.fromdablock@test.be", created.getMail());
        assertEquals("Harlem", created.getCity());
    }

}