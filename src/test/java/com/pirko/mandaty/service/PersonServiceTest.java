package com.pirko.mandaty.service;

import com.pirko.mandaty.exception.PersonExistException;
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

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void shouldSavePerson() {
        //given
        Person somePerson1 = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        when(personRepository.save(somePerson1)).thenReturn(somePerson1);
        //when
        Person somePerson2 = personRepository.save(somePerson1);
        //then
        assertEquals(somePerson1, somePerson2);
    }

    @Test
    void shouldReturnPersonExistExceptionWhenPersonWithSameIdExist() {
        //given
        Person somePerson1 = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        Person somePerson2 = new Person(1L, "12345123451", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        when(personRepository.findPersonById(somePerson1.getId())).thenReturn(Optional.of(somePerson1));
        //when
        PersonExistException ex = assertThrows(
                PersonExistException.class, () -> personService.save(somePerson2));
        //then
        assertTrue(ex.getMessage().contains("Osoba ID " + somePerson1.getId() + " już istnieje!"));
    }

    @Test
    void shouldReturnPersonExistExceptionWhenPersonWithSamePeselExist() {
        //given
        Person somePerson1 = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        when(personRepository.findPersonByPesel(somePerson1.getPesel())).thenReturn(Optional.of(somePerson1));
        //when
        PersonExistException ex = assertThrows(
                PersonExistException.class, () -> personService.save(somePerson1));
        //then
        assertTrue(ex.getMessage().contains("Osoba numerze pesel " + somePerson1.getPesel() + " już istnieje!"));
    }

    @Test
    void shouldReturnListOfPersons() {
        //given
        List<Person> personList = List.of(
                new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>()),
                new Person(2L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>()));
        when(personRepository.findAll()).thenReturn(personList);
        //when
        List<Person> loadedList = personService.findAll();
        //then
        assertEquals(personList.size(), loadedList.size());
    }

    @Test
    void shouldThrowExceptionWhenTryDeleteNotExisId() {
        //given
        Long id = 0L;
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> personService.deleteById(id));
        //then
        assertTrue(ex.getMessage().contains("Nie można wykonać operacji usuwania. Brak osoby o ID " + id));
    }

    @Test
    void shouldThrowExceptionWhenPersonIsNotExistInDataBaseAndNotDelete() {
        //given
        Long id = 1L;
        when(personRepository.findPersonById(anyLong())).thenReturn(Optional.empty());
        //when
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> personService.deleteById(id));
        //then
        assertTrue(ex.getMessage().contains("Nie można wykonać operacji usuwania. Brak osoby o ID " + id));
    }

    @Test
    void shouldFindPersonByPesel() {
        //given
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        when(personRepository.findPersonByPesel(anyString())).thenReturn(Optional.of(somePerson));
        //when
        Person person = personService.findPersonByPesel("123");
        //then
        assertEquals(person, somePerson);
    }

    @Test
    void shouldThrowExceptionAndNotFindPersonByPesel() {
        //given
        String pesel = "pesel";
        when(personRepository.findPersonByPesel(anyString())).thenReturn(Optional.empty());
        //when
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> personService.findPersonByPesel(pesel));
        //then
        assertTrue(ex.getMessage().contains("Brak osoby z numerem PESEL: " + pesel + " w bazie danych."));
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
        verify(personRepository, times(1)).findAll(isA(Pageable.class));
    }

    @Test
    void shouldThrowExceptionWhenGivenPersonIsNullAndWhenMandateIsAdded() {
        //given
        //when
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> personService.addMandate(null, new Mandate()));
        //then
        assertTrue(ex.getMessage().contains("Nie można wystawić mandatu, brak podanej osoby w bazie danych"));
    }

    @Test
    void shouldThrowExceptionWhenGivenMandateIsNullAndWhenMandateIsAdded() {
        //given
        //when
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> personService.addMandate(new Person(), null));
        //then
        assertTrue(ex.getMessage().contains("Nie można wystawić mandatu, brak podanego mandatu w bazie danyc"));
    }

    @Test
    void shouldThrowExceptionWhenAddMandateAndPointsIsMoreThanMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(2), new ArrayList<>(), 16, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, mandateList);
        //when
        TooManyPointsException ex = assertThrows(
                TooManyPointsException.class, () -> personService.addMandate(somePerson, new Mandate()));
        //then
        assertTrue(ex.getMessage().contains(somePerson.getFirstName() + " " + somePerson.getLastName()
                + " ma już przekroczoną dozwoloną liczbę punktów karnych, sprawa musi trafić do sądu."));
    }

    @Test
    void shouldAddMandateForPerson() {
        //given
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        //when
        personService.addMandate(somePerson, new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(100)));
        //then
        assertEquals(somePerson.getMandates().size(), 1);
    }

    @Test
    void shouldReturnTrueIfPersonHasMoreThanMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(2), new ArrayList<>(), 10, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusHours(3), new ArrayList<>(), 5, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, mandateList);
        //when
        boolean isTrue = personService.personHasMoreThanMaxPointsPerYear(somePerson);
        //then
        assertTrue(isTrue);
    }

    @Test
    void shouldReturnFalseIfPersonHasLessThanMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(2), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusHours(3), new ArrayList<>(), 5, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, mandateList);
        //when
        boolean isFalse = personService.personHasMoreThanMaxPointsPerYear(somePerson);
        //then
        assertFalse(isFalse);
    }

    @Test
    void shouldReturnTrueIfWillItBeAboveMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(3), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusHours(5), new ArrayList<>(), 5, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, mandateList);
        //when
        boolean isTrue = personService.isItAboveMaxPointsPerYear(somePerson, 10);
        //then
        assertTrue(isTrue);
    }

    @Test
    void shouldReturnFalseIfWillItBeUnderMaxPointsPerYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now().minusHours(1), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now().minusHours(3), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusHours(5), new ArrayList<>(), 5, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, mandateList);
        //when
        boolean isFalse = personService.isItAboveMaxPointsPerYear(somePerson, 1);
        //then
        assertFalse(isFalse);
    }

    @Test
    void shouldGetCountedPointsFromCurrentYear() {
        //given
        List<Mandate> mandateList = List.of(
                new Mandate(1L, "12345678912", LocalDateTime.now(), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(2L, "12345678912", LocalDateTime.now(), new ArrayList<>(), 5, BigDecimal.valueOf(100)),
                new Mandate(3L, "12345678912", LocalDateTime.now().minusYears(1), new ArrayList<>(), 99, BigDecimal.valueOf(100)),
                new Mandate(4L, "12345678912", LocalDateTime.now().minusYears(1), new ArrayList<>(), 99, BigDecimal.valueOf(100)));
        Person somePerson = new Person(1L, "12345678912", "Jan", "Nowak", "nowak@wp.pl", 0, mandateList);
        //when
        Integer countedPoints = personService.countPointsFromCurrentYear(somePerson);
        //then
        assertEquals(countedPoints, 10);
    }

    @Test
    void shouldThrowExceptionAndNotCountPointsFromCurrentYear() {
        //given
        Person somePerson = null;
        //when
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class, () -> personService.countPointsFromCurrentYear(somePerson));
        //then
        assertTrue(ex.getMessage().contains("Nie można policzyć ilości punktów w danym roku. Brak podanej osoby w bazie danych"));
    }

}
