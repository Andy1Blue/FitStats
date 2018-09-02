package pl.krzysztofbujak.fitstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.krzysztofbujak.fitstat.forms.DataForm;
import pl.krzysztofbujak.fitstat.repositories.DataRepository;
import pl.krzysztofbujak.fitstat.repositories.UsersRepository;
import pl.krzysztofbujak.fitstat.services.DataServices;
import pl.krzysztofbujak.fitstat.services.LoginService;
import pl.krzysztofbujak.fitstat.services.UsersService;

@Controller
public class StatsController {

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

    @GetMapping("/stats")
    public String stats(Model model) {
        if (!loginService.isLogin()) {
            return "redirect:/login";
        } else {
            model.addAttribute("dataForm", new DataForm());
            model.addAttribute("allData", dataRepository.findAllByOrderByIdDesc());
            model.addAttribute("users", usersService.getUsers());
            model.addAttribute("login", loginService);
            model.addAttribute("data", dataServices);
            model.addAttribute("dataLoginUser", dataRepository.findByUsers_IdOrderByAddingDateDesc(loginService.getLoginId()));
            model.addAttribute("dataLoginUserOnlyLast", dataRepository.findByUsers_IdOrderByIdDesc(loginService.getLoginId()).get(0));
            model.addAttribute("countAllRecordByUserId", dataRepository.countByUsers_IdOrderByIdDesc(loginService.getLoginId()));
            model.addAttribute("lastAddedRecordTime", dataRepository.findByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("theBiggestWeightRecord", dataRepository.findByUsers_IdOrderByWeightDesc(loginService.getLoginId()).subList(0, 1));

        }
        return "stats";
    }

    @GetMapping("/stats/summary")
    public String summary(Model model){
        if (!loginService.isLogin()) {
            return "redirect:/login";
        } else {
            model.addAttribute("dataForm", new DataForm());
            model.addAttribute("allData", dataRepository.findAllByOrderByIdDesc());
            model.addAttribute("users", usersService.getUsers());
            model.addAttribute("login", loginService);
            model.addAttribute("data", dataServices);
            model.addAttribute("dataLoginUser", dataRepository.findByUsers_IdOrderByAddingDateDesc(loginService.getLoginId()));
            model.addAttribute("countAllRecordByUserId", dataRepository.countByUsers_IdOrderByIdDesc(loginService.getLoginId()));
            model.addAttribute("lastAddedRecordTime", dataRepository.findByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("theBiggestWeightRecord", dataRepository.findByUsers_IdOrderByWeightDesc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("theSmallestWeightRecord", dataRepository.findByUsers_IdOrderByWeightAsc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("firstAddedRecordTime", dataRepository.findByUsers_IdOrderByIdAsc(loginService.getLoginId()).subList(0, 1));
            return "summary";
        }
    }

    @GetMapping("/stats/indicators")
    public String userSettings(Model model){
        if (!loginService.isLogin()) {
            return "redirect:/login";
        } else {
            model.addAttribute("userGender", usersRepository.findLoginById(loginService.getLoginId()).getGender());
            model.addAttribute("userYearOfBirth", usersRepository.findLoginById(loginService.getLoginId()).getYearOfBirth());
            model.addAttribute("data", dataServices);
            model.addAttribute("users", usersService);
            model.addAttribute("login", loginService);
            model.addAttribute("lastWeight", dataRepository.findWeightByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("userGrowth", usersRepository.findGrowthById(loginService.getLoginId()).getGrowth());
            model.addAttribute("lastFat", dataRepository.findFatByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("lastWater", dataRepository.findWaterByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("lastMuscles", dataRepository.findMusclesByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0, 1));
            model.addAttribute("lastBones", dataRepository.findBonesByUsers_IdOrderByIdDesc(loginService.getLoginId()).subList(0, 1));
            return "indicators";
        }
    }

    @GetMapping("/stats/weight")
    public String weight(Model model){
        if (!loginService.isLogin()) {
            return "redirect:/login";
        } else {
              return "weight";
        }
    }

}
