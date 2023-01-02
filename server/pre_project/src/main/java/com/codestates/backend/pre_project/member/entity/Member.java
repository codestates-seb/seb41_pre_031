package com.codestates.backend.pre_project.member.entity;

import com.codestates.backend.pre_project.audit.Auditable;
import com.codestates.backend.pre_project.likes.answer.AnswerLikes;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.profile.entity.Profile;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long memberId;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String memberName;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<AnswerLikes> likeMembers;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Member(long memberId, String email, String password, String memberName) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


}