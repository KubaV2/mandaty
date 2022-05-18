package com.pirko.mandaty.service;

import com.pirko.mandaty.model.Offense;
import com.pirko.mandaty.repository.OffenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OffenseService {

    private final OffenseRepository offenseRepository;

    public List<Offense> findAll(){
        return offenseRepository.findAll();
    }

    public Map<String, List<Offense>> offenseByGroup(){
        return findAll().stream().collect(Collectors.groupingBy(Offense::getOptgroup));
    }

}
