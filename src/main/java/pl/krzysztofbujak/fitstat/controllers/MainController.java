package pl.krzysztofbujak.fitstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.krzysztofbujak.fitstat.entities.DataEntity;
import pl.krzysztofbujak.fitstat.entities.UsersEntity;
import pl.krzysztofbujak.fitstat.forms.DataForm;
import pl.krzysztofbujak.fitstat.forms.LoginForm;
import pl.krzysztofbujak.fitstat.repositories.DataRepository;
import pl.krzysztofbujak.fitstat.repositories.UsersRepository;
import pl.krzysztofbujak.fitstat.services.DataServices;
import pl.krzysztofbujak.fitstat.services.LoginService;
import pl.krzysztofbujak.fitstat.services.UsersService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

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

    @GetMapping("/")
    public String index(Model model){
        if(!loginService.isLogin()){
            return "redirect:/login";
        } else {
            model.addAttribute("users", usersService.getUsers());
            model.addAttribute("login",loginService);
            model.addAttribute("data", dataServices);
            model.addAttribute("countAllRecordByUserId", dataRepository.countByUsers_IdOrderByIdDesc(loginService.getLoginId()));
            model.addAttribute("lastAddedRecordTime", dataRepository.findByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0,1));
            model.addAttribute("theBiggestWeightRecord", dataRepository.findByUsers_IdOrderByWeightDesc(loginService.getLoginId()).subList(0,1));
            return "index";
        }
    }




}
