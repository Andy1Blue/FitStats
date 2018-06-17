package pl.krzysztofbujak.fitstat.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.krzysztofbujak.fitstat.forms.DataForm;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "fitstats_data")
@Getter
@Setter
@NoArgsConstructor
public class DataEntity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UsersEntity users;

    @Column(name = "creation_date")
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

    public DataEntity(DataForm dataForm) {
        setId(dataForm.getId());
        setUsers(dataForm.getUsers());
        setCreationDate(dataForm.getCreationDate());
        setComment(dataForm.getComment());
        setHand(dataForm.getHand());
        setWaist(dataForm.getWaist());
        setBelly(dataForm.getBelly());
        setBottom(dataForm.getBottom());
        setThigh(dataForm.getThigh());
        setCalf(dataForm.getCalf());
        setWeight(dataForm.getWeight());
        setFat(dataForm.getFat());
        setWater(dataForm.getWater());
        setMuscles(dataForm.getMuscles());
        setBones(dataForm.getBones());
        setCaloric(dataForm.getCaloric());
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "id=" + id +
                ", users=" + users +
                ", creationDate=" + creationDate +
                ", comment=" + comment +
                ", hand=" + hand +
                ", waist=" + waist +
                ", belly=" + belly +
                ", bottom=" + bottom +
                ", thigh=" + thigh +
                ", calf=" + calf +
                ", weight=" + weight +
                ", fat=" + fat +
                ", water=" + water +
                ", muscles=" + muscles +
                ", bones=" + bones +
                ", caloric=" + caloric +
                '}';
    }
}
