package com.AbhiDev.edurecomm.apiservices;

import com.wireout.apiservices.responses.GenericResponse;

public class ResetPasswordResponse extends GenericResponse {
    String  status;
    String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
