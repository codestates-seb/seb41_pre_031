package com.codestates.backend.pre_project.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProfileDto {
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long profileId;
        private String profileTitle;
        private String homepage;
        private String location;
        private String about;
        private long profileView;

        public void setProfileId(long profileId) {this.profileId = profileId;}
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long profileId;
        private String profileTitle;
        private String homepage;
        private String location;
        private String about;
        private long profileView;
    }
}
