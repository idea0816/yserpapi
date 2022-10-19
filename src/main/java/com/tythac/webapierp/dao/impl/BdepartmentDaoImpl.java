package com.tythac.webapierp.dao.impl;

import com.tythac.webapierp.dao.BdepartmentDao;
import com.tythac.webapierp.mapper.BdepartmentRowMapper;
import com.tythac.webapierp.model.Bdepartment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 鞋廠部門資料(部份)
 * <p>
 * 取得鞋廠部門資料
 */

@Component
public class BdepartmentDaoImpl implements BdepartmentDao {

    @Resource
    private NamedParameterJdbcTemplate lyserpJdbcTemplate;

    Map<String, Object> map;

    // 取得鞋廠部門資料
    @Override
    public List<Bdepartment> getDepList(String extraSQL) {
        String sqlGetDepList = "SELECT ID, DepName, DepMemo FROM BDepartment ";
        map = new HashMap<>();
        if (!extraSQL.equals("ALL")) {
            sqlGetDepList = sqlGetDepList + extraSQL;
        }

        List<Bdepartment> getDepList = lyserpJdbcTemplate.query(sqlGetDepList, map, new BdepartmentRowMapper());
        if (getDepList.size() > 0) {
            return getDepList;
        } else {
            return Collections.emptyList();
        }
    }
}
