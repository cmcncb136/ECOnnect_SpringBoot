package com.econnect.econnect.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    @PostMapping("/find/")
    public MemberDto getMemberByUid(@RequestParam("uid") String uid) {
        return MemberDto.toDto(memberService.getMember(uid));
    }

    @PostMapping("/finds/")
    public List<MemberDto> getMembersByUid(@RequestParam("uid") List<String> uids) {
        List<MemberDto> members = new ArrayList<>();
        for(String uid : uids)
            members.add(MemberDto.toDto(memberService.getMember(uid)));

        return members;
    }

    @PostMapping("/update/")
    public Boolean updateMember(@RequestParam("uid") String uid, @RequestBody MemberDto dto) {
        if(!memberService.existsMember(uid)) return false;
        Member originalMember = memberService.getMember(uid);
        originalMember.setName(dto.getName());
        originalMember.setAddress(dto.getAddress());
        originalMember.setPhone(dto.getPhone());

        memberService.save(originalMember);
        return true;
    }

    @PostMapping("/join/")
    public boolean joinMember(@RequestBody MemberDto memberDto) {
        if(memberService.existsMember(memberDto.getUid())) return false;
        if(memberDto == null) return false;

        Member member = Member.toEntity(memberDto);
        member.setJoinDate(LocalDate.now());
        member.setPoint(0);

        memberService.save(member);
        return true;
    }



    @PostMapping("/exist/")
    public Boolean existMemberByUid(@RequestParam("uid") String uid) {
        System.out.println(uid);
        return memberService.existsMember(uid);
    }


}
