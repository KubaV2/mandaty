package com.pirko.mandaty.service;

import com.pirko.mandaty.exception.TooManyPointsException;
import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.repository.PersonRepository;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void shouldGetCountedPointsFromCurrentYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), "Wykroczenie1", 5, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(3), "Wykroczenie2", 5, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusYears(1), "Wykroczenie3", 5, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", mandateList);
        //when
        Integer countedPoints = personService.countPointsFromCurrentYear(somePerson);
        //then
        assertEquals(countedPoints, 10);
        assertNotEquals(countedPoints, 15);
    }

    @Test
    void shouldReturnTrueIfWillItBeAboveMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), "Wykroczenie1", 5, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(3), "Wykroczenie2", 5, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusYears(1), "Wykroczenie3", 5, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", mandateList);
        //when
        boolean isTrue = personService.isItAboveMaxPointsPerYear(somePerson, 15);
        boolean isFalse = personService.isItAboveMaxPointsPerYear(somePerson, 1);
        //then
        assertTrue(isTrue);
        assertFalse(isFalse);
    }

    @Test
    void shouldReturnTrueIfPersonHasMoreThanMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), "Wykroczenie1", 10, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(2), "Wykroczenie2", 10, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusHours(3), "Wykroczenie3", 5, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", mandateList);
        //when
        boolean isTrue = personService.personHasMoreThanMaxPointsPerYear(somePerson);
        //then
        assertTrue(isTrue);
    }

    @Test
    void shouldThrowExceptionWhenAddMandateWhenPointsIsMoreThanMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), "Wykroczenie1", 10, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(2), "Wykroczenie2", 16, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", mandateList);
        //when
        //then
        TooManyPointsException ex = assertThrows(
                TooManyPointsException.class, () -> personService.addMandate(somePerson, new Mandate()));
        assertTrue(ex.getMessage().contains(somePerson.getFirstName() + " " + somePerson.getLastName()
                + " ma już przekroczoną dozwoloną liczbę punktów karnych."));
    }

    @Test
    void shouldAddMandateForPerson() {
        //given
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", new ArrayList<>());
        //when
        personService.addMandate(somePerson, new Mandate());
        //then
        assertEquals(somePerson.getMandates().size(), 1);
    }

    @Test
    void shouldInvokeFindPaginatedOneTime() {
        //given
        Page<Person> personPage = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(0, 4, Sort.by("id").ascending());
        when(this.personRepository.findAll(pageable)).thenReturn(personPage);
        //when
        Page<Person> test = personService.findPaginated(1, 4, "id", "ASC");
        //then
        verify(personRepository, times(1)).findAll(org.mockito.ArgumentMatchers.isA(Pageable.class));
    }

    @Test
    void shouldFindPersonByPesel() {
        //given
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", new ArrayList<>());
        when(personRepository.findPersonByPesel(anyString())).thenReturn(Optional.of(somePerson));
        //when
        Person person = personService.findPersonByPesel("123");
        //then
        assertEquals(person, somePerson);
    }

    @Test
    void shouldInvokeDeleteByIdOneTime() {
        //given
        Long idToDelete = 0L;
        //when
        personService.deleteById(idToDelete);
        //then
        verify(personRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void shouldReturnListOfPersons() {
        //given
        List<Person> personList = List.of(
                new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", new ArrayList<>()),
                new Person(2L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", new ArrayList<>()));
        when(personRepository.findAll()).thenReturn(personList);

        //when
        List<Person> loadedList = personService.findAll();

        //then
        assertEquals(personList.size(), loadedList.size());
    }

    @Test
    void shouldSavePerson() {
        //given
        Person somePerson1 = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", new ArrayList<>());
        when(personRepository.save(somePerson1)).thenReturn(somePerson1);
        //when
        Person somePerson2 = personRepository.save(somePerson1);
        //then
        assertEquals(somePerson1, somePerson2);
    }
}
