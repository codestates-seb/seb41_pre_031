package com.codestates.backend.pre_project.post.comment.entity;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(length = 500)
    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //private long answerId;

    //private long questionId;

    @CreatedDate
    private LocalDateTime commentRegDate;

    @ManyToOne
    @JoinTable(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinTable(name = "ANSWER_ID")
    private Answer answer;

    public Comment(long commentId, String commentBody) {
        this.commentId = commentId;
        this.commentBody = commentBody;
    }


}
