package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
public class UserRepository {
    private final Map<String, User> myUsers = new HashMap<>();

    public UserRepository() {
        myUsers.put("1", new User("123456789", "Rob","Vanoudenhoven","robvanoudenhoven@telenet.be",
                "Robland", null, null, 0, User.Role.ADMIN));
        myUsers.put("2", new User("898425142", "Bob","Vanjongenhoven","robvanjongenhoven@telenet.be",
                "Bobland", null, null, 0, User.Role.LIBRARIAN));
        myUsers.put("3", new User("874987563", "Job","Vanjonenhoven","Jobvanjongenhoven@telenet.be",
                "Jobland", null, null, 0, User.Role.MEMBER));
    }

    public User save(User user){
        myUsers.put(user.getId(), user);
        return user;
    }

    public List<User> getAll() {
        return new ArrayList<>(myUsers.values());
    }

    public User fetchUser(String id){
        return myUsers.get(id);
    }

}
