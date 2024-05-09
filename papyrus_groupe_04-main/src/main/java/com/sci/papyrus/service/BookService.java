package com.sci.papyrus.service;

import com.sci.papyrus.common.DuplicateResourceException;
import com.sci.papyrus.dto.BookDTO;
import com.sci.papyrus.entity.Book;
import com.sci.papyrus.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookDTO create(BookDTO form) {

        if (repository.existsByIsbn(form.getIsbn())) {
            throw new DuplicateResourceException("Book with isbn " + form.getIsbn() + " already exists");
        }
        Book book = Book.builder()
                .isbn(form.getIsbn())
                .title(form.getTitle())
                .author(form.getAuthor())
                .build();

        Book saved = repository.save(book);
        return BookDTO
                .builder()
                .id(saved.getId())
                .author(saved.getAuthor())
                .isbn(saved.getIsbn())
                .title(saved.getTitle())
                .build();
    }

    public List<BookDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(book ->
                        BookDTO
                                .builder()
                                .id(book.getId())
                                .author(book.getAuthor())
                                .isbn(book.getIsbn())
                                .title(book.getTitle())
                                .build()
                )
                .toList();
    }

    public  void deleteBook(Long id) {
        repository.deleteById(id);
    }


    public Book book_update(Long id, Book new_book) {
        Book book_existed = repository.findById(id)
                                             .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        // Mettre à jour les champs du livre existant avec les nouvelles valeurs
        book_existed.setTitle(new_book.getTitle());
        book_existed.setAuthor(new_book.getAuthor());
        // Mettre à jour d'autres champs si nécessaire...

        // Enregistrer les modifications dans la base de données
        return repository.save(book_existed);
    }

}
