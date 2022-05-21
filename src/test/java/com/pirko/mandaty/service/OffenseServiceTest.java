package com.pirko.mandaty.service;

import com.pirko.mandaty.model.Offense;
import com.pirko.mandaty.repository.OffenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OffenseServiceTest {

    @Mock
    private OffenseRepository offenseRepository;

    @InjectMocks
    private OffenseService offenseService;


    @Test
    void shouldReturnListOfOffenses() {
        //given
        List<Offense> offenses = List.of(
                new Offense(1L,"wykroczenie", "za szybka jazda"),
                new Offense(2L,"wykroczenie","potracenie"));
        when(offenseRepository.findAll()).thenReturn(offenses);
        //when
        List<Offense> loadedList = offenseService.findAll();
        //then
        assertEquals(offenses.size(), loadedList.size());
    }

    @Test
    void shouldReturnMapWithObjectsSortedByGroup() {
        //given
        List<Offense> offenses = List.of(new Offense(1L,"wykroczenie", "za szybka jazda"),
                new Offense(2L,"wykroczenie","potracenie"));
        when(offenseRepository.findAll()).thenReturn(offenses);
        //when
        Map<String, List<Offense>> offensesMap = offenseService.findOffenseByGroup();
        //then
        assertEquals(offensesMap.get("wykroczenie"), offenses);
    }

}
