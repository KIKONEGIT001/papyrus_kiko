package com.sci.papyrus.service;

import java.awt.print.Book;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.sci.papyrus.common.DuplicateResourceException;
import com.sci.papyrus.dto.BookDTO;
import com.sci.papyrus.dto.BorrowingDTO;
import com.sci.papyrus.dto.BorrowingDTO.BorrowingDTOBuilder;
import com.sci.papyrus.dto.Borrowingform;
import com.sci.papyrus.entity.Borrowing;
import com.sci.papyrus.repository.BookRepository;
import com.sci.papyrus.repository.BorrowingRepository;

public class BorrowService {

    private final BorrowingRepository repository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowingRepository repository){
        this.repository = repository;
        this.bookRepository = null;
    }
    
    public BorrowingDTO create(Borrowingform form){
 // TODO: verifier que le livre existe 
        Optional<Book> optionalBook = bookRepository.findById(form.getBookId());
        if (optionalBook.isEmpty()){
            throw new IllegalAccessException();
        }
        if(repository.findByUserIdAndReturnedIsFalse(form.getUserId()).isPresent()){
            throw new DuplicateResourceException("User has some borrowing");
        }
        // verifier que l'utilisateur existe
        // verifier qu'il na pas d'emprunt en cours

        // convertir le form en entity
        Borrowing borrowing = Borrowing.builder()
                            .creationDate(Instant.now())
                            .returnDate(Instant.now().plus(7,ChronoUnit.DAYS))
                            .userId(form.getUserId())
                            .returned(false)
                            .book(optionalBook.get())
                            .build();
        repository.save(borrowing);
        // creer l'emprunt

        Borrowing saved = repository.save(borrowing);
        BookDTO bookDTO = BookDTO.builder()
                            .id(saved.getId())
                            .author(saved.getBook().getAuthor())
                            .isbn(saved.getBook().getIsbn())
                            .title(saved.getBook().getTitle())
                            .build();



        return BorrowingDTO.builder()
                .creationDate(saved.getCreationDate())
                .returnDate(saved.getReturnDate())
                .id(saved.getId())
                .userId(saved.getUserId())
                .book(saved.getBook())
                .build();
        
    }
        
    

}
