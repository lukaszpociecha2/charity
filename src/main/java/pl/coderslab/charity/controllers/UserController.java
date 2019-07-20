package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.payload.Email;
import pl.coderslab.charity.payload.UserRegisterRequest;
import pl.coderslab.charity.service.UserService;



@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("user", new UserRegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerFormProcessing(@ModelAttribute UserRegisterRequest userRegisterRequest){
        System.out.println("In POST register");
        userService.saveUser(userRegisterRequest);
        return "main_login";
    }

    @PostMapping(value = "/validate-email", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean isEmailValid(@RequestBody Email myEmail){
        System.out.println(myEmail.getEmail());

        return !userService.isEmailValid(myEmail.getEmail());
    }

}
