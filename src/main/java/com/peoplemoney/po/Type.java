package com.peoplemoney.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Type {
    private Integer CID;//种类
    private String CNAME;//名字
    private String UPTIME;
    private String MESSAGE;//备注
    private String UPTOR;//更新人
    private String typeType;


    public Type() {
    }

    public Type(Integer CID, String CNAME, String UPTIME, String MESSAGE,String UPTOR,String typeType) {
        this.CID = CID;
        this.CNAME = CNAME;
        this.UPTIME = UPTIME;
        this.MESSAGE = MESSAGE;
        this.UPTOR=UPTOR;
        this.typeType=typeType;
    }

    @Override
    public String toString() {
        return "Type{" +
                "CID=" + CID +
                ", CNAME='" + CNAME + '\'' +
                ", UPTIME='" + UPTIME + '\'' +
                ", MESSAGE='" + MESSAGE + '\'' +
                ", UPTOR='" + UPTOR + '\'' +
                ", type='" + typeType + '\'' +
                '}';
    }
}
