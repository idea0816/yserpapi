package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.Bdepartment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 鞋廠部門資料(部份)
 */
public class BdepartmentRowMapper implements RowMapper<Bdepartment> {
    @Override
    public Bdepartment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bdepartment bdepartment = new Bdepartment();
        bdepartment.setID(rs.getString("ID"));
        bdepartment.setDepName(rs.getString("DepName"));
        bdepartment.setDepMemo(rs.getString("DepMemo"));
        return bdepartment;
    }
}
