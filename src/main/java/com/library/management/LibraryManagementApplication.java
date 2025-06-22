package com.library.management;

import com.library.management.entity.Book;
import com.library.management.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    // This adds test books at startup
    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("Harry and Potter", "vansh", "456",true)); 
            bookRepository.save(new Book("Spring Boot Guide", "arman", "756",false));
            bookRepository.save(new Book("Pride and Prejudice", "shivansh", "321",true));
        };
    }
}
