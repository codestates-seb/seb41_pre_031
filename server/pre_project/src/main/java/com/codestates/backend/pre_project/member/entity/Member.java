package com.codestates.backend.pre_project.member.entity;

import com.codestates.backend.pre_project.likes.entity.Likes;
import com.codestates.backend.pre_project.point.entity.Point;
import com.codestates.backend.pre_project.post.answer.Answer;
import com.codestates.backend.pre_project.post.comment.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.profile.Profile;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long memberId;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 30)
    private String memberName;

    @CreatedDate
    private LocalDateTime memberRegDate;

    @LastModifiedDate
    private LocalDateTime memberLastDate;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Profile profile = new Profile(this);

//    public void setProfile(Profile profile) {
//        this.profile = profile;
//    }

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Likes likes = new Likes(this);

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Point point = new Point(this);

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();
}