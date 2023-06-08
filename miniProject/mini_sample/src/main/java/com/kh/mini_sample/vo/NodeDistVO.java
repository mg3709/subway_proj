package com.kh.mini_sample.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeDistVO implements Comparable<NodeDistVO> {

    private int index;
    private long cost;
    private String trainN;


    private int count;


    public NodeDistVO(int index, long cost,int count) {
        this.index = index;
        this.cost = cost;
        this.trainN = "";
        this.count = count;
    }
    @Override
    public int compareTo(NodeDistVO o) {
        if (this.cost == o.cost) {
            return Integer.compare(this.count, o.count);
        }
        return Long.compare(this.cost, o.cost);
    }
}
