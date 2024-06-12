package com.econnect.econnect.admin;


import com.econnect.econnect.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/exist/")
    public Boolean adminCheck(@RequestParam("adminUid") String uid){
        return adminService.existAdmin(uid);
    }

    @PostMapping("/challenge_state/try_list/")
    public List<ChallengeStateFullAdminDto> tryList(@RequestParam("adminUid") String uid){
        if(!adminService.existAdmin(uid)) return null;        System.out.println("adminUid : " + uid);
        System.out.println("adminUid : " + uid);

        return adminService.findTryMemberChallengeState();
    }

    @PostMapping("/challenge_state/admin_check/")
    public CommonResult adminCheck(@RequestParam("adminUid") String uid,
                                    @RequestParam("challengeStateId") Integer challengeStateId,
                                    @RequestParam("checkId") Integer checkId){
        if(!adminService.existAdmin(uid)) return null;
        return adminService.adminCheck(challengeStateId, checkId);

    }
}
