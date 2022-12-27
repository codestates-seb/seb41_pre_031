package com.codestates.backend.pre_project.profile.controller;

import com.codestates.backend.pre_project.profile.dto.ProfileDto;
import com.codestates.backend.pre_project.profile.entity.Profile;
import com.codestates.backend.pre_project.profile.profileMapper.ProfileMapper;
import com.codestates.backend.pre_project.profile.service.ProfileService;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/profiles")
@Validated
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper mapper;

    public ProfileController(ProfileService profileService, ProfileMapper mapper) {
        this.profileService = profileService;
        this.mapper = mapper;
    }

    @PatchMapping("/{profile-id}")
    public ResponseEntity patchProfile(
            @PathVariable("profile-id") @Positive long profileId,
            @Valid @RequestBody ProfileDto.Patch requestBody){

        requestBody.setProfileId(profileId);

        Profile profile = profileService.updateProfile(mapper.profilePatchDtoToProfile(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.profileToprofileResponseDto(profile)),
                HttpStatus.OK);
    }

    @GetMapping("/{profile-id}")
    public ResponseEntity getProfile(
            @PathVariable("profile-id") @Positive long profileId) {
        Profile profile = profileService.findProfile(profileId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.profileToprofileResponseDto(profile)),
                HttpStatus.OK);
    }
}
