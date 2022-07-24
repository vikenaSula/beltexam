package com.codingdojo.exam.repositories;

import com.codingdojo.exam.models.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IdeaRepository  extends CrudRepository<Idea,Long> {
    List<Idea> findAll();

    List<Idea> findAllByOrderByUserslikedCountAsc();

    List<Idea> findAllByOrderByUserslikedCountDesc();

}