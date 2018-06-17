package pl.krzysztofbujak.fitstat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import pl.krzysztofbujak.fitstat.entities.DataEntity;
import pl.krzysztofbujak.fitstat.repositories.DataRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataServices {

    @Autowired
    LoginService loginService;

    @Autowired
    DataRepository dataRepository;

    public String countAllRecordByUserId() {
        List<DataEntity> list = dataRepository.countByUsers_IdOrderByIdDesc(loginService.getLoginId());
        String joined = StringUtils.join(list, "");
        return joined;
    }

    public static String convertTimestampToString(Timestamp timestamp){
        Date date = new Date();
        date.setTime(timestamp.getTime());
        String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
        return formattedDate;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double countBMI(double weight, double growth){
        double meters = growth / 100;
        double multiply = meters * meters;
        double bmi = weight / multiply;
        return round(bmi,2);
    }

    public static String checkBMI(double bmi) {
        String status = "Brak danych";
        if (bmi < 18.5) {
            status = "Niedowaga!";
        } else if (bmi > 18.5 && bmi < 25) {
            status = "Wynik jest prawidłowy";
        } else if (bmi > 25 && bmi < 30) {
            status = "Nadwaga!";
        } else if (bmi == 30 || bmi > 30) {
            status = "Otyłość!";
        }
        return status;
    }

    public static String checkFat(double fat, int age, int gender) {
        //TODO: tabela z https://www.festiwalbiegowy.pl/sites/default/files/Zrzut%20ekranu%202014-12-05%20o%2011.16.40.png
        String status = "Brak danych";
        return status;
    }

    public static String checkWater(){
        //TODO: tabela z https://1.bp.blogspot.com/-EHcXyqqd-lc/UaOBZoslfSI/AAAAAAAAGSM/a0D-BCuPtgQ/s1600/Zawarto%25C5%259B%25C4%2587+wody+w+zale%25C5%25BCno%25C5%259Bci+od+wieku.JPG
        String status = "Brak danych";
        return status;
    }

    public static String checMuscles(double muscles, int gender){
        //TODO: tabela z https://i1.wp.com/www.fitnesss.net/wp-content/uploads/2015/08/muscle-mass-percentage-chart.jpg
        String status = "Brak danych";
        if (gender == 0) {
            if(muscles < 33.40) {
                status = "Niska zawartość";
            } else if (muscles > 33.40 && muscles < 39.40) {
                status = "Normalna zawartość";
            } else if (muscles > 39.40 && muscles < 44.10) {
                status = "Wysoka zawartość";
        } else if (muscles > 44.10) {
                status = "Bardzo wysoka zawartość";
        }
        }

        if (gender == 1) {
            if(muscles < 24.40) {
                status = "Niska zawartość";
            } else if (muscles > 24.40 && muscles < 30.30) {
                status = "Normalna zawartość";
            } else if (muscles > 30.30 && muscles < 35.20) {
                status = "Wysoka zawartość";
            } else if (muscles > 35.20) {
                status = "Bardzo wysoka zawartość";
            }
        }


        return status;
    }

    public static String checkBones(){
        //TODO: tabela z https://tanita.eu/media/bone_mass.jpg
        String status = "Brak danych";
        return status;
    }

}
