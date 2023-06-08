package com.kh.mini_sample.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeTransVO implements Comparable<NodeTransVO> {

    private int index;
    private long cost;
    private String trainN;

    private int count;
    public NodeTransVO(int index, long cost,int count) {
        this.index = index;
        this.cost = cost;
        this.trainN = "";
        this.count = count;
    }

    @Override
    public int compareTo(NodeTransVO o) {
        if (this.count == o.count) {
            return Long.compare(this.cost, o.cost);
        }
        return Integer.compare(this.count, o.count);
    }
}

