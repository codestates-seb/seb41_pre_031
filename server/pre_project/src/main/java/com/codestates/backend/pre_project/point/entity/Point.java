package com.codestates.backend.pre_project.point.entity;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.profile.Profile;
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
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointId;

    @Column
    private long pointCount;

    @NotNull
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @NotNull
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    public Point(Profile profile) {
        this.profile = profile;
        if (profile.getPoint() != this) {
            profile.setPoint(this);
        }
    }

    public Point(Member member) {
        this.member = member;
        if (member.getPoint() != this) {
            member.setPoint(this);
        }
    }
}
