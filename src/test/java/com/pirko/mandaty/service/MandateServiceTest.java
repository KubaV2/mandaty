package com.pirko.mandaty.service;

import com.pirko.mandaty.exception.PersonExistException;
import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.repository.MandateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MandateServiceTest {

    @Mock
    private MandateRepository mandateRepository;

    @InjectMocks
    private MandateService mandateService;

    @Test
    void shouldSaveMandate() {
        //given
        Mandate mandate1 = new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(100));
        when(mandateRepository.save(mandate1)).thenReturn(mandate1);
        //when
        Mandate mandate2 = mandateService.save(mandate1);
        //then
        assertEquals(mandate1, mandate2);
    }

    @Test
    void shouldThrowExceptionAndNotSaveMandate() {
        //given
        Mandate mandate1 = new Mandate(1L, "23232323231", LocalDateTime.now().minusHours(2), new ArrayList<>(), 11, BigDecimal.valueOf(100));
        when(mandateRepository.findMandateById(anyLong())).thenReturn(Optional.of(mandate1));
        //when
        PersonExistException ex = assertThrows(
                PersonExistException.class, () -> mandateService.save(mandate1));
        //then
        assertTrue(ex.getMessage().contains("Ten mandat jest już w bazie danych"));
    }

    @Test
    void shouldReturnListOfMandates() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(2), new ArrayList<>(), 16, BigDecimal.valueOf(100)));
        when(mandateRepository.findAll()).thenReturn(mandateList);
        //when
        List<Mandate> loadedList = mandateService.findAll();
        //then
        assertEquals(mandateList.size(), loadedList.size());
    }

    @Test
    void shouldThrowExceptionWhenTryDeleteNotExisId() {
        //given
        Long id = 0L;
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> mandateService.deleteById(id));
        //then
        assertTrue(ex.getMessage().contains("Nie można wykonać operacji usuwania. Brak mandatu o ID " + id));
    }

    @Test
    void shouldThrowExceptionWhenMandateIsNotExistInDataBaseAndNotDelete() {
        //given
        Long id = 1L;
        when(mandateRepository.findMandateById(anyLong())).thenReturn(Optional.empty());
        //when
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> mandateService.deleteById(id));
        //then
        assertTrue(ex.getMessage().contains("Nie można wykonać operacji usuwania. Brak mandatu o ID " + id));
    }

    @Test
    void shouldInvokeFindAllOneTime() {
        //given
        Page<Mandate> mandatePage = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(0, 4, Sort.by("id").ascending());
        when(this.mandateRepository.findAll(pageable)).thenReturn(mandatePage);
        //when
        mandateService.findPaginated(1, 4, "id", "ASC");
        //then
        verify(mandateRepository, times(1)).findAll(org.mockito.ArgumentMatchers.isA(Pageable.class));
    }

}
