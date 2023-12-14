package com.renewr.reward.dto;

import java.util.List;

public record RewardResponse (
        int total,
        List<RewardHistoryResponse> responses
) {
}
