package com.pluralsight.blog.data;


import com.pluralsight.blog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AuthorRepository extends JpaRepository<Author, Long> {


}

//We previously created an Author Entity so that we can show author information and be able to list posts by author.
// Now we need an AuthorRepository interface that extends the JpaRepository interface.
// To do this, in AuthorRepository.java, delete everything except the package.
// Replace it with public interface AuthorRepository extends JpaRepository<Author, Long> {},
// and make sure you also import org.springframework.data.jpa.repository.JpaRepository
// and com.pluralsight.blog.model.Author.