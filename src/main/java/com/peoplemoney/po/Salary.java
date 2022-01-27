package com.peoplemoney.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Salary {
    private Integer SID;
    private Integer CID;
    private String CNAME;
    private Double SALARY;
    private String EMPEE_ACCT;
    private String EMPEE_ID;
    private String MONTH_ID;
    private String DAY_ID;
    private String MKT_AREA_ID;

    public Salary() {
    }

    public Salary(Integer SID, Integer CID, String CNAME, Double SALARY, String EMPEE_ACCT, String EMPEE_ID, String MONTH_ID, String DAY_ID, String MKT_AREA_ID) {
        this.SID = SID;
        this.CID = CID;
        this.CNAME = CNAME;
        this.SALARY = SALARY;
        this.EMPEE_ACCT = EMPEE_ACCT;
        this.EMPEE_ID = EMPEE_ID;
        this.MONTH_ID = MONTH_ID;
        this.DAY_ID = DAY_ID;
        this.MKT_AREA_ID = MKT_AREA_ID;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "SID=" + SID +
                ", CID=" + CID +
                ", CNAME='" + CNAME + '\'' +
                ", SALARY=" + SALARY +
                ", EMPEE_ACCT='" + EMPEE_ACCT + '\'' +
                ", EMPEE_ID='" + EMPEE_ID + '\'' +
                ", MONTH_ID='" + MONTH_ID + '\'' +
                ", DAY_ID='" + DAY_ID + '\'' +
                ", MKT_AREA_ID='" + MKT_AREA_ID + '\'' +
                '}';
    }
}
