package com.codestates.backend.pre_project.post.question;

import com.codestates.backend.pre_project.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionTagId;


    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Column(nullable = false)
    private String tagName;


//    public void setQuestion(Question question) {
//
//        if(question==null){
//            this.question = question;
//        }
//        for(int i =0; i <question.getQuestionTags().size(); i++){
//            this.setTagName(question.getQuestionTags().get(i).getTagName());
//        }
//
//    }
}
