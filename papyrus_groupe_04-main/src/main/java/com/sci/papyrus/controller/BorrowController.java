package com.sci.papyrus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sci.papyrus.dto.BorrowingDTO;
import com.sci.papyrus.dto.Borrowingform;
import com.sci.papyrus.entity.Borrowing;
import com.sci.papyrus.service.BorrowService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Borrow")
public class BorrowController {

    private final BorrowService borrowService;
    @PostMapping
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    public ResponseEntity<BorrowingDTO> create(@RequestBody @Validated Borrowingform form){
        return ResponseEntity.ok(borrowService.create(form));

    }
}
