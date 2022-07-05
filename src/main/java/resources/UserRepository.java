package resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public class UserRepository {

    public List<User> userRepo;

    public static UserRepository createUserRepository() {
        List<User> users = initilizeRepo();
        return new UserRepository(users);
    }

    public static List<User> initilizeRepo() {
        List<User> userRepo = new ArrayList<>();
        for (int i = 0; i < 300000; i++) {
            String name = "User " + i;
            String loginName = "loginuser" + i;
            boolean licensed = i % 2 == 0;
            boolean isOnline = i % 2 == 0;
            User newUser = new User(name, loginName, licensed, isOnline);
//            log.info("New User created: {}", newUser);
            userRepo.add(newUser);
        }
        return userRepo;
    }

}
