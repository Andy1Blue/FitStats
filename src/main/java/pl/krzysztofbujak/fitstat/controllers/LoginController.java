package pl.krzysztofbujak.fitstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.krzysztofbujak.fitstat.forms.LoginForm;
import pl.krzysztofbujak.fitstat.repositories.DataRepository;
import pl.krzysztofbujak.fitstat.repositories.UsersRepository;
import pl.krzysztofbujak.fitstat.services.DataServices;
import pl.krzysztofbujak.fitstat.services.LoginService;
import pl.krzysztofbujak.fitstat.services.UsersService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    LoginService loginService;

    @Autowired
    DataServices dataServices;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("allData", usersRepository.findAll());
        model.addAttribute("users", usersService);
        model.addAttribute("login",loginService);
        loginService.setLoginFail(false);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute @Valid LoginForm loginForm,
                            BindingResult bindingResult,
                            Model model){
        model.addAttribute("login", loginService);

        loginService.setLoginFail(false);
        if(bindingResult.hasErrors()){
            loginService.setLoginFail(true);
            return "login";
        }
        loginService.login(loginForm);


        if(loginService.isLogin()){
            return "redirect:/";
        }else{
            loginService.setLoginFail(true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(){

        loginService.setLogin(false);
        return "redirect:/";
    }
}
