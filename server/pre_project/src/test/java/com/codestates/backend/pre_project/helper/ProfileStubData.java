package com.codestates.backend.pre_project.helper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.point.entity.Point;
import com.codestates.backend.pre_project.profile.dto.ProfileDto;
import com.codestates.backend.pre_project.profile.entity.Profile;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProfileStubData {
    private static Map<HttpMethod, Object> stubRequestBody;
    static{
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.PATCH, new ProfileDto.Patch(
                1,"나는 슈퍼신찬맨","sc.io","Korea",
                "만나서 반갑습니다. 개발자로 성공합시다.",1
        ));
    }

    public static class MockProfile {
        public static Object getRequestBody(HttpMethod method) {return stubRequestBody.get(method);}

        public static ProfileDto.Response getSingleResponseBody() {
            return new ProfileDto.Response(
                    1,"나는 슈퍼신찬맨","sc.io","Korea",
                    "만나서 반갑습니다. 개발자로 성공합시다.",1
            );
        }

        public static ProfileDto.Response getSingleResponseBody(long profileId,
                                                                String profileTitle,
                                                                String homepage,
                                                                String location,
                                                                String profileAbout,
                                                                long profileView ) {
            long optionalprofileId = Optional.ofNullable(profileId).orElse(1L);
            String optionalprofileTitle = Optional.ofNullable(profileTitle).orElse("나는 슈퍼신찬맨");
            String optionalhomepage = Optional.ofNullable(homepage).orElse("sc.io");
            String optionallocation = Optional.ofNullable(location).orElse("Korea");
            String optionalprofileAbout = Optional.ofNullable(profileAbout).orElse("만나서 반갑습니다. 개발자로 성공합시다.");
            long optionalprofileView = Optional.ofNullable(profileView).orElse(1L);
            return new ProfileDto.Response(optionalprofileId,
                    optionalprofileTitle,
                    optionalhomepage,
                    optionallocation,optionalprofileAbout,optionalprofileView);
        }

        public static Profile getSingleResultProfile(long prfoileId) {
            Profile profile = new Profile(1,"나는 슈퍼신찬맨","sc.io","Korea",
                    "만나서 반갑습니다. 개발자로 성공합시다.",1,new Member(),new Point());
            profile.setProfileId(prfoileId);

            return profile;
        }
    }
}
