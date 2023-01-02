package com.codestates.backend.pre_project.profile.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.profile.entity.Profile;
import com.codestates.backend.pre_project.profile.repository.ProfileRepository;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final CustomBeanUtils beanUtils;

    private final MemberService memberService;

    public ProfileService(ProfileRepository profileRepository,
                          MemberService memberService,
                          CustomBeanUtils<Profile> beanUtils) {
        this.profileRepository = profileRepository;
        this.memberService = memberService;
        this.beanUtils = beanUtils;
    }

    public Profile updateProfile(Profile profile) {
        Profile findProfile = findVerifiedProfile(profile.getProfileId());
        Member postMember = memberService.findVerifiedMember(findProfile.getMember().getMemberId());
        if (memberService.getCurrentMember().getMemberId() != postMember.getMemberId())
            throw new BusinessLogicException(ExceptionCode.EDIT_NOT_ALLOWED);

        Profile updateProfile = (Profile) beanUtils.copyNonNullProperties(profile,findProfile);

        return profileRepository.save(updateProfile);
    }

    public Profile updatePoint(Profile profile) {
        Profile findProfile = findVerifiedProfile(profile.getProfileId());
        Member postMember = memberService.findVerifiedMember(findProfile.getMember().getMemberId());

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

    public List<Profile> findProfiles() {
        return profileRepository.findAll();
    }

}
