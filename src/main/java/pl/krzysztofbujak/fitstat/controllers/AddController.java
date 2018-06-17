package pl.krzysztofbujak.fitstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.krzysztofbujak.fitstat.entities.DataEntity;
import pl.krzysztofbujak.fitstat.forms.DataForm;
import pl.krzysztofbujak.fitstat.repositories.DataRepository;
import pl.krzysztofbujak.fitstat.repositories.UsersRepository;
import pl.krzysztofbujak.fitstat.services.DataServices;
import pl.krzysztofbujak.fitstat.services.LoginService;
import pl.krzysztofbujak.fitstat.services.UsersService;

@Controller
public class AddController {

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

    @GetMapping("/add")
    public String add(Model model){
        if (!loginService.isLogin()) {
            return "redirect:/login";
        } else {
            model.addAttribute("dataForm", new DataForm());
            model.addAttribute("allData", dataRepository.findAllByOrderByIdDesc());
            model.addAttribute("users", usersService.getUsers());
            model.addAttribute("login", loginService);
            model.addAttribute("dataLoginUser", dataRepository.findByUsers_IdOrderByIdDesc(loginService.getLoginId()));
            model.addAttribute("userWhoLoginNow", usersRepository.findById(loginService.getLoginId()));
            return "add_data";
        }
    }

    @PostMapping("/add")
    public String add(@ModelAttribute DataForm dataForm){
        DataEntity dataEntity = new DataEntity(dataForm);
        dataRepository.save(dataEntity);
        return "redirect:/stats";
    }
}
