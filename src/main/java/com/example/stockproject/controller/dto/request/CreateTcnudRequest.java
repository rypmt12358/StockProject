package com.example.stockproject.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTcnudRequest {
    private String TradeDate;
    private String BranchNo;
    private String CustSeq;
    private String DocSeq;
    private String Stock;
    private double Price;
    private double Qty;
    private double RemainQty;
    private double Fee;
    private double Cost;
    private String ModDate;
    private String ModTime;
    private String ModUser;
}
