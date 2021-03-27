package com.example.smartsoft.controllers;

import com.example.smartsoft.models.dto.FromToDto;
import com.example.smartsoft.models.entity.Valute;
import com.example.smartsoft.services.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ValuteService valuteService;



    @GetMapping("/")
    public String index() {
        valuteService.getFreshValuteList();
        System.out.println("OK");
        return "redirect:/converter";
    }

    //valuteservice post запрос получать id1,id2,кол-во

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/converter")
    public String converter(Model model, @ModelAttribute("userModel") final FromToDto userModel) {
        valuteService.getFreshValuteList();
        model.addAttribute("valutes", valuteService.getAllValute());
        return "converter";

    }

    @PostMapping(value = "/convert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public String convert(Model model, FromToDto fromToDto, final RedirectAttributes redirectAttributes) {
        fromToDto = valuteService.convertValue(fromToDto);
        valuteService.saveHistory(fromToDto);
        redirectAttributes.addFlashAttribute("userModel", fromToDto);
        return "redirect:/converter";
    }

    @GetMapping("/login")
    public String auth() {
        return "login";
    }


    //история должна хранить историю конвертаций!
    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("history", valuteService.getHistory());
        return "history";
    }


}
