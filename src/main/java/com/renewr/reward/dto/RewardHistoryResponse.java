package com.renewr.reward.dto;

import com.renewr.member.domain.Member;
import com.renewr.reward.domain.RewardHistory;

public record RewardHistoryResponse (
    String sender,
    String receiver,
    String amount,
    String total,
    String createdAt
) {

    public static RewardHistoryResponse toResponseBySender(RewardHistory history) {
        return new RewardHistoryResponse(
                history.getSender().getName(),
                history.getReceiver().getName(),
                "-" + history.getAmount(),
                String.valueOf(history.getTotal()),
                history.getCreatedDate().toString()
        );
    }

    public static RewardHistoryResponse toResponseByReceiver(RewardHistory history) {
        return new RewardHistoryResponse(
                history.getSender().getName(),
                history.getReceiver().getName(),
                "+" + history.getAmount(),
                String.valueOf(history.getTotal()),
                history.getCreatedDate().toString()
        );
    }

}
