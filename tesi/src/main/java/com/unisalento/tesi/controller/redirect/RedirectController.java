package com.unisalento.tesi.controller.redirect;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RedirectController {
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/biglietteria")
    public String biglietteria(Model model) {
        return "biglietteria";
    }

    @GetMapping("/registrazione")
    public String registrazione(Model model) {
        return "registrazione";
    }
}
