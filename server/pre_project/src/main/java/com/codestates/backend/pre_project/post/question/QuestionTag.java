package com.codestates.backend.pre_project.post.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionTagId;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    public void setTag(Tag tag) {
        this.tag = tag;
        if(!this.tag.getQuestionsTags().contains(this))
            this.tag.getQuestionsTags().add(this);
    }

    public void setQuestion(Question question) {
        this.question = question;
        if(!this.question.getQuestionTags().contains(this))
            this.question.getQuestionTags().add(this);
    }
}
