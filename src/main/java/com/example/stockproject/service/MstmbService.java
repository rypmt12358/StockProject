package com.example.stockproject.service;


import com.example.stockproject.controller.dto.request.CreateMstmbRequest;
import com.example.stockproject.controller.dto.request.UpdateMstmbRequest;
import com.example.stockproject.controller.dto.response.StatusResponse;
import com.example.stockproject.model.MstmbRepository;
import com.example.stockproject.model.entity.Mstmb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class MstmbService {
    @Autowired
    private MstmbRepository mstmbRepository;

    public List<Mstmb> getAllMstmb(){
        List<Mstmb> response= mstmbRepository.findAll();
        return response;
    }

    public Mstmb getMstmbByStock(String stock){
        Mstmb mstmb = mstmbRepository.selectMstmbByStock(stock);
        return  mstmb;
    }

    public  String createMstmb(CreateMstmbRequest request) {
        Mstmb mstmb = new Mstmb();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");

        mstmb.setStock(request.getStock());
        mstmb.setStockName(request.getStockName());
        mstmb.setMarketType(request.getMarketType());
        mstmb.setCurPrice(request.getCurPrice());
        mstmb.setRefPrice(request.getRefPrice());
        mstmb.setCurrency(request.getCurrency());
        mstmb.setModDate(date.format(LocalDateTime.now()));
        mstmb.setModTime(time.format(LocalDateTime.now()));
        mstmb.setModUser(request.getModUser());

        mstmbRepository.save(mstmb);

        return "ok";
    }
    public StatusResponse updateCurPrice(UpdateMstmbRequest request){
        StatusResponse response = new StatusResponse();
        if (isBlank(request.getStock()) || request.getCurPrice()==null) {
            response.setStatus("FAIL");
        }else {
            mstmbRepository.updateCurPrice(request.getCurPrice(),request.getStock());
            response.setStatus("OK");
        }
        return response;
    }
}
