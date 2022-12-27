package com.codestates.backend.pre_project.profile.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.profile.entity.Profile;
import com.codestates.backend.pre_project.profile.repository.ProfileRepository;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final CustomBeanUtils beanUtils;

    public ProfileService(ProfileRepository profileRepository, CustomBeanUtils<Profile> beanUtils) {
        this.profileRepository = profileRepository;
        this.beanUtils = beanUtils;
    }

    // 멤버 생성하면 자동으로 프로필은 생성되는듯
//    public Profile createProfile(Profile profile) {
//        verifyExistsMemberId(profile.getMember().getMemberId());
//        return profileRepository.save(profile);
//    }

    public Profile updateProfile(Profile profile) {
        Profile findProfile = findVerifiedProfile(profile.getProfileId());
        Profile updateProfile = (Profile) beanUtils.copyNonNullProperties(profile,findProfile);

        return profileRepository.save(updateProfile);
    }

    public Profile findVerifiedProfile(long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        Profile findProfile = optionalProfile.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.PROFILE_NOT_FOUND));
        return findProfile;
    }

    public Profile findProfile(long profileId) {return findVerifiedProfile(profileId);}

    public Page<Profile> findProfiles(int page, int size) {
        return profileRepository.findAll(PageRequest.of(page,size, Sort.by("profileId").descending()));
    }

//    private void verifyExistsMemberId(long memberId) {
//        Optional<Profile> profile = profileRepository.findById(memberId);
//        if(profile.isPresent())
//            throw new BusinessLogicException(ExceptionCode.PROFILE_EXISTS);
//    }
}
