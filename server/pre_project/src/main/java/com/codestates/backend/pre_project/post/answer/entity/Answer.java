package com.codestates.backend.pre_project.post.answer.entity;

import com.codestates.backend.pre_project.audit.Auditable;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Answer extends Auditable {
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

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}