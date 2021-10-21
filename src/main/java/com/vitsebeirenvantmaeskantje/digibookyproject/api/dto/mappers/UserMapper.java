package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;

public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getInss(), user.getFirstName(), user.getLastName(), user.getMail(),
                user.getCity(), user.getStreet(), user.getStreetNumber(), user.getPostalCode(), user.getRole());
    }


}
