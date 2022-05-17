package com.pirko.mandaty.controller;

import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/osoba")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/dodaj")
    public String getPersonCreateForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "person/create";
    }

    @PostMapping("/dodaj")
    public String savePerson(@Valid Person person) {
        personService.save(person);
        return "redirect:/osoba";
    }

    @GetMapping()
    public String getAllPersons(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Person> page = personService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Person> persons = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalMandates", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listPersons", persons);
        return "person/list";
    }

    @DeleteMapping("/usun/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deleteById(id);
        return "redirect:/osoba";
    }

}
