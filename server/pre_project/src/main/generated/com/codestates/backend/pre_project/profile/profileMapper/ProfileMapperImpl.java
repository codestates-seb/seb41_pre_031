package com.codestates.backend.pre_project.profile.profileMapper;

import com.codestates.backend.pre_project.profile.dto.ProfileDto;
import com.codestates.backend.pre_project.profile.entity.Profile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-26T21:44:41+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
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

        profileId = profile.getProfileId();
        profileTitle = profile.getProfileTitle();
        homepage = profile.getHomepage();
        location = profile.getLocation();
        about = profile.getAbout();
        profileView = profile.getProfileView();

        ProfileDto.Response response = new ProfileDto.Response( profileId, profileTitle, homepage, location, about, profileView );

        return response;
    }
}
