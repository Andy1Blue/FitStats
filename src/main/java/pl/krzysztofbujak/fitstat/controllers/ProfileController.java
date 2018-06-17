package pl.krzysztofbujak.fitstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.krzysztofbujak.fitstat.repositories.DataRepository;
import pl.krzysztofbujak.fitstat.repositories.UsersRepository;
import pl.krzysztofbujak.fitstat.services.DataServices;
import pl.krzysztofbujak.fitstat.services.LoginService;
import pl.krzysztofbujak.fitstat.services.UsersService;

@Controller
public class ProfileController {

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

    @GetMapping("/profile/{name}")
    public String showProfile(Model model, @PathVariable("name") String name){
        model.addAttribute("userId",usersRepository.findIdByLogin(name).getId());
        model.addAttribute("userName",usersRepository.findIdByLogin(name).getLogin());
        model.addAttribute("userCreationTime",usersRepository.findIdByLogin(name).getCreationTime());
        model.addAttribute("data", dataServices);
        model.addAttribute("users", usersService);
        model.addAttribute("login",loginService);
        return "profile";
    }

    @GetMapping("/profile/settings/{name}")
    public String userSettings(Model model, @PathVariable("name") String name){
        if (!loginService.isLogin()) {
            return "redirect:/login";
        } else {
            model.addAttribute("userId", usersRepository.findIdByLogin(name).getId());
            model.addAttribute("userName", usersRepository.findIdByLogin(name).getLogin());
            model.addAttribute("userGender", usersRepository.findIdByLogin(name).getGender());
            model.addAttribute("userGrowth", usersRepository.findIdByLogin(name).getGrowth());
            model.addAttribute("userYearOfBirth", usersRepository.findIdByLogin(name).getYearOfBirth());
            model.addAttribute("userStatus", usersRepository.findIdByLogin(name).getStatus());
            model.addAttribute("userCreationTime", usersRepository.findIdByLogin(name).getCreationTime());
            model.addAttribute("data", dataServices);
            model.addAttribute("users", usersService);
            model.addAttribute("login", loginService);
            return "user_settings";
        }
    }
}
