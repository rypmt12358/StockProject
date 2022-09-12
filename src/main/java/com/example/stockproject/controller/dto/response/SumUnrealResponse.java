package com.example.stockproject.controller.dto.response;

import com.example.stockproject.model.entity.SumUnreal;
import com.example.stockproject.model.entity.Unreal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SumUnrealResponse {
    private List<SumUnreal> resultList;
    private String responseCode;
    private String message;
}
