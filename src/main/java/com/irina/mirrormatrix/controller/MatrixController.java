package com.irina.mirrormatrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irina.mirrormatrix.model.Matrix;
import com.irina.mirrormatrix.service.MatrixService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class MatrixController {
	
    private final MatrixService matrixService;

    @Autowired
    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }	
	
    @PostMapping("/mirror")
    public ResponseEntity<Object> mirrorMatrix(@RequestBody(required = false) Matrix inputMatrix) {
        log.info("Received POST request: /api/mirror");            
        if (inputMatrix == null || inputMatrix.getMatrix() == null ) {
            return new ResponseEntity<>("Input is incorrect, expecting matrix 4x4", HttpStatus.BAD_REQUEST);
        }    	
    	var isValid = matrixService.isValidMatrix(inputMatrix);
    	if (!isValid) {
    		log.error("Input is not valid from service class");
    		return new ResponseEntity<Object> ("Input matrix is incorretc", HttpStatus.BAD_REQUEST); 
    	}
    	log.info("Input matrix is valid, processing");      	

    	Matrix mirroredMatrix = matrixService.mirrorMatrix(inputMatrix);
    	log.debug("Mirrored matrix: ", inputMatrix);

    	return new ResponseEntity<Object> (mirroredMatrix, HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Invalid request body format or content";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

	
}
