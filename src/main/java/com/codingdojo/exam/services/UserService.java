package com.codingdojo.exam.services;

import com.codingdojo.exam.models.Idea;
import org.mindrot.jbcrypt.BCrypt;
import com.codingdojo.exam.models.LoginUser;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository ;

    public User findUser(Long id){
        return repository.findById(id).orElse(null);
    }

    public User register(User newUser, BindingResult result){
        Optional<User> potentialUser = this.repository.findByEmail(newUser.getEmail());
        if (potentialUser.isPresent())
            result.rejectValue("email", "EmailTaken", "Email address is already in use.");
        else if (!newUser.getPassword().equals(newUser.getConfirm()))
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");

        if (result.hasErrors())
            return null;

        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return repository.save(newUser);
    }

    public User login(LoginUser newLogin,BindingResult result ){
        Optional<User> potentialUser = this.repository.findByEmail(newLogin.getEmail());
        if (!potentialUser.isPresent())
            result.rejectValue("email", "EmailNotFound", "No user found with that Email address.");
        else  if (!BCrypt.checkpw(newLogin.getPassword(), potentialUser.get().getPassword()))
            result.rejectValue("password", "Matches", "Invalid Password!");

        if (result.hasErrors())
            return null;
        return potentialUser.get();
    }

    public List<User> getAllByIdea(Idea idea){
        return repository.findAllByLikedIdeas(idea);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
