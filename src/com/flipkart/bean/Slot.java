package com.flipkart.bean;

import java.util.Date;

public class Slot {

    private String slotId;
    private String centreId;
    private Date time;

    public Slot(String slotId, String centreId, Date time) {
        this.slotId = slotId;
        this.centreId = centreId;
        this.time = time;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getCentreId() {
        return centreId;
    }

    public void setCentreId(String centreId) {
        this.centreId = centreId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
