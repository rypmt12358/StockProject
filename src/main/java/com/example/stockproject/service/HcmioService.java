package com.example.stockproject.service;

import com.example.stockproject.controller.dto.request.CreateHcmioRequest;
import com.example.stockproject.controller.dto.request.UpdateHcmioRequest;
import com.example.stockproject.controller.dto.response.CreatDetailResponse;
import com.example.stockproject.model.HcmioRepository;
import com.example.stockproject.model.MstmbRepository;
import com.example.stockproject.model.TcnudRepository;
import com.example.stockproject.model.entity.CreateDetail;
import com.example.stockproject.model.entity.Hcmio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service

public class HcmioService {
    @Autowired
    private HcmioRepository hcmioRepository;
    @Autowired
    private TcnudRepository tcnudRepository;
    @Autowired
    private MstmbRepository mstmbRepository;

    public List<Hcmio> getAllHcmios() {
        List<Hcmio> response = hcmioRepository.findAll();
        return response;
    }

    public Hcmio getHcmioByDocSeq(String docseq) {
        Hcmio hcmio = hcmioRepository.getHcmioByDocSeq(docseq);
        return hcmio;
    }


    public CreatDetailResponse createHcmio(CreateHcmioRequest request) {
        Hcmio hcmio = new Hcmio();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
        hcmio.setTradeDate(request.getTradeDate());
        hcmio.setBranchNo(request.getBranchNo());
        hcmio.setCustSeq(request.getCustSeq());
        hcmio.setDocSeq(request.getDocSeq());
        hcmio.setStock(request.getStock());
        hcmio.setBsType("B");
        hcmio.setPrice(request.getPrice());
        hcmio.setQty(request.getQty());
        hcmio.setAmt(Math.round(hcmio.getPrice() * hcmio.getQty()));
        hcmio.setTax(0);
        hcmio.setFee(Math.round(hcmio.getAmt() * 0.001425));
        hcmio.setNetAmt(Math.round((hcmio.getAmt() + hcmio.getFee()) * -1));
        hcmio.setStinTax(0);
//        if (hcmio.getBsType().equals("S")) {// 賣出
//            hcmio.setFee(Math.round(hcmio.getAmt() * 0.001425));
//            hcmio.setTax(Math.round((hcmio.getAmt() * 0.003)));
//            hcmio.setNetAmt(Math.round((hcmio.getAmt() - hcmio.getFee() - hcmio.getTax())));
//            hcmio.setStinTax(0);
//        } else {// 買入
//            hcmio.setTax(0);
//            hcmio.setFee(Math.round(hcmio.getAmt() * 0.001425));
//            hcmio.setNetAmt(Math.round((hcmio.getAmt() + hcmio.getFee()) * -1));
//            hcmio.setStinTax(0);
//        }
        hcmio.setModDate(date.format(LocalDateTime.now()));
        hcmio.setModTime(time.format(LocalDateTime.now()));
        hcmio.setModUser("Chih");
        hcmioRepository.save(hcmio);

        tcnudRepository.CreatTcnud(
                hcmio.getTradeDate(), hcmio.getBranchNo(), hcmio.getCustSeq(), hcmio.getDocSeq(),
                hcmio.getStock(), hcmio.getPrice(), hcmio.getQty(), hcmio.getQty(), hcmio.getFee(),
                hcmio.getAmt() + hcmio.getFee(), hcmio.getModDate(), hcmio.getModTime(), hcmio.getModUser());

        CreateDetail createDetail = new CreateDetail();
        createDetail.setTradeDate(hcmio.getTradeDate());
        createDetail.setDocSeq(hcmio.getDocSeq());
        createDetail.setStock(hcmio.getStock());
        createDetail.setStockName(mstmbRepository.findByStock(hcmio.getStock()).getStockName());
        createDetail.setBuyPrice(hcmio.getPrice());
        createDetail.setNowPrice(mstmbRepository.findByStock(hcmio.getStock()).getCurPrice());
        createDetail.setQty((int)hcmio.getQty());
        createDetail.setRemainQty((int)hcmio.getQty());
        createDetail.setFee((int)hcmio.getFee());
        createDetail.setCost((int)(hcmio.getAmt() + hcmio.getFee()));
        createDetail.setMarketValue((int)((mstmbRepository.findByStock(hcmio.getStock()).getCurPrice() * hcmio.getQty()) -
                (mstmbRepository.findByStock(hcmio.getStock()).getCurPrice()*hcmio.getQty()*0.003)-
                (mstmbRepository.findByStock(hcmio.getStock()).getCurPrice()*hcmio.getQty()*0.001425)));
        createDetail.setUnrealProfit(createDetail.getMarketValue() - createDetail.getCost());

        List<CreateDetail> createDetailList=new ArrayList<>();
        createDetailList.add(createDetail);

        CreatDetailResponse response = new CreatDetailResponse();
        response.setResultList(createDetailList);
        String price = String.valueOf(request.getPrice());
        String qty =String.valueOf(request.getQty());
        if (isBlank(request.getTradeDate()) || isBlank(request.getBranchNo()) ||
                isBlank(request.getCustSeq()) || isBlank(request.getDocSeq()) ||
                isBlank(request.getStock()) || isBlank(price) || isBlank(qty)){
            response.setResponseCode("002");
            response.setMessage("參數檢核錯誤");
        } else {
            response.setResponseCode("000");
            response.setMessage("成功");
        }

        return response;
    }

