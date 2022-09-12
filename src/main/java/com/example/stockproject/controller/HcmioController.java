package com.example.stockproject.controller;

import com.example.stockproject.controller.dto.request.CreateHcmioRequest;
import com.example.stockproject.controller.dto.request.UpdateHcmioRequest;
import com.example.stockproject.controller.dto.response.CreatDetailResponse;
import com.example.stockproject.controller.dto.response.StatusResponse;
import com.example.stockproject.model.entity.Hcmio;
import com.example.stockproject.service.HcmioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hcmio")
public class HcmioController {
    @Autowired
    private HcmioService hcmioService;

    @GetMapping()
    public List<Hcmio> getAllHcmios() {
        List<Hcmio> response = hcmioService.getAllHcmios();
        return response;
    }

    @GetMapping("/{docseq}")
    public Hcmio getHcmioByDocSeq(@PathVariable String docseq) {
        Hcmio response = hcmioService.getHcmioByDocSeq(docseq);
        return response;
    }

    @PostMapping("/unreal/add")
    public CreatDetailResponse createHcmio(@RequestBody CreateHcmioRequest request) {
        CreatDetailResponse response = hcmioService.createHcmio(request);
        return response;
    }

    @PutMapping({"/{docseq}"})
    public StatusResponse updateHcmio(@PathVariable String docseq, @RequestBody UpdateHcmioRequest request) {
        String response = hcmioService.updateHcmio(docseq, request);
        return new StatusResponse(response);
    }


}
