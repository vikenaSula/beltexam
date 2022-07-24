package com.codingdojo.exam.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="ideas")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Content is required!")
    @Size(min = 3, max = 100, message = "Content must be between 3 and 100 characters")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_liked_ideas",
            joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User>users;


    //Extra variable that will help with sorting based on likes
    private Integer userslikedCount;
    public Idea(){
    }

    public Idea(Long id, String content, User creator, List<User> users, Integer userslikedCount) {
        this.id = id;
        this.content = content;
        this.creator = creator;
        this.users = users;
        this.userslikedCount = userslikedCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getUserslikedCount(){
        return userslikedCount;
    }

    public void setUserslikedCount(Integer userslikedCount) {
        this.userslikedCount = userslikedCount;
    }
}


