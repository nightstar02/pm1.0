package com.peoplemoney.po;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
    private String EMPEE_ID;//用户名字
    private String EMPEE_ACCT;//工号
    private String EMPEE_NAME ;//工号id
    private String MKT_AREA_NAME;//三级
    private String DEPT_NAME;//工号
    private String MKT_AREA_ID;//工号
    private Integer QX;//工号
    private String PASSWORD;//工号

    public User() {
    }



    public User(String EMPEE_ID, String EMPEE_ACCT, String EMPEE_NAME, String MKT_AREA_NAME, String DEPT_NAME, String MKT_AREA_ID, Integer QX, String PASSWORD) {
        this.EMPEE_ID = EMPEE_ID;
        this.EMPEE_ACCT = EMPEE_ACCT;
        this.EMPEE_NAME = EMPEE_NAME;
        this.MKT_AREA_NAME = MKT_AREA_NAME;
        this.DEPT_NAME = DEPT_NAME;
        this.MKT_AREA_ID = MKT_AREA_ID;
        this.QX = QX;
        this.PASSWORD = PASSWORD;
    }

    @Override
    public String toString() {
        return "USER{" +
                "EMPEE_ID='" + EMPEE_ID + '\'' +
                ", EMPEE_ACCT='" + EMPEE_ACCT + '\'' +
                ", EMPEE_NAME='" + EMPEE_NAME + '\'' +
                ", MKT_AREA_NAME='" + MKT_AREA_NAME + '\'' +
                ", DEPT_NAME='" + DEPT_NAME + '\'' +
                ", DEPT_ID='" + MKT_AREA_ID + '\'' +
                ", QX=" + QX +
                ", PASSWORD='" + PASSWORD + '\'' +
                '}';
    }
}
