package com.example.stockproject.controller;

import com.example.stockproject.controller.dto.request.SearchUnrealRequest;
import com.example.stockproject.controller.dto.response.SumUnrealResponse;
import com.example.stockproject.controller.dto.response.UnrealResponse;
import com.example.stockproject.model.entity.Tcnud;
import com.example.stockproject.service.TcnudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tcnud")
public class TcnudController {
    @Autowired
    private TcnudService tcnudService;

    @GetMapping()
    public List<Tcnud> getAllTnud() {
        List<Tcnud> response = tcnudService.getAllTcund();
        return response;
    }

    @PostMapping("/unreal/detail")
    public UnrealResponse createUnreal(@RequestBody SearchUnrealRequest request) {
        UnrealResponse response =tcnudService.getUnreal(request);
        return response;
    }

    @PostMapping("/unreal/sum")
    public SumUnrealResponse createSumUnreal(@RequestBody SearchUnrealRequest request) {
        SumUnrealResponse response =tcnudService.getSumUnreal(request);
        return response;
    }

}
