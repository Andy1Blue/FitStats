package pl.krzysztofbujak.fitstat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import pl.krzysztofbujak.fitstat.entities.DataEntity;
import pl.krzysztofbujak.fitstat.entities.UsersEntity;
import pl.krzysztofbujak.fitstat.repositories.UsersRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public List<UsersEntity> getUsers() {
        return usersRepository.findAll();
    }

    public int yearToAge(int year) {
        int actualYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = actualYear - year;
        return age;
    }

    public String numberToGender(int number) {
        String gender;
        if(number == 0){
            gender = "Mężczyzna";
        } else {
            gender = "Kobieta";
        }
        return gender;
    }

}