    public String updateHcmio(String curDocSeq, UpdateHcmioRequest request) {
        Hcmio hcmio = new Hcmio();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
        hcmio.setTradeDate(date.format(LocalDateTime.now()));
        hcmio.setBranchNo(request.getBranchNo());
        hcmio.setCustSeq(request.getCustSeq());
        hcmio.setDocSeq(curDocSeq);
        hcmio.setStock(request.getStock());
        hcmio.setBsType(request.getBsType());
        hcmio.setPrice(request.getPrice());
        hcmio.setQty(request.getQty());
        hcmio.setAmt(Math.round(hcmio.getPrice() * hcmio.getQty()));
        if (hcmio.getBsType().equals("S")) {// 賣出
            hcmio.setFee(Math.round(hcmio.getAmt() * 0.001425));
            hcmio.setTax(Math.round((hcmio.getAmt() * 0.003)));
            hcmio.setNetAmt(Math.round((hcmio.getAmt() - hcmio.getFee() - hcmio.getTax())));
            hcmio.setStinTax(0);
        } else {// 買入
            hcmio.setTax(0);
            hcmio.setFee(Math.round(hcmio.getAmt() * 0.001425));
            hcmio.setNetAmt(Math.round((hcmio.getAmt() + hcmio.getFee()) * -1));
            hcmio.setStinTax(0);
        }
        hcmio.setModDate(date.format(LocalDateTime.now()));
        hcmio.setModTime(time.format(LocalDateTime.now()));
        hcmio.setModUser(request.getModUser());
        hcmioRepository.updateHcmioDocSeq(hcmio.getTradeDate(), hcmio.getStock(), hcmio.getBsType(), hcmio.getPrice(), hcmio.getQty(), hcmio.getAmt(),
                hcmio.getFee(), hcmio.getTax(), hcmio.getStinTax(), hcmio.getNetAmt(), hcmio.getModDate(), hcmio.getModTime(), hcmio.getModUser(), curDocSeq);

        tcnudRepository.updateTcundDocSeq(hcmio.getTradeDate(), hcmio.getBranchNo(), hcmio.getCustSeq(), hcmio.getStock(), hcmio.getQty(), hcmio.getQty(), hcmio.getFee(),
                hcmio.getAmt() + hcmio.getFee(), hcmio.getPrice(), hcmio.getModDate(), hcmio.getModTime(), hcmio.getModUser(), curDocSeq);

        return "OK";
    }


}
