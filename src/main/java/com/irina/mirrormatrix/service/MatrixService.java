package com.irina.mirrormatrix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.irina.mirrormatrix.model.Matrix;

@Service
public class MatrixService {
    public static final int MATRIX_MAX_ROWS = 4;
    public static final int MATRIX_MAX_COLUMNS = 4;
    
    public Matrix mirrorMatrix(Matrix inputMatrix) {
    	List<List<Integer>> matrix = new ArrayList<>();
        
        for (int i = 0; i < MATRIX_MAX_ROWS; i++) {
        	ArrayList<Integer> innerList = new ArrayList<>();
            for (int j = 0; j < MATRIX_MAX_COLUMNS; j++) {            	
            	innerList.add(j, inputMatrix.getMatrix().get(i).get(MATRIX_MAX_COLUMNS - 1 - j));
            }
            matrix.add(innerList);
        }
        Matrix mirroredMatrix = new Matrix();
        mirroredMatrix.setMatrix(matrix);
        return mirroredMatrix;
    }

    public boolean isValidMatrix(Matrix inputMatrix) {  
        if (inputMatrix.getMatrix().size() != MATRIX_MAX_ROWS) {
            return false;
        }
        for (var row : inputMatrix.getMatrix()) {
            if (row.size() != MATRIX_MAX_COLUMNS) {
                return false;
            }
            for (int element : row) {
            	if (element < 0) {
            		return false;
            	}
            }
        }    	
        return true;
    }
}
