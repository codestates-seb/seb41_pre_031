package com.codestates.backend.pre_project.post.question;

import com.codestates.backend.pre_project.likes.entity.Likes;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false, length = 30)
    private String questionTitle;

    @Column(nullable = false, length = 1000)
    private String questionBody;

    @Column(nullable = false)
    private long questionView;

    @Column(nullable = false)
    private Long answerNum; //게시판 답변 수

    @Column(nullable = false)
    private Long questionLikes; //게시판 좋아요 수

    @CreatedDate
    private LocalDateTime questionRegDate;

    @LastModifiedDate
    private LocalDateTime questionLastDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Comment> comments;
            //= new ArrayList<>();
    //TODO

    @OneToMany(mappedBy = "question")
    private List<Likes> likes;
            //= new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionTag> questionTags;
            //= new LinkedList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
    //= new ArrayList<>();
//    @Column(nullable = false)
//    private long memberId;

    @PrePersist
    public void prePersist() {
        this.questionLikes = (this.questionLikes == null ? 0 : this.questionLikes);
        this.answerNum = (this.answerNum == null ? 0 : this.answerNum);

        for(int i=0; i< this.questionTags.size(); i++){
            if(this.questionTags.get(i).getQuestion()==null){
                this.questionTags.get(i).setQuestion(this);
            }
        }
    }

    public void setMember(Member member) {
        this.member = member;
        if(!this.member.getQuestions().contains(this)){
            this.member.getQuestions().add(this);
        }
    }

}
