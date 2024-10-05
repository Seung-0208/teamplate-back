package com.cnu.teamProj.teamProj.member.controller;

import com.cnu.teamProj.teamProj.member.dto.ProjMemDto;
import com.cnu.teamProj.teamProj.member.repository.MemberRepository;
import com.cnu.teamProj.teamProj.member.service.MemberServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teamProj/member")
@ApiOperation(value="멤버 관리", notes = "멤버 조회와 관련된 코드")
public class MemberController {

    private MemberServiceImpl memberService;
    @Autowired
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    //특정 프로젝트 선택 시 해당 프로젝트의 팀원 불러오는 api
    @GetMapping("/project")
    public List<ProjMemDto> getProjMem(@RequestParam Map map) {
        return memberService.findProjMemByProjID(String.valueOf(map.get("projId")));
    }
}
