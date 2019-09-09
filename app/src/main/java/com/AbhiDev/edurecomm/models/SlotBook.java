
package com.AbhiDev.edurecomm.models;

import com.wireout.models.Slot;

import java.io.Serializable;

public class SlotBook implements Serializable
{

    private Integer id;
    private String callSlot;
    private String mode;
    private String contact;
    private Integer owner;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCallSlot() {
        return callSlot;
    }

    public void setCallSlot(String callSlot) {
        this.callSlot = callSlot;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

}
