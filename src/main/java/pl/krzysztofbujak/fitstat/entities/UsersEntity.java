package pl.krzysztofbujak.fitstat.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "fitstats_users")
@Getter
@Setter
@NoArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue
    private int id;

    private String login;

    private String password;

    private String status;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    //0 - man, 1 - woman
    private int gender;

    private double growth;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DataEntity> data;
}
