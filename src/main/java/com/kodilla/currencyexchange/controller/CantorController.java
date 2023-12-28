package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.exception.CantorNotFoundException;
import com.kodilla.currencyexchange.domain.cantor.Cantor;
import com.kodilla.currencyexchange.domain.cantor.CantorDto;
import com.kodilla.currencyexchange.mapper.CantorMapper;
import com.kodilla.currencyexchange.service.db.DbServiceCantor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cantors")
@RequiredArgsConstructor
public class CantorController {
    private final CantorMapper mapper;
    private final DbServiceCantor service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CantorDto>> getCantor() {
        List<Cantor> cantorList = service.getAllCantors();
        return ResponseEntity.ok(mapper.mapToCantorDtoList(cantorList));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{cantorId}")
    public ResponseEntity<CantorDto> getCantor(@PathVariable Long cantorId) throws CantorNotFoundException {
        return ResponseEntity.ok(mapper.mapToCantorDto(service.getCantor(cantorId)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}")
    public ResponseEntity<CantorDto> getCantorByName(@PathVariable String name) throws CantorNotFoundException {
        return ResponseEntity.ok(mapper.mapToCantorDto(service.getCantorByName(name)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/city/{city}")
    public ResponseEntity<List<CantorDto>> getCantor(@PathVariable String city) throws CantorNotFoundException {
        return ResponseEntity.ok(mapper.mapToCantorDtoList(service.getCantorByCity(city)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{cantorId}")
    public ResponseEntity<Void> deleteCantor(@PathVariable Long cantorId) {
        service.deleteCantor(cantorId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CantorDto> updateCantor(@RequestBody CantorDto cantorDto) {
        Cantor cantor = mapper.mapToCantor(cantorDto);
        Cantor saveCantor = service.saveCantor(cantor);
        return ResponseEntity.ok(mapper.mapToCantorDto(saveCantor));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createCantor(@RequestBody CantorDto cantorDto) {
        Cantor cantor = mapper.mapToCantor(cantorDto);
        service.saveCantor(cantor);
        return ResponseEntity.ok().build();
    }
}
