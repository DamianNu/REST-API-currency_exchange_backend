package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.cantor.Cantor;
import com.kodilla.currencyexchange.domain.cantor.CantorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CantorMapperTestSuite {
    @Autowired
    CantorMapper cantorMapper;

    @Test
    void mapToCantorTest() {
        //Given
        CantorDto cantorDto = new CantorDto(10L,"Test","Warszawa","ul. Test 1","24h/7");

        //When
        Cantor cantor = cantorMapper.mapToCantor(cantorDto);

        //Then
        assertEquals(10L, cantor.getId());
        assertEquals("Test", cantor.getName());
        assertEquals("Warszawa", cantor.getCity());
        assertEquals("ul. Test 1", cantor.getAddress());
        assertEquals("24h/7", cantor.getOpeningHours());
    }

    @Test
    void mapToCantorDtoTest() {
        //Given
        Cantor cantor = new Cantor(10L,"Test","Warszawa","ul. Test 1","24h/7");

        //When
        CantorDto cantorDto = cantorMapper.mapToCantorDto(cantor);

        //Then
        assertEquals(10L, cantorDto.getId());
        assertEquals("Test", cantorDto.getName());
        assertEquals("Warszawa", cantorDto.getCity());
        assertEquals("ul. Test 1", cantorDto.getAddress());
        assertEquals("24h/7", cantorDto.getOpeningHours());
    }

    @Test
    void mapToCantorDtoListTest() {
        //Given
        Cantor cantor1 = new Cantor(10L,"Test1","Warszawa","ul. Test 1","24h/7");
        Cantor cantor2 = new Cantor(20L,"Test2","Gdańsk","ul. Test 2","6:00-18:00");
        List<Cantor> cantorList = new ArrayList<>();
        cantorList.add(cantor1);
        cantorList.add(cantor2);

        //When
        List<CantorDto> cantorDtoList = cantorMapper.mapToCantorDtoList(cantorList);

        //Then
        assertEquals("Test1", cantorDtoList.get(0).getName());
        assertEquals("Gdańsk", cantorDtoList.get(1).getCity());
        assertEquals("ul. Test 1", cantorDtoList.get(0).getAddress());
        assertEquals("6:00-18:00", cantorDtoList.get(1).getOpeningHours());
    }
}