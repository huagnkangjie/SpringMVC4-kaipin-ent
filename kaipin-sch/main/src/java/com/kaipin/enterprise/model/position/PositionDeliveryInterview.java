package com.kaipin.enterprise.model.position;

public class PositionDeliveryInterview {
    private String positionDeliveryId;

    private String interviewId;

    private String offerId;

    private String examInviteId;

    public String getPositionDeliveryId() {
        return positionDeliveryId;
    }

    public void setPositionDeliveryId(String positionDeliveryId) {
        this.positionDeliveryId = positionDeliveryId == null ? null : positionDeliveryId.trim();
    }

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId == null ? null : interviewId.trim();
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId == null ? null : offerId.trim();
    }

    public String getExamInviteId() {
        return examInviteId;
    }

    public void setExamInviteId(String examInviteId) {
        this.examInviteId = examInviteId == null ? null : examInviteId.trim();
    }
}