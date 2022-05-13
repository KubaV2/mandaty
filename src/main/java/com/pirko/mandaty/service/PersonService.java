package com.pirko.mandaty.service;

import com.pirko.mandaty.exception.PersonExistException;
import com.pirko.mandaty.exception.TooManyPointsException;
import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private static final int MAX_POINTS_PER_YEAR = 24;
    private final MailService mailService;
    private final PersonRepository personRepository;

    public Person save(Person person) {
        if (personRepository.findPersonByPesel(person.getPesel()).isEmpty()) {
            return personRepository.save(person);
        } else throw new PersonExistException("Osoba numerze pesel " + person.getPesel() + " już istnieje!");
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void deleteById(Long id) {
        if (personRepository.findPersonById(id).isEmpty()) {
            personRepository.deleteById(id);
        } else throw new EntityNotFoundException("Brak osoby o ID " + id);
    }

    public Person findPersonByPesel(String pesel) {
        return personRepository.findPersonByPesel(pesel).orElseThrow(
                () -> new EntityNotFoundException("Brak osoby z numerem PESEL: " + pesel + " w bazie danych."));
    }

    public Page<Person> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.personRepository.findAll(pageable);
    }

    public void addMandate(Person person, Mandate mandate) {
        if (personHasMoreThanMaxPointsPerYear(person)) {
            throw new TooManyPointsException(person.getFirstName() + " " + person.getLastName()
                    + " ma już przekroczoną dozwoloną liczbę punktów karnych.");
        } else if (!person.getMandates().contains(mandate)) {
            person.getMandates().add(mandate);
        }

        if (isItAboveMaxPointsPerYear(person, mandate.getPoints())) {
            mailService.sendEmail(person, "UWAGA! Przekroczony dopuszczalny limit punktów karnych",
                    "W związku z przekroczeniem dozwolonej ilości punktów karnych(" +
                            MAX_POINTS_PER_YEAR + ") odebrano ci prawo jazdy.");
        }
    }

    public boolean personHasMoreThanMaxPointsPerYear(Person person) {
        return countPointsFromCurrentYear(person) > MAX_POINTS_PER_YEAR;
    }

    public boolean isItAboveMaxPointsPerYear(Person person, int pointFromNewMandate) {
        return countPointsFromCurrentYear(person) + pointFromNewMandate > MAX_POINTS_PER_YEAR;
    }

    public Integer countPointsFromCurrentYear(Person person) {
        return person.getMandates().stream()
                .filter(mandate -> mandate.getDateTime().getYear() == LocalDateTime.now().getYear())
                .map(Mandate::getPoints)
                .reduce(0, Integer::sum);
    }
}
