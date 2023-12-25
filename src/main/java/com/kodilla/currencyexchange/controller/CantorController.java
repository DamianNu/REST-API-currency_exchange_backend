package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.Cantor;
import com.kodilla.currencyexchange.domain.CantorDto;
import com.kodilla.currencyexchange.mapper.CantorMapper;
import com.kodilla.currencyexchange.service.DbServiceCantor;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{cantorId}")
    public ResponseEntity<CantorDto> getCantor(@PathVariable Long cantorId) throws CantorNotFoundException {
        return ResponseEntity.ok(mapper.mapToCantorDto(service.getCantor(cantorId)));
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

//    @RequestMapping(method = RequestMethod.GET, value = "/code/{currencyCode}")
//    public ResponseEntity<CurrencyDto> getCurrencyByCode(@PathVariable String currencyCode) throws CurrencyNotFoundException {
//        return ResponseEntity.ok(mapper.mapToCurrencyDto(service.getCurrencyByCode(currencyCode)));
//    }

//    @GetMapping("/up")
//    public ResponseEntity<Void> upCurrency() {
//        currencyService.updateCurrency();
//        return ResponseEntity.ok().build();
//    }
}
