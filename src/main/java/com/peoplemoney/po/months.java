package com.peoplemoney.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class months {
    private String month;

    public months() {
    }

    public months(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "months{" +
                "month='" + month + '\'' +
                '}';
    }
}
