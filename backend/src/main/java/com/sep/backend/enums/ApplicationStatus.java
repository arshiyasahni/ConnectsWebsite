package com.sep.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public enum ApplicationStatus
{
    //status 0 -> applied by candidate
    //status 1 -> rejected by employer
    //status 2 -> interview with employer
    //status 3 -> accepted by employer

    APPLIED_BY_CANDIDATE(0),
    REJECTED_BY_EMPLOYER(1),
    INTERVIEW_WITH_EMPLOYER(2),
    ACCEPTED_BY_EMPLOYER(3);

    private int status;
    ApplicationStatus(int value) {
        this.status = value;
    }

    public int getValue() {
        return status;
    }
    public static ApplicationStatus getByValue(int value) {
        for (ApplicationStatus status : ApplicationStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null; // If the value doesn't match any enum constant.
    }
}
