package com.tythac.webapierp.dao.impl;

import com.tythac.webapierp.dao.QCBLYYDao;
import com.tythac.webapierp.mapper.QCBLYYRowMapper;
import com.tythac.webapierp.model.QCBLYY;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 *
 * 取得BC品原因
 */
@Component
public class QCBLYYDaoImpl implements QCBLYYDao {
    @Resource
    private NamedParameterJdbcTemplate lyserpJdbcTemplate;

    Map<String, Object> map;

    // 取得BC品原因
    @Override
    public List<QCBLYY> getBlyyList(String extraSQL) {
        String sqlGetBlyyList = "SELECT GSBH, YYBH, DFL, YWSM, ZWSM, AlarmL, StopL, YN " +
                "FROM QCBLYY ";
        if (!extraSQL.equals("ALL")) {
            sqlGetBlyyList = sqlGetBlyyList + extraSQL;
        }
        List<QCBLYY> getBlyyList = lyserpJdbcTemplate.query(sqlGetBlyyList, map, new QCBLYYRowMapper());
        if (getBlyyList.size() > 0) {
            return getBlyyList;
        } else {
            return Collections.emptyList();
        }
    }
}
