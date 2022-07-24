package com.codingdojo.exam.repositories;

import com.codingdojo.exam.models.Idea;
import com.codingdojo.exam.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<User,Long> {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    List<User> findAllByLikedIdeas(Idea idea);

}

