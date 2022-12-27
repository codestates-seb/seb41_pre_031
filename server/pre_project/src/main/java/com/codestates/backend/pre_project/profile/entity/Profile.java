package com.codestates.backend.pre_project.profile.entity;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.point.entity.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;

    private String profileTitle;

    @Column(length = 50)
    private String homepage;

    @Column(length = 50)
    private String location;

    private String about;

    private long profileView;

    @OneToOne
    @JoinColumn(name ="MEMBER_ID")
    @NotNull
    private Member member;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private Point point;

}
