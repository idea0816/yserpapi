package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.Busers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/11/4
 * @Description 帳號資料
 */
public class BusersRowMapper implements RowMapper<Busers> {
    @Override
    public Busers mapRow(ResultSet rs, int rowNum) throws SQLException {
        Busers busers = new Busers();
        busers.setUSERID(rs.getString("USERID"));  // 用戶編號
        busers.setUSERNAME(rs.getString("USERNAME"));    // 用戶姓名
        busers.setPWD(rs.getString("PWD")); // 密碼
        busers.setEMAIL(rs.getString("EMAIL"));   // E-MAIL ADDRESS
        busers.setLASTDATETIME(rs.getString("LASTDATETIME"));    // 最後登入時間
        busers.setYN(rs.getString("YN"));  // 啟用
        busers.setPasswordchend(rs.getString("passwordchend"));   // 密碼更換時間
        busers.setFromIP(rs.getString("fromIP"));  // 來源IP
        busers.setDepid(rs.getString("depid"));   // 單位代號
        busers.setEngname(rs.getString("Engname")); // 英文名
        busers.setReport(rs.getString("Report"));
        busers.setSupervisorID(rs.getString("SupervisorID"));
        return busers;
    }
}
