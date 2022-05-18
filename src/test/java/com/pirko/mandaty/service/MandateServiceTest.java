package com.pirko.mandaty.service;

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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MandateServiceTest {

    @Mock
    private MandateRepository mandateRepository;

    @InjectMocks
    private MandateService mandateService;

    @Test
    void shouldInvokeFindPaginatedOneTime() {
        //given
        Page<Mandate> mandatePage = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(0, 4, Sort.by("id").ascending());
        when(this.mandateRepository.findAll(pageable)).thenReturn(mandatePage);
        //when
        mandateService.findPaginated(1, 4, "id", "ASC");
        //then
        verify(mandateRepository, times(1)).findAll(org.mockito.ArgumentMatchers.isA(Pageable.class));
    }

    @Test
    void shouldInvokeDeleteByIdOneTime() {
        //given
        Long idToDelete = 0L;
        //when
        mandateService.deleteById(idToDelete);
        //then
        verify(mandateRepository, times(1)).deleteById(idToDelete);
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
    void shouldSaveMandate() {
        //given
        Mandate mandate1 = new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(100));
        when(mandateRepository.save(mandate1)).thenReturn(mandate1);
        //when
        Mandate mandate2 = mandateRepository.save(mandate1);
        //then
        assertEquals(mandate1, mandate2);
    }
}
