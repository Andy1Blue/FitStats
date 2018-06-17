package pl.krzysztofbujak.fitstat.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.krzysztofbujak.fitstat.entities.UsersEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DataForm {
    private int id;

    private UsersEntity users;

    private LocalDateTime creationDate;

    private String comment;

    private double hand;

    private double waist;

    private double belly;

    private double bottom;

    private double thigh;

    private double calf;

    private double weight;

    private double fat;

    private double water;

    private double muscles;

    private double bones;

    private double caloric;
}
