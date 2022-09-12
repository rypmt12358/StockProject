package com.example.stockproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SumUnreal {
    private String stock;
    private String stockName;
    private double nowPrice;
    private int sumRemainQty;
    private int sumFee;
    private int sumCost;
    private int sumMarketValue;
    private int sumUnrealProfit;
    private List<Unreal> detailList;
}
