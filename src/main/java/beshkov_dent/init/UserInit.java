package beshkov_dent.init;


import beshkov_dent.model.UserEntity;
import beshkov_dent.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {


    private final UserRepo userRepo;
    private final PasswordEncoder encoder;


    public UserInit(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;

        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userRepo.count() == 0) {
            UserEntity userEntityToAdd = new UserEntity();

            String userName = "sirakova";
            String password = "rumburak";
            String userRole = "ADMIN";

            userEntityToAdd.setUserName(userName);
            userEntityToAdd.setPassword(encoder.encode(password));
            userEntityToAdd.setUserRole(userRole);

            this.userRepo.save(userEntityToAdd);
        }
    }
}
