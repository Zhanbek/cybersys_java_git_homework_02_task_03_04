package my.web.app.lesson1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
    @GetMapping("/")
    public String firstPage(Model model) {
        model.addAttribute("name", "Иван");
        return "index";
    }

    @GetMapping("/form")
    public String firstForm() {
        return "form";
    }

    @PostMapping("/form")
    public String postForm(Model model, @RequestParam String fio, @RequestParam String age) {
        model.addAttribute("fio", fio);
        model.addAttribute("age", age);
        return "info_user";
    }
}
