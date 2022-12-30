package com.codestates.backend.pre_project.post.answer.entity;

import com.codestates.backend.pre_project.likes.answer.AnswerLikes;
import com.codestates.backend.pre_project.likes.question.QuestionLikes;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @Column(nullable = false, length = 500)
    private String answerBody;


    @Column(nullable = false)
    private int answerLikes;

    @Column
    @Nullable
    private Boolean answerSelected;

    @CreatedDate
    private LocalDateTime answerRegDate;

    @LastModifiedDate
    private LocalDateTime answerLastDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

//    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
//    private List<answerLikes> answerLikesList;
//        //= new ArrayList<>();

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<Comment> comments;// = new ArrayList<>();

//  @Column(nullable = false)
//  private long memberId;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

//    public void setAnswerLikes(List<AnswerLikes> answerlikes) {
//        this.answerlikes = answerlikes;
//    }
//
//    public void setQuestionLikes(List<QuestionLikes> likes) {
//        this.likes = likes;
//    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}