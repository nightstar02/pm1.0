package com.peoplemoney.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Page<T> {
    private Integer pageNum;
    private Integer pageSize;
    private long totalCount;

    private Integer totalPages;
    private Integer prePage;
    private Integer nextPage;

    private Integer startNavPage;
    private Integer endNavPage;

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", startNavPage=" + startNavPage +
                ", endNavPage=" + endNavPage +
                ", dataList=" + dataList +
                '}';
    }

    private List<T> dataList;

    public Page(Integer pageNum,Integer pageSize,long totalCount){
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.totalCount=totalCount;

        this.totalPages=(int)Math.ceil(totalCount/(pageSize*1.0));

        this.prePage=pageNum-1<1?1: pageNum-1;
        this.nextPage=pageNum +1 >totalPages? totalPages:pageNum+1;

        this.startNavPage=prePage-5;
        this.endNavPage=pageNum+4;
        if (this.startNavPage<1){
            this.startNavPage=1;
            this.endNavPage=this.startNavPage+9>totalPages? totalPages:this.startNavPage+9;

        }

        if (this.endNavPage>totalPages){
            this.endNavPage=totalPages;
            this.startNavPage=this.endNavPage-9<1?1:this.endNavPage-9;
        }
    }
}
