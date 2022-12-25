package com.codestates.backend.pre_project.profile.profileMapper;

import com.codestates.backend.pre_project.profile.dto.ProfileDto;
import com.codestates.backend.pre_project.profile.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
    Profile profilePatchDtoToProfile(ProfileDto.Patch patch);
    ProfileDto.Response profileToprofileResponseDto(Profile profile);
}
