package com.example.stockproject.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateHcmioRequest {
    private String TradeDate;
    private String BranchNo;
    private String CustSeq;
    private String DocSeq;
    private String Stock;
    private String BsType;
    private double Price;
    private double Qty;
    private double Amt;
    private double Fee;
    private double Tax;
    private double StinTax;
    private double NetAmt;
    private String ModDate;
    private String ModTime;
    private String ModUser;

}
