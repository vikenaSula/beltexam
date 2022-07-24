package com.codingdojo.exam.services;

import com.codingdojo.exam.models.Idea;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.repositories.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {

    @Autowired
    private IdeaRepository repository;
    public Idea findIdea(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Idea> getAll(){
        return repository.findAll();
    }


    public Idea create(Idea idea) {
        return repository.save(idea);
    }

    public Idea update(Idea newIdea, Idea oldIdea) {
        //When updating an idea, save the users that liked
        List<User> userList =oldIdea.getUsers();
        newIdea.setUsers(userList);
        newIdea.setUserslikedCount(newIdea.getUsers().size());
        return repository.save(newIdea);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Idea> getAllAscendingOrder(){
        return repository.findAllByOrderByUserslikedCountAsc();
    }
    public List<Idea> getAllDescendingOrder(){
        return repository.findAllByOrderByUserslikedCountDesc();
    }

    //Method for adding a like
    public void addLike(Idea idea, User user){
        idea.getUsers().add(user);
        idea.setUserslikedCount(idea.getUsers().size());
        repository.save(idea);
    }

    //Method for removing a like
    public void removeLike(Idea idea, User user){
        idea.getUsers().remove(user);
        idea.setUserslikedCount(idea.getUsers().size());
        repository.save(idea);
    }

}
