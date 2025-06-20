package beshkov_dent.repository;


import beshkov_dent.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findByUserName(String username);
}
