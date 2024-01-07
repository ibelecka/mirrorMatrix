package com.irina.mirrormatrix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.irina.mirrormatrix.model.Matrix;

@SpringBootTest
public class MatrixServiceTest {
    
	@Autowired
    private MatrixService matrixService;
	
    @Test
    public void testMirrorMatrix() {
        // Test scenario: Mirroring a sample input matrix
    	Matrix inputMatrix = new Matrix();
        List<List<Integer>> innerMatrix = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16)
        );

        inputMatrix.setMatrix(innerMatrix);
        
        Matrix expectedMirroredMatrix = new Matrix();
        List<List<Integer>> mirroredInnerMatrix = Arrays.asList(
                Arrays.asList(4, 3, 2, 1),
                Arrays.asList(8, 7, 6, 5),
                Arrays.asList(12, 11, 10, 9),
                Arrays.asList(16, 15, 14, 13)
        );
        expectedMirroredMatrix.setMatrix(mirroredInnerMatrix);
        
        Matrix mirroredMatrix = matrixService.mirrorMatrix(inputMatrix);
        assertEquals(expectedMirroredMatrix, mirroredMatrix);
    }	
    
    @Test
    public void isValidMatrix_1() {
    	//Test scenario: more rows then expected
    	Matrix inputMatrix = new Matrix();
        List<List<Integer>> innerMatrix = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16)
        );

        inputMatrix.setMatrix(innerMatrix);

        boolean expectedResult = false;
        
        var isValid = matrixService.isValidMatrix(inputMatrix);
        assertEquals(expectedResult, isValid);
    }    
    
    @Test
    public void isValidMatrix_2() {
    	//Test scenario: more columns then expected
    	Matrix inputMatrix = new Matrix();
        List<List<Integer>> innerMatrix = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16),
                Arrays.asList(13, 14, 15, 16)
        );

        inputMatrix.setMatrix(innerMatrix);

        boolean expectedResult = false;
        
        var isValid = matrixService.isValidMatrix(inputMatrix);
        assertEquals(expectedResult, isValid);
    }     
    
    
    @Test
    public void isValidMatrix_3() {
    	//Test scenario: not all natural numbers
    	Matrix inputMatrix = new Matrix();
        List<List<Integer>> innerMatrix = Arrays.asList(
                Arrays.asList(1, -1, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16),
                Arrays.asList(13, 14, 15, 16)
        );

        inputMatrix.setMatrix(innerMatrix);
        boolean expectedResult = false;
        
        var isValid = matrixService.isValidMatrix(inputMatrix);
        assertEquals(expectedResult, isValid);
    }              
    
}
