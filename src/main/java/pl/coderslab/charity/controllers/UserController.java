package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.payload.Email;
import pl.coderslab.charity.payload.UserRegisterRequest;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.EmailServiceImpl;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    private UserService userService;
    private UserRepository userRepository;
    private EmailServiceImpl emailService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, EmailServiceImpl emailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerFormProcessing(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        } else if (userRepository.existsByUsername(user.getUsername())){
            FieldError error = new FieldError("user", "username", "This email is not available");
            result.addError(error);
            return "register";
        } else

        userService.saveUser(user);
        return "redirect:/donate";
    }

    @PostMapping(value = "/validate-email", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean isEmailValid(@RequestBody Email myEmail){
        System.out.println(myEmail.getEmail());

        return !userService.isEmailValid(myEmail.getEmail());
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails!=null) {

            User user = userRepository.findByUsername(userDetails.getUsername());
            UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
            userRegisterRequest.setFirstName(user.getFirstName());
            userRegisterRequest.setLastName(user.getLastName());
            userRegisterRequest.setUsername(user.getUsername());

            model.addAttribute("userRegisterRequest", userRegisterRequest);
            return "profile";
        }
        return "redirect:/login";
    }

    @PostMapping("/profile")
    public String editProcess(@AuthenticationPrincipal UserDetails userDetails, @Valid @ModelAttribute UserRegisterRequest userRegisterRequest, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "profile";
        }
        if(userRepository.existsByUsername(userRegisterRequest.getUsername())){
            FieldError error = new FieldError("modelUser", "username", "This email already exists");
            bindingResult.addError(error);
            return "profile";
        }
        else {
            User oldUser = userRepository.findByUsername(userDetails.getUsername());
            oldUser.setUsername(userRegisterRequest.getUsername());
            oldUser.setPassword(userRegisterRequest.getPassword());
            oldUser.setFirstName(userRegisterRequest.getFirstName());
            oldUser.setLastName(userRegisterRequest.getLastName());
            userService.saveUser(oldUser);
            return "redirect:/login";
        }

        /*if(modelUser.getUsername()!=null) {
        *//*    if (result.hasErrors()){
                return "profile";
            } else *//*if (userRepository.existsByUsername(modelUser.getUsername())){
//                FieldError error = new FieldError("modelUser", "username", "This email already exists");
//                result.addError(error);
                return "profile";
            } else {

                User oldUser = userRepository.findByUsername(userDetails.getUsername());
                oldUser.setUsername(modelUser.getUsername());
                oldUser.setPassword(modelUser.getPassword());
                oldUser.setFirstName(modelUser.getFirstName());
                oldUser.setLastName(modelUser.getLastName());
                userService.saveUser(oldUser);
                return "redirect:/login";
            }
        } else {

            return "redirect:/login";
        }*/
    }

    @GetMapping("send")
    public void sendMessage(){
        //emailService.send();
    }
}
