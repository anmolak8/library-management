package com.library.management.service;

import com.library.management.entity.Book;
import com.library.management.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    
    @Mock
    private BookRepository bookRepository;
    
    @InjectMocks
    private BookService bookService;
    
    private Book book1;
    private Book book2;
    
    @BeforeEach
    void setUp() {
        book1 = new Book("Harry and Potter", "vansh", "456", true);
        book2 = new Book("Spring Boot Guide", "arman", "756", false);
    }
    
    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);
        
        List<Book> result = bookService.getAllBooks();
        
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }
    
    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        
        Optional<Book> result = bookService.getBookById(1L);
        
        assertTrue(result.isPresent());
        assertEquals("Harry and Potter", result.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }
    
    @Test
    void testSaveBook() {
        when(bookRepository.save(book1)).thenReturn(book1);
        
        Book result = bookService.saveBook(book1);
        
        assertEquals("Harry and Potter", result.getTitle());
        verify(bookRepository, times(1)).save(book1);
    }
    
    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);
        
        bookService.deleteBook(1L);
        
        verify(bookRepository, times(1)).deleteById(1L);
    }
    
    @Test
    void testGetAvailableBooks() {
        List<Book> availableBooks = Arrays.asList(book1);
        when(bookRepository.findByAvailable(true)).thenReturn(availableBooks);
        
        List<Book> result = bookService.getAvailableBooks();
        
        assertEquals(1, result.size());
        assertTrue(result.get(0).isAvailable());
        verify(bookRepository, times(1)).findByAvailable(true);
    }
}
