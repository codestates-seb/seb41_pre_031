package com.codestates.backend.pre_project.member.mapper;

import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.member.entity.Member;
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
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.email( requestBody.getEmail() );
        member.password( requestBody.getPassword() );
        member.memberName( requestBody.getMemberName() );

        return member.build();
    }

    @Override
    public Member memberPatchDtoToMember(MemberDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.memberId( requestBody.getMemberId() );
        member.password( requestBody.getPassword() );
        member.memberName( requestBody.getMemberName() );

        return member.build();
    }

    @Override
    public MemberDto.Response memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String email = null;
        String memberName = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        memberId = member.getMemberId();
        email = member.getEmail();
        memberName = member.getMemberName();
        createdAt = member.getCreatedAt();
        modifiedAt = member.getModifiedAt();

        MemberDto.Response response = new MemberDto.Response( memberId, email, memberName, createdAt, modifiedAt );

        return response;
    }

    @Override
    public List<MemberDto.Response> membersToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto.Response> list = new ArrayList<MemberDto.Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponseDto( member ) );
        }

        return list;
    }
}
