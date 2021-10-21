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
        CreateMemberDto createMemberDto = new CreateMemberDto("1324564877", null, "From the block",
                "bobby.fromdablock@test.be", "Harlem", null, null, 0);
        UserDto created = userService.createNewMember(createMemberDto);

        assertNotNull(created.getId());
        assertEquals("From the block", created.getLastName());
        assertEquals("1324564877", created.getInss());
        assertEquals("bobby.fromdablock@test.be", created.getMail());
        assertEquals("Harlem", created.getCity());
    }

}