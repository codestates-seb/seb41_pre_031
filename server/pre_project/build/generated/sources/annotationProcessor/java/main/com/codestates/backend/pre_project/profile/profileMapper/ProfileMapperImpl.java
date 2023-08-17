package com.codestates.backend.pre_project.profile.profileMapper;

import com.codestates.backend.pre_project.profile.dto.ProfileDto;
import com.codestates.backend.pre_project.profile.entity.Profile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-02T10:18:54+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public Profile profilePatchDtoToProfile(ProfileDto.Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Profile.ProfileBuilder profile = Profile.builder();

        profile.profileId( patch.getProfileId() );
        profile.profileTitle( patch.getProfileTitle() );
        profile.homepage( patch.getHomepage() );
        profile.location( patch.getLocation() );
        profile.about( patch.getAbout() );
        profile.profileView( patch.getProfileView() );

        return profile.build();
    }

    @Override
    public ProfileDto.Response profileToprofileResponseDto(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        long profileId = 0L;
        String profileTitle = null;
        String homepage = null;
        String location = null;
        String about = null;
        long profileView = 0L;
        int point = 0;
        LocalDateTime createdAt = null;

        profileId = profile.getProfileId();
        profileTitle = profile.getProfileTitle();
        homepage = profile.getHomepage();
        location = profile.getLocation();
        about = profile.getAbout();
        profileView = profile.getProfileView();
        point = profile.getPoint();
        createdAt = profile.getCreatedAt();

        ProfileDto.Response response = new ProfileDto.Response( profileId, profileTitle, homepage, location, about, profileView, point, createdAt );

        return response;
    }

    @Override
    public List<ProfileDto.Response> profileToprofilesResponseDtos(List<Profile> profile) {
        if ( profile == null ) {
            return null;
        }

        List<ProfileDto.Response> list = new ArrayList<ProfileDto.Response>( profile.size() );
        for ( Profile profile1 : profile ) {
            list.add( profileToprofileResponseDto( profile1 ) );
        }

        return list;
    }
}
