package com.example.stockproject.model;

import com.example.stockproject.model.entity.PkRefer;
import com.example.stockproject.model.entity.Tcnud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TcnudRepository extends JpaRepository<Tcnud, PkRefer> {
/*
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Tcnud(TradeDate, BranchNo, CustSeq, DocSeq, Stock, Price, Qty, RemainQty, Fee, Cost, ModDate, ModTime, ModUser) VALUES (:TradeDate, :BranchNo, :CustSeq, :DocSeq, :Stock, :Price, :Qty, :RemainQty, :Fee, :Cost, :ModDate, :ModTime, :ModUser)",nativeQuery = true)
    void CreatTcnud(@Param("TradeDate")String TradeDate, @Param("BranchNo")String BranchNo, @Param("CustSeq")String CustSeq, @Param("DocSeq")String DocSeq, @Param("Stock")String Stock, @Param("Price")double Price, @Param("Qty")double Qty, @Param("RemainQty")double RemainQty, @Param("Fee")double Fee, @Param("Cost")double Cost, @Param("ModDate")String ModDate, @Param("ModTime")String ModTime, @Param("ModUser")String ModUser);
*/

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO tcnud(TradeDate, BranchNo, CustSeq, DocSeq, Stock, Price, Qty, RemainQty, Fee, Cost, ModDate, ModTime, ModUser) " +
//            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13)", nativeQuery = true)
//    void CreatTcnud(String tradeDate, String branchNo, String custSeq, String docSeq, String stock, double price, double qty, double remainQty, double fee, double cost, String modDate, String modTime, String modUser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tcnud SET TradeDate=?1,BranchNo=?2,CustSeq=?3,Stock=?4,Price=?5,Qty=?6,RemainQty=?7,Fee=?8,Cost=?9,ModDate=?10,ModTime=?11,ModUser=?12 WHERE DocSeq=?13", nativeQuery = true)
    void updateTcundDocSeq(String tradeDate, String branchNo, String custSeq, String stock, double Price, double qty, double remainQty, double fee, double cost, String modDate, String modTime, String modUser, String docSeq);

    @Query(value = "SELECT * FROM tcnud WHERE BranchNo=?1 AND CustSeq=?2 AND Stock=?3", nativeQuery = true)
    List<Tcnud> findByBCS(String branchNo, String custSeq, String Stock);

    List<Tcnud> findByBranchNoAndCustSeq(String branchNo , String custSeq);

    @Query(value = "SELECT DISTINCT Stock FROM tcnud WHERE BranchNo=?1 AND CustSeq=?2", nativeQuery = true)
    List<String> findByDistinctBC(String branchNo, String custSeq);

}
