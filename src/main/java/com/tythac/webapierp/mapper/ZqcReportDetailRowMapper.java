package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.ZqcReportDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2023/3/27
 * @Description 成型-日明細表格
 */
public class ZqcReportDetailRowMapper implements RowMapper<ZqcReportDetail> {
    @Override
    public ZqcReportDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        ZqcReportDetail zqcReportDetail = new ZqcReportDetail();
        zqcReportDetail.setYYBH(rs.getString("YYBH"));    // 不良代碼QCBLYY.YYBH
        zqcReportDetail.setZWSM(rs.getString("ZWSM"));    // 不良原因QCBLYY.ZWSM
        zqcReportDetail.setTotal(rs.getString("Total"));   // 不良數量合計
        zqcReportDetail.setH7(rs.getString("h7"));
        zqcReportDetail.setH8(rs.getString("h8"));
        zqcReportDetail.setH9(rs.getString("h9"));
        zqcReportDetail.setH10(rs.getString("h10"));
        zqcReportDetail.setH11(rs.getString("h11"));
        zqcReportDetail.setH12(rs.getString("h12"));
        zqcReportDetail.setH13(rs.getString("h13"));
        zqcReportDetail.setH14(rs.getString("h14"));
        zqcReportDetail.setH15(rs.getString("h15"));
        zqcReportDetail.setH16(rs.getString("h16"));
        zqcReportDetail.setH17(rs.getString("h17"));
        return zqcReportDetail;
    }
}
