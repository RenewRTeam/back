package com.renewr.reward.service;

import com.renewr.global.common.BaseException;
import com.renewr.member.domain.Member;
import com.renewr.member.dto.Admin;
import com.renewr.member.exception.MemberErrorCode;
import com.renewr.member.service.MemberFindService;
import com.renewr.reward.domain.RewardHistory;
import com.renewr.reward.dto.RewardHistoryResponse;
import com.renewr.reward.dto.RewardOperation;
import com.renewr.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;

    private final MemberFindService memberFindService;

    public List<RewardHistoryResponse> getHistory(Long memId) {
        Member member = memberFindService.findByMemberId(memId);
        List<RewardHistoryResponse> responses = new ArrayList<>();

        member.getSenders().stream()
                .map(RewardHistoryResponse::toResponseBySender)
                .forEach(responses::add);

        member.getReceivers().stream()
                .map(RewardHistoryResponse::toResponseByReceiver)
                .forEach(responses::add);

        return responses;
    }

    public Long deposit(Long memId, int amount) {
        Member member = memberFindService.findByMemberId(memId);
        Member admin = memberFindService.findAdmin();

        member.updateReward(amount, RewardOperation.ADD);
        RewardHistory history = RewardHistory.builder()
                .sender(member)
                .receiver(admin)
                .amount(amount)
                .total(member.getReward())
                .build();

        rewardRepository.save(history);
        return memId;
    }

    public Long withdraw(Long memId, int amount) {
        Member member = memberFindService.findByMemberId(memId);
        Member admin = memberFindService.findAdmin();

        if (member.getReward() < amount) {
            throw BaseException.type(MemberErrorCode.INSUFFICIENT_BALANCE);
        }

        member.updateReward(amount, RewardOperation.SUBTRACT);
        RewardHistory history = RewardHistory.builder()
                .sender(admin)
                .receiver(member)
                .amount(amount)
                .total(member.getReward())
                .build();

        rewardRepository.save(history);
        return memId;
    }

    public Long transfer(Long senderId, Long receiverId, int amount) {
        Member sender = memberFindService.findByMemberId(senderId);
        Member receiver = memberFindService.findByMemberId(receiverId);

        if (sender.getReward() < amount) {
            throw BaseException.type(MemberErrorCode.INSUFFICIENT_BALANCE);
        }

        sender.updateReward(amount, RewardOperation.SUBTRACT);
        receiver.updateReward(amount, RewardOperation.ADD);

        RewardHistory senderHistory = RewardHistory.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .total(sender.getReward())
                .build();

        RewardHistory receiverHistory = RewardHistory.builder()
                .sender(receiver)
                .receiver(sender)
                .amount(amount)
                .total(receiver.getReward())
                .build();

        rewardRepository.save(senderHistory);
        rewardRepository.save(receiverHistory);

        return senderId;
    }

}
