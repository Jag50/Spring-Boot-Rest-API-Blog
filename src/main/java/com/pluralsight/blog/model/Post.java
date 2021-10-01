package com.pluralsight.blog.model;

//PART 2
//Since we want to connect Author to Post, we need to add a relationship to Author inside the Post Entity.
// Add a private Author property called author to the Post Entity class and use the @ManyToOne
// annotation with (fetch = FetchType.EAGER).
// We also need to do a few additional things in the Post class for the Author relationship:
//
//Add a getter, public Author getAuthor(), that returns author.
//Add a setter, public void setAuthor(Author author), that sets this.author to the passed in parameter.

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version//Adding version to the title class
    private Long version;
    @NotNull //Adding @NotNull to the title class property
    @Size(min=4, max=100)
    private String title;
    @Column(length=1000000)
    @Lob
    private String body;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    public Author getAuthor(){
        return author;
    }

    public void setAuthor(Author author){

        this.author = author;
    }


    public Post() {
        super();
    }

    public Post(String title, String body){//, Author author) {
        this();
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
        return outputFormatter.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Post))
            return false;
        Post otherPost = (Post)obj;
        return this.title.equals(otherPost.getTitle()) &&
               this.body.equals(otherPost.getBody());
    }
}
