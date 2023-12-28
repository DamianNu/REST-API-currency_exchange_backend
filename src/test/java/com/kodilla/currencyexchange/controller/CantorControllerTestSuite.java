package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.cantor.Cantor;
import com.kodilla.currencyexchange.domain.cantor.CantorDto;
import com.kodilla.currencyexchange.exception.CantorNotFoundException;
import com.kodilla.currencyexchange.mapper.CantorMapper;
import com.kodilla.currencyexchange.service.db.DbServiceCantor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CantorControllerTestSuite {

    @Autowired
    private CantorController controller;

    @MockBean
    private DbServiceCantor dbService;

    @MockBean
    private CantorMapper mapper;

    @Test
    void shouldFetchCurrencyTest() {
        // Given
        List<Cantor> cantorList = new ArrayList<>();
        Cantor cantor1 = new Cantor(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        Cantor cantor2 = new Cantor(2L,"Cantor2","Poznań","ul. Prosta 2", "pon-pt: 8:00-17:00");
        Cantor cantor3 = new Cantor(3L,"Cantor3","Gdynia","ul. Prosta 3", "pon-pt: 7:00-16:00");
        cantorList.add(cantor1);
        cantorList.add(cantor2);
        cantorList.add(cantor3);
        List<CantorDto> cantorListDto = new ArrayList<>();
        CantorDto cantorDto1 = new CantorDto(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        CantorDto cantorDto2 = new CantorDto(2L,"Cantor2","Poznań","ul. Prosta 2", "pon-pt: 8:00-17:00");
        CantorDto cantorDto3 = new CantorDto(3L,"Cantor3","Gdynia","ul. Prosta 3", "pon-pt: 7:00-16:00");
        cantorListDto.add(cantorDto1);
        cantorListDto.add(cantorDto2);
        cantorListDto.add(cantorDto3);

        when(dbService.getAllCantors()).thenReturn(cantorList);
        when(mapper.mapToCantorDtoList(cantorList)).thenReturn(cantorListDto);
        //When & Then
        ResponseEntity<List<CantorDto>> listResponseEntity = controller.getCantor();

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        List<CantorDto> listCantorDto = listResponseEntity.getBody();
        assertNotNull(listCantorDto);
        assertEquals("Cantor1", listCantorDto.get(0).getName());
        assertEquals("Poznań", listCantorDto.get(1).getCity());
        assertEquals("ul. Prosta 3", listCantorDto.get(2).getAddress());
    }

    @Test
    void shouldFetchCantorByIdTest() throws CantorNotFoundException {
        // Given
        Cantor cantor1 = new Cantor(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        CantorDto cantorDto1 = new CantorDto(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");

        when(dbService.getCantor(1L)).thenReturn(cantor1);
        when(mapper.mapToCantorDto(cantor1)).thenReturn(cantorDto1);

        // When & Then
        ResponseEntity<CantorDto> responseEntity = controller.getCantor(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CantorDto cantorDtoTest = responseEntity.getBody();
        assertEquals("Cantor1", cantorDtoTest.getName());
        assertEquals("Warszawa", cantorDtoTest.getCity());
        assertEquals("ul. Prosta 1", cantorDtoTest.getAddress());
        assertEquals("pon-pt: 7:00-17:00", cantorDtoTest.getOpeningHours());
    }

    @Test
    void deleteCantorByIdTest() {
        // When & Then
        ResponseEntity<Void> responseEntity = controller.deleteCantor(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateCantorTest() {
        // Given
        Cantor cantor = new Cantor(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        Cantor cantorSave = new Cantor(1L,"Cantor1","Poznań","ul. Prosta 2", "pon-pt: 9:00-19:00");
        CantorDto cantorDto = new CantorDto(2L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        CantorDto cantorDtoSave = new CantorDto(2L,"Cantor1","Poznań","ul. Prosta 2", "pon-pt: 9:00-19:00");

        when(mapper.mapToCantor(cantorDto)).thenReturn(cantor);
        when(dbService.saveCantor(cantor)).thenReturn(cantorSave);
        when(mapper.mapToCantorDto(cantorSave)).thenReturn(cantorDtoSave);

        // When & Then
        ResponseEntity<CantorDto> responseEntity = controller.updateCantor(cantorDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CantorDto cantorTest = responseEntity.getBody();
        assertEquals("Cantor1", cantorTest.getName());
        assertEquals("Poznań", cantorTest.getCity());
        assertEquals("ul. Prosta 2", cantorTest.getAddress());
        assertEquals("pon-pt: 9:00-19:00", cantorTest.getOpeningHours());
    }

    @Test
    void createCantorTest() {
        // Given
        Cantor cantor = new Cantor(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        CantorDto cantorDto = new CantorDto(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");

        when(mapper.mapToCantor(cantorDto)).thenReturn(cantor);
        when(dbService.saveCantor(cantor)).thenReturn(cantor);

        // When & Then
        ResponseEntity<Void> responseEntity = controller.createCantor(cantorDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldFetchCantorByNameTest() throws CantorNotFoundException {
        // Given
        Cantor cantor = new Cantor(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        CantorDto cantorDto = new CantorDto(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");

        when(dbService.getCantorByName("Cantor1")).thenReturn(cantor);
        when(mapper.mapToCantorDto(cantor)).thenReturn(cantorDto);

        // When & Then
        ResponseEntity<CantorDto> responseEntity = controller.getCantorByName("Cantor1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CantorDto cantorTest = responseEntity.getBody();
        assertEquals("Cantor1", cantorTest.getName());
        assertEquals("Warszawa", cantorTest.getCity());
        assertEquals("ul. Prosta 1", cantorTest.getAddress());
        assertEquals("pon-pt: 7:00-17:00", cantorTest.getOpeningHours());
    }

    @Test
    void shouldFetchCantorByCityTest() throws CantorNotFoundException {
        // Given
        List<Cantor> cantorList = new ArrayList<>();
        Cantor cantor1 = new Cantor(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        Cantor cantor2 = new Cantor(2L,"Cantor2","Warszawa","ul. Prosta 2", "pon-pt: 9:00-17:00");
        cantorList.add(cantor1);
        cantorList.add(cantor2);
        List<CantorDto> cantorDtoList = new ArrayList<>();
        CantorDto cantorDto1 = new CantorDto(1L,"Cantor1","Warszawa","ul. Prosta 1", "pon-pt: 7:00-17:00");
        CantorDto cantorDto2 = new CantorDto(2L,"Cantor2","Warszawa","ul. Prosta 2", "pon-pt: 9:00-17:00");
        cantorDtoList.add(cantorDto1);
        cantorDtoList.add(cantorDto2);

        when(dbService.getCantorByCity("Warszawa")).thenReturn(cantorList);
        when(mapper.mapToCantorDtoList(cantorList)).thenReturn(cantorDtoList);

        // When & Then
        ResponseEntity<List<CantorDto>> responseEntity = controller.getCantor("Warszawa");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<CantorDto> cantorTestList = responseEntity.getBody();
        assertEquals("Cantor1", cantorTestList.get(0).getName());
        assertEquals("Warszawa", cantorTestList.get(1).getCity());
        assertEquals("ul. Prosta 1", cantorTestList.get(0).getAddress());
        assertEquals("pon-pt: 9:00-17:00", cantorTestList.get(1).getOpeningHours());
    }
}