package com.example.stockproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unreal {
    private String tradeDate;
    private String docSeq;
    private String stock;
    private String stockName;
    private double buyPrice;
    private double nowPrice;
    private int qty;
    private int remainQty;
    private int fee;
    private int cost;
    private int marketValue;
    private int unrealProfit;

}
