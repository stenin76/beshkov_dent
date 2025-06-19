package beshkov_dent.service;


import beshkov_dent.model.UserEntity;
import beshkov_dent.repository.UserRepo;
import beshkov_dent.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepo userRepo;
    private final LoggedUser loggedUser;
    private final HttpSession session;

    public UserService(PasswordEncoder encoder, UserRepo userRepo, LoggedUser loggedUser, HttpSession session) {
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.loggedUser = loggedUser;
        this.session = session;
    }

    public boolean checkCredentials(String username, String password) {
        Optional<UserEntity> user = userRepo.findByUserName(username);
        return user.filter(userEntity -> encoder.matches(password, userEntity.getPassword())).isPresent();
    }

    public void login(String username) {
        UserEntity user = userRepo.findByUserName(username).orElse(null);

        if (user != null) {
            this.loggedUser.setId(user.getId());
            this.loggedUser.setUsername(user.getUserName());
        }
    }

    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }
}
