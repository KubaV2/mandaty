package com.pirko.mandaty.service;

import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.repository.MandateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MandateService {

    private final MandateRepository mandateRepository;

    public Mandate save(Mandate mandate) {
        return mandateRepository.save(mandate);
    }

    public List<Mandate> findAll() {
        return mandateRepository.findAll();
    }

    public void deleteById(Long id) {
        if (mandateRepository.findMandateById(id).isEmpty()) {
            mandateRepository.deleteById(id);
        } else throw new EntityNotFoundException("Brak mandatu o ID " + id);
    }

    public Page<Mandate> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.mandateRepository.findAll(pageable);
    }
}
