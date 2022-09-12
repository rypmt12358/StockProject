package com.example.stockproject.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMstmbRequest {
    private String Stock;
    private String StockName;
    private char MarketType;
    private double CurPrice;
    private double RefPrice;
    private String Currency;
    private String ModDate;
    private String ModTime;
    private String ModUser;
}
