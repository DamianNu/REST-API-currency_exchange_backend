package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.Cantor;
import com.kodilla.currencyexchange.domain.CantorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CantorMapper {
    public Cantor mapToCantor(final CantorDto cantorDto) {
        return new Cantor(
                cantorDto.getId(),
                cantorDto.getName(),
                cantorDto.getCity(),
                cantorDto.getAddress(),
                cantorDto.getOpeningHours()
        );
    }

    public CantorDto mapToCantorDto(final Cantor cantor) {
        return new CantorDto(
                cantor.getId(),
                cantor.getName(),
                cantor.getCity(),
                cantor.getAddress(),
                cantor.getOpeningHours()
        );
    }

    public List<CantorDto> mapToCantorDtoList(final List<Cantor> cantorList) {
        return cantorList.stream()
                .map(this::mapToCantorDto)
                .toList();
    }
}
