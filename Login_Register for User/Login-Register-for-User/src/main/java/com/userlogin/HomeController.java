package com.userlogin;


import com.userlogin.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        boolean success = userService.register(user);
        if (success) {
            return "redirect:/home?registerSuccess=true";
        } else {
            return "redirect:/home?registerError=true";
        }
    }
}

