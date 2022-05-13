package com.pirko.mandaty.repository;

import com.pirko.mandaty.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findPersonByPesel(@Param("pesel") String pesel);

    Optional<Person> findPersonById(@Param("id") Long id);


}
