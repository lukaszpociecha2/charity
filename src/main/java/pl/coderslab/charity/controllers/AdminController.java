package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String adminLoginPanel(){
        return "admin_panel/login";
    }

    @GetMapping("/admin/index")
    public String adminIndex(){
        return "admin_panel/index";
    }

}
