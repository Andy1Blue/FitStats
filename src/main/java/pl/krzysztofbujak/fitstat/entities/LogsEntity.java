package pl.krzysztofbujak.fitstat.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fitstats_logs")
@Getter
@Setter
@NoArgsConstructor
public class LogsEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "add_date")
    private String addingDate;

    private String ip;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private UsersEntity users;

    public LogsEntity(String addingDate, String ip, UsersEntity users) {
        this.addingDate = addingDate;
        this.ip = ip;
        this.users = users;
    }
}
