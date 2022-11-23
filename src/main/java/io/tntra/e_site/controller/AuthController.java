package io.tntra.e_site.controller;


import io.tntra.e_site.dto.UserDto;
import io.tntra.e_site.model.User;
import io.tntra.e_site.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    // handler method to handle user registration form request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
       return "register";
    }

     // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration( @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){

        User existing = userService.findUserByEmail(userDto.getEmail());

        if(existing != null){
            result.rejectValue("email","null","This Account is already registered");
        }
        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String ListOfRegisteredUser(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


}
