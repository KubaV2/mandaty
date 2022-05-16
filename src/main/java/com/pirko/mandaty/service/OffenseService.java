package com.pirko.mandaty.service;

import com.pirko.mandaty.model.Offense;
import com.pirko.mandaty.repository.OffenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffenseService {

    private final OffenseRepository offenseRepository;

    public List<Offense> findAll(){
        return offenseRepository.findAll();
    }
}
