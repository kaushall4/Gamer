package ch.bzz.gamerList.data;

import ch.bzz.gamerList.model.Gamer;
import ch.bzz.gamerList.model.Spiel;
import ch.bzz.gamerList.model.User;
import ch.bzz.gamerList.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * data handler for reading the users
 * <p>
 * M133: Bookshelf
 *
 * @author Marcel Suter
 */
public class UserData {
    private static final UserData instance = new UserData();

    /**
     * finds a user by username / password
     * @param username
     * @param password
     * @return User object / null=not found
     */
    public static User findUser(String username, String password) {
        User user = new User();
        List<User> userList = readJson();

        for (User entry: userList) {
            if (entry.getUsername().equals(username) &&
                    entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * reads the json file as a List
     * @return userList
     */
    private static List<User> readJson() {
        List<User> userList = new ArrayList<>();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("gamerUserJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                userList.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}