package beshkov_dent.repository;


import beshkov_dent.model.Message;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository <Message, Long> {

    @NotNull
    List<Message> findAll();

}
