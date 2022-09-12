package com.example.stockproject.model;

import com.example.stockproject.model.entity.Hcmio;
import com.example.stockproject.model.entity.PkRefer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HcmioRepository extends JpaRepository<Hcmio, PkRefer> {

    @Query(value = "SELECT*FROM hcmio WHERE DocSeq = ?1 ", nativeQuery = true)
    Hcmio getHcmioByDocSeq(String docseq);


    @Modifying
    @Transactional
    @Query(value = "UPDATE hcmio SET TradeDate=?1,Stock=?2, BsType=?3,Price=?4, Qty=?5, Amt=?6, Fee=?7,Tax=?8,StinTax=?9,NetAmt=?10, ModDate=?11, ModTime=?12, ModUser=?13 WHERE DocSeq=?14", nativeQuery = true)
    void updateHcmioDocSeq(String tradedate,String stock,String bstype,double price,double qty,double amt,double fee,double tax,double stintax,double netamt,String moddate,String modtime,String moduser,String docseq);

}
