package com.fc.pass.controller.admin;

import com.fc.pass.util.LocalDateTimeUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BulkPassRequest {
    private Integer packageSeq;
    private String userGroupId;
    private LocalDateTime startedAt;

    public void setStartedAt(String startedAtString) {
        this.startedAt = LocalDateTimeUtils.parse(startedAtString);
    }
}
