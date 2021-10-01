//PART 3
//Add the corresponding property to the Author Entity class, which will be a List<Post> called posts with
// the @OneToMany annotation. We also need to do a few additional things in the Author class for the Post relationship:
//
//To the default constructor, after  super();, initialize posts to a new empty ArrayList.
//Also edit the getter, public List<Post> getPosts(), to return posts instead of null.
//And in lieu of a setter, edit  public void addPost(Post post) to add the passed-in post parameter to the posts list
// instead of the return; statement.



package com.pluralsight.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Author {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @JsonIgnore //Hides password and username
    private String username;
    @JsonIgnore
    private String password;

    @OneToMany
    List<Post> posts;

    public Author() {

        super();

        posts = new ArrayList<>();
    }

    public Author(String username, String firstname, String lastname, String password) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        setPassword(password);
    }

    public void setPassword(String password) {
         this.password = PASSWORD_ENCODER.encode(password);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        Author inputAuthor = (Author)obj;
        if (!this.firstname.equals(inputAuthor.getFirstName())) {
            System.out.println("firstname not equal");
            return false;}
        if (!this.lastname.equals(inputAuthor.getLastname())) {
            System.out.println("lastname not equal");
            return false;}
        if (!this.username.equals(inputAuthor.getUsername())) {
            System.out.println("username not equal");
            return false;}
        return true;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
