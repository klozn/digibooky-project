package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.users.UserDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto().setId(user.getId())
                .setInss(user.getInss())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMail(user.getMail())
                .setStreet(user.getStreet())
                .setStreetNumber(user.getStreetNumber())
                .setPostalCode(user.getPostalCode())
                .setCity(user.getCity())
                .setRole(user.getRole());
    }


}
