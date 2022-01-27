package com.peoplemoney.po;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class years {
    private String year;

    public years() {
    }

    public years(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "year{" +
                "year='" + year + '\'' +
                '}';
    }
}
