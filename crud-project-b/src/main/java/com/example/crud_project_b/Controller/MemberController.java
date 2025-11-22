package com.example.crud_project_b.Controller;

import com.example.crud_project_b.Service.MemberService;
import com.example.crud_project_b.domain.Member;
import com.example.crud_project_b.dto.MemberDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public String join(@RequestBody @Valid MemberDto dto) {
        memberService.join(dto);
        return "success";
    }




}
