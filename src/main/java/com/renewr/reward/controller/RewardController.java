package com.renewr.reward.controller;

import com.renewr.global.annotation.CurrentUser;
import com.renewr.global.common.BaseResponse;
import com.renewr.member.dto.Admin;
import com.renewr.member.service.MemberFindService;
import com.renewr.reward.dto.RewardHistoryResponse;
import com.renewr.reward.dto.RewardResponse;
import com.renewr.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reward")
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/history")
    public BaseResponse<RewardResponse> getHistory(@CurrentUser Long memId) {
        return new BaseResponse<>(rewardService.getHistory(memId));
    }

    @PostMapping("/deposit")
    public BaseResponse<Long> deposit(@CurrentUser Long id, @RequestParam int amount) {
        return new BaseResponse<>(rewardService.deposit(id, amount));
    }

    @PostMapping("/withdraw")
    public BaseResponse<Long> withdraw(@CurrentUser Long id, @RequestParam int amount) throws Exception {
        return new BaseResponse<>(rewardService.withdraw(id, amount));
    }

    @PostMapping("/transfer")
    public BaseResponse<Long> transfer(@CurrentUser Long id, @RequestParam int amount) {
        return new BaseResponse<>(rewardService.transfer(id, 1L, amount));
    }

}
