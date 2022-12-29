package com.codestates.backend.pre_project.member.controller;

import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.mapper.MemberMapper;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.response.MultiResponseDto;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;

    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody){
        Member member = mapper.memberPostDtoToMember(requestBody);
        //member.setProfile(new Profile());
        //member.setPoint(new Point());

        Member createdMember = memberService.createMember(member);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(createdMember)),
                HttpStatus.CREATED);

    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberDto.Patch requestBody){

        requestBody.setMemberId(memberId);

        Member member = memberService.updateMember(mapper.memberPatchDtoToMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)),
                HttpStatus.OK);

    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponses(members),
                        pageMembers),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

