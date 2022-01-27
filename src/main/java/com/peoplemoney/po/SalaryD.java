package com.peoplemoney.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryD {
    private String MONTH_ID;
    private String EMPEE_ACCT;
    private String EMPEE_ID;
    private String EMPEE_NAME;
    private Double AMONEY;
    private Double RMONEY;


    public SalaryD() {
    }

    public SalaryD(String MONTH_ID, String EMPEE_ACCT, String EMPEE_ID, String EMPEE_NAME, Double aMONEY, Double rMONEY) {
        this.MONTH_ID = MONTH_ID;
        this.EMPEE_ACCT = EMPEE_ACCT;
        this.EMPEE_ID = EMPEE_ID;
        this.EMPEE_NAME = EMPEE_NAME;
         this.AMONEY = aMONEY;
        this.RMONEY = rMONEY;
    }

    @Override
    public String toString() {
        return "SalaryD{" +
                "MONTH_ID='" + MONTH_ID + '\'' +
                ", EMPEE_ACCT='" + EMPEE_ACCT + '\'' +
                ", EMPEE_ID='" + EMPEE_ID + '\'' +
                ", EMPEE_NAME='" + EMPEE_NAME + '\'' +
                ", A_MONEY=" + AMONEY +
                ", R_MONEY=" + RMONEY +
                '}';
    }
}
