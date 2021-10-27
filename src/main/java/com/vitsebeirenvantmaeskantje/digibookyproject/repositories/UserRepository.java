package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<String, User> myUsers = new HashMap<>();
    // CODEREVIEW Why a Map<String, User>?
    // Other options: Set<User>, Map<UUID, User>

    public UserRepository() {
        myUsers.put("1", new User("123456789", "Rob","Vanoudenhoven","robvanoudenhoven@telenet.be",
                "Robland", null, null, 0, User.Role.ADMIN));
        myUsers.put("2", new User("898425142", "Bob","Vanjongenhoven","robvanjongenhoven@telenet.be",
                "Bobland", null, null, 0, User.Role.LIBRARIAN));
        myUsers.put("3", new User("874987563", "Job","Vanjongenhoven","Jobvanjongenhoven@telenet.be",
                "Jobland", null, null, 0, User.Role.MEMBER));
    }

    public User save(User user){
        // CODEREVIEW: Adding the same user multiple times will create new entries in the map
        // This is because a new ID is created for every `new User`
        myUsers.put(user.getId(), user);
        return user;
    }

    public List<User> getAll() {
        // CODEREVIEW Optional: Return immutable collection
        //return new ArrayList<>(myUsers.values());
        return List.copyOf(myUsers.values());
    }

    public User fetchUser(String id){
        return myUsers.get(id);
    }

    // CODEREVIEW: Improve method name, as it does throw an exception
    // CODEREVIEW: Ubiquitous language is important => UserId vs MemberId => be consistent
    public boolean assertUserIdExists(String memberId){
        return myUsers.containsKey(memberId);
    }

}
