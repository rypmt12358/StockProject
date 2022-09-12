package com.example.stockproject.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchUnrealRequest {
    private String branchNo;
    private String custSeq;
    private String stock;

//    private  Double max;
//    private Double min;
}
