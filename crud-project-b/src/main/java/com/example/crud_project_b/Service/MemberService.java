package com.example.crud_project_b.Service;

import com.example.crud_project_b.Repository.MemberRepository;
import com.example.crud_project_b.domain.Member;
import com.example.crud_project_b.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(MemberDto dto) {
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        memberRepository.save(member);
    }
}
