package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, User> myUsers = new HashMap<>();

    public UserRepository() {
        myUsers.put("1", new User("123456789", "Rob","Vanoudenhoven","robvanoudenhoven@telenet.be",
                "Robland", null, null, 0, User.Role.ADMIN));
    }

    public User save(User user){
        myUsers.put(user.getId(), user);
        return user;
    }

}
