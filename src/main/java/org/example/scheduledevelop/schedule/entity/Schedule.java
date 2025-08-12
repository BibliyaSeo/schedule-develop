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

    @Column(name = "title", nullable = false, length = 10)
    private String title;

    @Column(name = "contents", nullable = false, length = 100)
    private String contents;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
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
