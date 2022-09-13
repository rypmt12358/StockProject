package com.example.stockproject.model;

import com.example.stockproject.model.entity.Hcmio;
import com.example.stockproject.model.entity.Mstmb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MstmbRepository extends JpaRepository<Mstmb, String> {
    @Query(value = "SELECT*FROM mstmb WHERE Stock = ?1 ", nativeQuery = true)
    Mstmb selectMstmbByStock(String stock);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO mstmb(Stock, StockName, MarketType, CurPrice, RefPrice, Currency, ModDate, ModTime, ModUser) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    void CreatMstmb(String stock, String stockname, char markettype, double curprice, double refprice, String currency, String moddate, String modtime, String moduser);

    Mstmb findByStock(String stock);

    @Modifying
    @Transactional
    @Query(value = "UPDATE  mstmb SET CurPrice =?1 WHERE Stock = ?2", nativeQuery = true)
    void updateCurPrice(double curprice, String stock);


}
