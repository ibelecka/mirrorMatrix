package com.irina.mirrormatrix.controller;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irina.mirrormatrix.model.Matrix;
import com.irina.mirrormatrix.service.MatrixService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MatrixController.class)
@AutoConfigureMockMvc
public class MatrixControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatrixService matrixService;
    
    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        // Test scenario: correct input matrix
        Matrix inputMatrix = new Matrix();
        List<List<Integer>> innerMatrix = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16)
        );
        inputMatrix.setMatrix(innerMatrix);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(inputMatrix);

        when(matrixService.isValidMatrix(inputMatrix)).thenReturn(true);
        
        mockMvc.perform(post("/api/mirror")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());     
    }    
    
    @Test
    public void whenInvalidInput1_thenReturns400() throws Exception {
    	//Test scenario: input matrix in null
        Matrix inputMatrix = null;
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(inputMatrix);

        mockMvc.perform(post("/api/mirror")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());     
    }      

    @Test
    public void whenInvalidInput2_thenReturns400() throws Exception {
    	//Test scenario: input matrix in have null matrix
        Matrix inputMatrix = new Matrix();
        List<List<Integer>> innerMatrix  = null;
        inputMatrix.setMatrix(innerMatrix);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(inputMatrix);

        mockMvc.perform(post("/api/mirror")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());     
    }    
	
    @Test
    public void whenInvalidInput3_thenReturns400() throws Exception {
    	//Test scenario: matrix service return isValid = false;
        Matrix inputMatrix = new Matrix();
        List<List<Integer>> innerMatrix = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16),
                Arrays.asList(1, 2, 3, 4)
        );
        inputMatrix.setMatrix(innerMatrix);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(inputMatrix);
        
        when(matrixService.isValidMatrix(inputMatrix)).thenReturn(false);

        mockMvc.perform(post("/api/mirror")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());     
    }    

    @Test
    public void whenInvalidInput4_thenReturns400() throws Exception {
    	//Test scenario: input is not Matrix object
        String inputMatrix = "123";

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(inputMatrix);

        mockMvc.perform(post("/api/mirror")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());     
    }     
}
