package com.codestates.backend.pre_project.post.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    private String tagName;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<QuestionTag> questionsTags;

    @PrePersist
    public void prePersist() {
        for(int i=0; i< this.questionsTags.size(); i++){
            if(this.questionsTags.get(i).getTag()==null){
                this.questionsTags.get(i).setTag(this);
            }
        }
    }
}
