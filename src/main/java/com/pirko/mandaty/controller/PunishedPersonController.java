package com.pirko.mandaty.controller;

import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/karani")
@RequiredArgsConstructor
public class PunishedPersonController {

    private final PersonService personService;

    @GetMapping()
    public String getPunishedPersons(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Person> page = personService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Person> punishedPersons = page.getContent().stream()
                .filter(person -> person.getMandates().size() > 0)
                .collect(Collectors.toList());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalMandates", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listPunishedPersons", punishedPersons);
        return "person/punished";
    }
}
