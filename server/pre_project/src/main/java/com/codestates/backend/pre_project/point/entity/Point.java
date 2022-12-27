package com.codestates.backend.pre_project.point.entity;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.profile.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointId;

    @Column
    private int pointCount;

    @NotNull
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @NotNull
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    public Point( @NotNull Member member, Profile profile) {

        this.member = member;
        if (member.getPoint() != this) {
            member.setPoint(this);
        }

    }
}
