package com.codestates.backend.pre_project.likes.answer;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnswerLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerlikesId;

    @ManyToOne
    @JoinColumn(name = "answer")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name ="member")
    @NotNull
    private Member member;

    @Column
    private int count = 0;
}