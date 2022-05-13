package com.pirko.mandaty.controller;

import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.service.MandateService;
import com.pirko.mandaty.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mandat")
@RequiredArgsConstructor
public class MandateController {

    private final MandateService mandateService;
    private final PersonService personService;

    @GetMapping("/wystaw")
    public String getMandateCreateForm(Model model) {
        Mandate mandate = new Mandate();
        model.addAttribute("mandate", mandate);
        return "mandate/create";
    }

    @PostMapping("/wystaw")
    public String saveMandate(Mandate mandate) {
        Person person = personService.findPersonByPesel(mandate.getPesel());
        personService.addMandate(person, mandate);
        mandateService.save(mandate);
        return "redirect:/mandat";
    }

    @GetMapping()
    public String getAllMandates(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Mandate> page = mandateService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Mandate> mandates = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalMandates", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listMandates", mandates);
        return "mandate/list";
    }

    @DeleteMapping("/usun/{id}")
    public String deleteMandate(@PathVariable Long id) {
        mandateService.deleteById(id);
        return "redirect:/mandat";
    }

}
