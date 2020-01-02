package app.component.controller.auth;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    @GetMapping("login")
    public String index(Model model){
        return "login";
    }

    @PostMapping("login")
    public String login(Model model){
        return "redirect:/";
    }

    @GetMapping("index")
    public String redirect(Model model){
        return "index";
    }
}
