package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void whenUserMade_doesNotThrowIfMailIsValid() {
        assertDoesNotThrow(() -> new User("123564789",null, "From the block",
                "bobby.fromdablock@gmail.com", "Harlem",null,null, 0));
    }

    @Test
    void whenUserMade_throwsExceptionIfMailIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new User("123564789",null, "From the block", "bobby.fromdablockgmail.com",
                        "Harlem",null,null, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new User("123564789",null, "From the block", "bobby.fromdablock@gmailcom",
                        "Harlem",null,null, 0));
    }


}