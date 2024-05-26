package com.econnect.econnect.member;

import com.econnect.econnect.carbon_footprint_calculation.CarbonFootprintCalculationResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMember(String uid) {
        Optional<Member> member = memberRepository.findById(uid);
        if(member.isPresent()) {
            return member.get();
        }else{
            return null;
        }
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public boolean existsMember(String uid) {
        return memberRepository.existsById(uid);
    }
}
