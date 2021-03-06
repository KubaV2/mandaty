package com.pirko.mandaty.controller;

import ch.qos.logback.classic.pattern.ThrowableHandlingConverter;
import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.model.Offense;
import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.service.MandateService;
import com.pirko.mandaty.service.OffenseService;
import com.pirko.mandaty.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mandat")
@RequiredArgsConstructor
public class MandateController {

    private final MandateService mandateService;
    private final PersonService personService;
    private final OffenseService offenseService;

    @GetMapping("/wystaw")
    public String getMandateCreateForm(Model model) {
        Mandate mandate = new Mandate();
        Map<String, List<Offense>> offenses = offenseService.findOffenseByGroup();
        model.addAttribute("offenses", offenses);
        model.addAttribute("mandate", mandate);
        return "mandate/create";
    }

    @PostMapping("/wystaw")
    public String saveMandate(@Valid Mandate mandate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Map<String, List<Offense>> offenses = offenseService.findOffenseByGroup();
            model.addAttribute("offenses", offenses);
            return "mandate/create";
        }
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
