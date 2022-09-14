package com.example.stockproject.controller;



import com.example.stockproject.controller.dto.request.CreateMstmbRequest;
import com.example.stockproject.controller.dto.request.UpdateCurPriceRequest;
import com.example.stockproject.controller.dto.response.StatusResponse;
import com.example.stockproject.model.entity.Mstmb;
import com.example.stockproject.service.MstmbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mstmb")
public class MstmbController {
    @Autowired
    private MstmbService mstmbService;

    @GetMapping()
    public List<Mstmb> getAllMstmb() {
        List<Mstmb> response = mstmbService.getAllMstmb();
        return response;
    }

    @GetMapping("/{stock}")
    public Mstmb getMstmbByStock(@PathVariable String stock){
        Mstmb mstmb = this.mstmbService.getMstmbByStock(stock);
        return mstmb;
    }

    @PostMapping()
    public StatusResponse createMstmb(@RequestBody CreateMstmbRequest request){
        String response =mstmbService.createMstmb(request);
        return new StatusResponse(response);
    }

    @PostMapping("/updateCurPrice")
    public StatusResponse updateCurPrice(@RequestBody UpdateCurPriceRequest request){
        StatusResponse response = mstmbService.updateCurPrice(request);
        return response;

    }



}

