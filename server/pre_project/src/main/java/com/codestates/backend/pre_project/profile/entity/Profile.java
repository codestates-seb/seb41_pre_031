package com.codestates.backend.pre_project.profile.entity;

import com.codestates.backend.pre_project.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;

    @Nullable
    private String profileTitle;

    @Column(length = 50)
    @Nullable
    private String homepage;

    @Column(length = 50)
    @Nullable
    private String location;

    @Column
    @Nullable
    private String about;

    @Nullable
    private long profileView;

    @CreatedDate
    private LocalDateTime profileRegDate;

    @OneToOne
    @JoinColumn(name ="MEMBER_ID")
    @NotNull
    private Member member;

    @Column
    @NotNull
    private int point;

}
