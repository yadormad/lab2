package com.mag.lab2.controller;

import com.mag.lab2.model.dto.Machinist;
import com.mag.lab2.service.MachinistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MachinistController {
    private final MachinistService machinistService;

    @Autowired
    public MachinistController(@Qualifier("machinistLogger") MachinistService machinistService) {
        this.machinistService = machinistService;
    }

    @GetMapping("/machinists")
    public String getAllMachinists(Model model) {
        model.addAttribute("machinistsList", machinistService.getAll());
        model.addAttribute("machinist", new Machinist());
        return "machinists";
    }

    @RequestMapping(value="/machinists/edit", method=RequestMethod.POST)
    public String editMachinist(@ModelAttribute Machinist machinist) {
        machinistService.editMachinist(machinist);
        return "redirect:/machinists";
    }

    @RequestMapping(value="/machinists/add", method=RequestMethod.POST)
    public String addMachinist(@ModelAttribute Machinist machinist) {
        machinistService.addMachinist(machinist);
        return "redirect:/machinists";
    }

    @RequestMapping(value="/machinists/delete", method=RequestMethod.POST)
    public String deleteMachinist(@ModelAttribute Machinist machinist) {
        machinistService.delete(machinist.getId());
        return "redirect:/machinists";
    }
}
