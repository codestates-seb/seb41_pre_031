package com.codestates.backend.pre_project.post.question;

import com.codestates.backend.pre_project.audit.Auditable;
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
public class QuestionTag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionTagId;


    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Column(nullable = false)
    private String tagName;

}
