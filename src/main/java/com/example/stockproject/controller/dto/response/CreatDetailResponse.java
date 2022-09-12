package com.example.stockproject.controller.dto.response;

import com.example.stockproject.model.entity.CreateDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatDetailResponse {
    private List<CreateDetail> resultList;
    private String responseCode;
    private String message;
}
