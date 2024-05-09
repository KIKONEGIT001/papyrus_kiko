package com.sci.papyrus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sci.papyrus.entity.Borrowing;



public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    
    Optional<Borrowing> findByUserIdAndReturnedIsFalse(String userId);

}
