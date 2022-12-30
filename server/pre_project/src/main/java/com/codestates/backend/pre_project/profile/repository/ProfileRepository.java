package com.codestates.backend.pre_project.profile.repository;

import com.codestates.backend.pre_project.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
