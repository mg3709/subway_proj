package com.kh.mini_sample.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StationVO {
    private int index;
    private String ln_cd;
    private  String name;

    private List<String> transfer;
    private List<String> transTime;
    private List<String> edge;


    private List<String>[] WeekArvTime;
    private List<String>[] WeekDptTime;
    private List<String>[] HoliArvTime;
    private List<String>[] HoliDptTime;
    private List<String>[] WeekArvTrnNo;
    private List<String>[] WeekDptTrnNo;

    private List<String>[] HoliArvTrnNo;
    private List<String>[] HoliDptTrnNo;


    public StationVO(int index, String ln_cd, String name,List<String> edge,List<String> transfer,List<String> transTime) {
        this.index = index;
        this.ln_cd = ln_cd;
        this.name = name;
        this.edge = edge;
        this.transTime = transTime;
        this.transfer = transfer;
        this.WeekArvTime = new ArrayList[24]; //시간이 index번호로 들어가고 분정보가 index안에 값
        this.WeekDptTime = new ArrayList[24];
        this.WeekArvTrnNo = new ArrayList[24];
        this.WeekDptTrnNo = new ArrayList[24];
        this.HoliArvTime = new ArrayList[24]; //시간이 index번호로 들어가고 분정보가 index안에 값
        this.HoliDptTime = new ArrayList[24];
        this.HoliArvTrnNo = new ArrayList[24];
        this.HoliDptTrnNo = new ArrayList[24];

        for (int i = 0; i < this.WeekArvTime.length; i++) {
            WeekArvTime[i] = new ArrayList<>();
            WeekDptTime[i] = new ArrayList<>();
            WeekArvTrnNo[i] = new ArrayList<>();
            WeekDptTrnNo[i] = new ArrayList<>();

            HoliArvTime[i] = new ArrayList<>();
            HoliDptTime[i] = new ArrayList<>();
            HoliArvTrnNo[i] = new ArrayList<>();
            HoliDptTrnNo[i] = new ArrayList<>();
        }
    }


}