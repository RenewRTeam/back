package com.renewr.reward.dto;

import com.renewr.member.domain.Member;
import com.renewr.reward.domain.RewardHistory;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public record RewardHistoryResponse (
    String sender,
    String receiver,
    String amount,
    String total,
    String createdAt
) {

    public static RewardHistoryResponse toResponseBySender(RewardHistory history) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = history.getCreatedDate().format(formatter);

        return new RewardHistoryResponse(
                history.getSender().getName(),
                history.getReceiver().getName(),
                "-" + history.getAmount(),
                String.valueOf(history.getTotal()),
                formattedDate
        );
    }

    public static RewardHistoryResponse toResponseByReceiver(RewardHistory history) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = history.getCreatedDate().format(formatter);

        return new RewardHistoryResponse(
                history.getSender().getName(),
                history.getReceiver().getName(),
                "+" + history.getAmount(),
                String.valueOf(history.getTotal()),
                formattedDate
        );
    }

}
