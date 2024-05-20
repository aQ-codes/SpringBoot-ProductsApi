package rest_project.apiproduct.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import rest_project.apiproduct.model.ApplicationUser;
import rest_project.apiproduct.model.LoginResponseDTO;
import rest_project.apiproduct.model.RegistrationDTO;
import rest_project.apiproduct.Services.AuthenticationService;


@RestController
@CrossOrigin("http://localhost:3002")
@RequestMapping("/auth")




public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
   
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}  