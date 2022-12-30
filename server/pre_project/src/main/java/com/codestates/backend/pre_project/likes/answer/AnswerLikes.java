package com.codestates.backend.pre_project.likes.answer;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import lombok.*;
import org.springframework.lang.Nullable;

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
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name ="MEMBER_ID")
    @NotNull
    private Member member;

    @Column
    @Nullable
    private Boolean isClicked;
}
