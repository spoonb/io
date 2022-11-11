package io.bio.socket.demo07;

import java.util.ArrayList;
import java.util.List;

public class CharRoom {

    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }
}
