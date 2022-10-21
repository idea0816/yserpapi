package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.DDZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 訂單基本資料(部份)
 */
public class DDZLRowMapper implements RowMapper<DDZL> {
    @Override
    public DDZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        DDZL ddzl = new DDZL();
        ddzl.setDDBH(rs.getString("DDBH"));    // 訂單編號
        ddzl.setGSBH(rs.getString("GSBH"));    // 公司編號
        ddzl.setXieXing(rs.getString("XieXing")); // 鞋型
        ddzl.setSheHao(rs.getString("SheHao"));  // 色號
        ddzl.setARTICLE(rs.getString("ARTICLE")); // ARTICLE
        ddzl.setCCGB(rs.getString("CCGB"));    // 尺寸國別
        ddzl.setKHBH(rs.getString("KHBH"));    // 客戶代號
        ddzl.setKHPO(rs.getString("KHPO"));    // 客戶PO
        ddzl.setDDRQ(rs.getString("DDRQ"));    // 接單日期
        ddzl.setShipDate(rs.getString("ShipDate"));    // 指定交期
        ddzl.setPairs(rs.getInt("Pairs"));  // 雙數
        return ddzl;
    }
}
