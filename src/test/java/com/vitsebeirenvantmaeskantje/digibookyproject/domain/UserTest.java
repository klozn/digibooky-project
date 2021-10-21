package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void whenUserMade_doesNotThrowIfMailIsValid() {
        assertDoesNotThrow(() -> new User("123564789", "From the block", "bobby.fromdablock@gmail.com", "Harlem"));
    }

    @Test
    void whenUserMade_throwsExceptionIfMailIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new User("123564789", "From the block", "bobby.fromdablockgmail.com", "Harlem"));
        assertThrows(IllegalArgumentException.class,
                () -> new User("123564789", "From the block", "bobby.fromdablock@gmailcom", "Harlem"));
    }


}