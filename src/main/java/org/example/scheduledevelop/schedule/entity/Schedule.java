package org.example.scheduledevelop.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.scheduledevelop.common.entity.BaseEntity;
import org.example.scheduledevelop.users.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public void updateSchedule(String title, String contents) {
        if (title != null) this.title = title;
        if (contents != null) this.contents = contents;

    }
}
