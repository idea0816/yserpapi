package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.Zreport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2023/1/12
 * @Description For WeekReport
 */
public class ZreportRowMapper implements RowMapper<Zreport> {
    @Override
    public Zreport mapRow(ResultSet rs, int rowNum) throws SQLException {
        Zreport zreport = new Zreport();
        zreport.setDepNo(rs.getString("DepNo"));  // 部門編號
        zreport.setDateTime(rs.getString("DateTime")); // 掃描時間
        zreport.setQty(rs.getInt("Qty")); // 掃描數字
        return zreport;
    }
}
