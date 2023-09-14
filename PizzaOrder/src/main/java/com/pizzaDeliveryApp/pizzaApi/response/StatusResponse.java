package com.pizzaDeliveryApp.pizzaApi.response;

import com.pizzaDeliveryApp.pizzaApi.constant.Constants;

public class StatusResponse {

    private int status;

    private String message;

    private Object response= Constants.Errors.RESPONSE_MESSAGE;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }
}
