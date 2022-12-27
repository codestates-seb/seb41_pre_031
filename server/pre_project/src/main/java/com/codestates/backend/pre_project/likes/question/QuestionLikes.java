package com.codestates.backend.pre_project.likes.question;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionlikesId;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

    @ManyToOne
    @JoinColumn(name ="member")
    @NotNull
    private Member member;

    @Column
    private int count = 0;
}
