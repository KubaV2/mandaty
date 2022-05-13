package com.pirko.mandaty.repository;

import com.pirko.mandaty.model.Mandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MandateRepository extends JpaRepository<Mandate, Long> {

    Optional<Mandate> findMandateById(@Param("id") Long id);

}
