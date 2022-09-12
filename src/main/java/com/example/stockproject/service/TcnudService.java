package com.example.stockproject.service;

import com.example.stockproject.controller.dto.request.SearchUnrealRequest;
import com.example.stockproject.controller.dto.response.SumUnrealResponse;
import com.example.stockproject.controller.dto.response.UnrealResponse;
import com.example.stockproject.model.MstmbRepository;
import com.example.stockproject.model.TcnudRepository;
import com.example.stockproject.model.entity.Mstmb;
import com.example.stockproject.model.entity.SumUnreal;
import com.example.stockproject.model.entity.Tcnud;
import com.example.stockproject.model.entity.Unreal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class TcnudService {
    @Autowired
    private TcnudRepository tcnudRepository;
    @Autowired
    private MstmbRepository mstmbRepository;

    public List<Tcnud> getAllTcund() {
        List<Tcnud> response = tcnudRepository.findAll();
        return response;
    }

    public UnrealResponse getUnreal(SearchUnrealRequest request) {

        List<Tcnud> tcnudList;
        Mstmb mstmb;
        if (!request.getStock().isEmpty()) {
            tcnudList = tcnudRepository.findByBCS(request.getBranchNo(), request.getCustSeq(), request.getStock());
        } else {
            tcnudList = tcnudRepository.findByBranchNoAndCustSeq(request.getBranchNo(), request.getCustSeq());
        }
        List<Unreal> unrealList = new ArrayList<>();
        for (Tcnud td : tcnudList) {
            mstmb = mstmbRepository.selectMstmbByStock(td.getStock());
            Unreal un = new Unreal();
            un.setTradeDate(td.getTradeDate());
            un.setDocSeq(td.getDocSeq());
            un.setStock(td.getStock());
            un.setStockName(mstmb.getStockName());
            un.setBuyPrice(Math.round(td.getPrice() * 100.0) / 100.0);
            un.setNowPrice(Math.round(mstmb.getCurPrice() * 100.0) / 100.0);
            un.setQty((int) td.getQty());
            un.setRemainQty((int) td.getRemainQty());
            un.setFee((int) Math.round(td.getFee()));
            un.setCost((int) Math.round(td.getCost()));
            un.setMarketValue((int) Math.round((mstmb.getCurPrice() * td.getQty()) - (mstmb.getCurPrice() * td.getQty() * 0.003) - (mstmb.getCurPrice() * td.getQty() * 0.001425)));
            un.setUnrealProfit((int) Math.round(un.getMarketValue() - td.getCost()));
            unrealList.add(un);
        }


        UnrealResponse response = new UnrealResponse();
        response.setResultList(unrealList);
        if ((!tcnudRepository.findByBranchNoAndCustSeq(request.getBranchNo(), request.getCustSeq()).isEmpty()) && request.getStock().isEmpty()) {
            response.setResponseCode("000");
            response.setMessage("成功");
        } else if (isBlank(request.getBranchNo()) || isBlank(request.getStock()) || isBlank(request.getCustSeq())) {
            response.setResponseCode("002");
            response.setMessage("參數檢核錯誤");
        } else if (response.getResultList().isEmpty()) {
            response.setResponseCode("001");
            response.setMessage("查無符合資料");
        } else if (!tcnudRepository.findByBCS(request.getBranchNo(), request.getCustSeq(), request.getStock()).isEmpty()) {
            response.setResponseCode("000");
            response.setMessage("成功");
        } else {
            response.setResponseCode("005");
            response.setMessage("伺服器內部錯誤");
        }

        return response;
    }


    public SumUnrealResponse getSumUnreal(SearchUnrealRequest request) {
        List<Unreal> unrealList = getUnreal(request).getResultList();
        List<SumUnreal> sumUnrealList = new ArrayList<>();
        List<String> stockList = tcnudRepository.findByDistinctBC(request.getBranchNo(), request.getCustSeq());

        for (String stock : stockList) {
            List<Unreal> newUnrealList = new ArrayList<>();
            Mstmb mstmb=mstmbRepository.findByStock(stock);
            int sumRemainQty = 0, sumFee = 0, sumCost = 0, sumMarketValue = 0, sumUnrealProfit = 0;
            for (Unreal unreal : unrealList) {
                if (unreal.getStock().equals(stock)) {
                    sumRemainQty += unreal.getRemainQty();
                    sumFee+=unreal.getFee();
                    sumCost+=unreal.getCost();
                    sumMarketValue+= unreal.getMarketValue();
                    sumUnrealProfit+=unreal.getUnrealProfit();
                    newUnrealList.add(unreal);
                }
            }
            SumUnreal sumUnreal=new SumUnreal();
            sumUnreal.setStock(stock);
            sumUnreal.setStockName(mstmb.getStockName());
            sumUnreal.setNowPrice(mstmb.getCurPrice());
            sumUnreal.setSumRemainQty(sumRemainQty);
            sumUnreal.setSumFee(sumFee);
            sumUnreal.setSumCost(sumCost);
            sumUnreal.setSumMarketValue(sumMarketValue);
            sumUnreal.setSumUnrealProfit(sumUnrealProfit);
            sumUnreal.setDetailList(newUnrealList);
            sumUnrealList.add(sumUnreal);

        }

        SumUnrealResponse response = new SumUnrealResponse();
        response.setResultList(sumUnrealList);
        if (!tcnudRepository.findByBranchNoAndCustSeq(request.getBranchNo(), request.getCustSeq()).isEmpty() && request.getStock().isEmpty()) {
            response.setResponseCode("000");
            response.setMessage("成功");
        } else if (isBlank(request.getBranchNo()) || isBlank(request.getStock()) || isBlank(request.getCustSeq())) {
            response.setResponseCode("002");
            response.setMessage("參數檢核錯誤");
        } else if (unrealList.isEmpty()) {
            response.setResponseCode("001");
            response.setMessage("查無符合資料");
        } else if (!tcnudRepository.findByBCS(request.getBranchNo(), request.getCustSeq(), request.getStock()).isEmpty()) {
            response.setResponseCode("000");
            response.setMessage("成功");
        } else {
            response.setResponseCode("005");
            response.setMessage("伺服器內部錯誤");
        }

        return response;
    }


}
