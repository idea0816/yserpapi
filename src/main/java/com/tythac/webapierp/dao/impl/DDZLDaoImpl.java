package com.tythac.webapierp.dao.impl;

import com.tythac.webapierp.dao.DDZLDao;
import com.tythac.webapierp.mapper.DDZLRowMapper;
import com.tythac.webapierp.model.DDZL;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/10/15
 * @Description 訂單資料
 *
 * 取得訂單資料
 */

@Component
public class DDZLDaoImpl implements DDZLDao {
    @Resource
    private NamedParameterJdbcTemplate lyserpJdbcTemplate;
    Map<String, Object> map;

    // 取得訂單資料
    @Override
    public List<DDZL> getDDZL(String extraSQL) {
        String sqlGetDDZL = "SELECT DDZL.DDBH, DDZL.GSBH, XieXing, SheHao, ARTICLE, CCGB, KHBH, KHPO, DDRQ, ShipDate, Pairs " +
                "FROM DDZL ";
        map = new HashMap<>();
        if (!extraSQL.equals("ALL")) {
            sqlGetDDZL = sqlGetDDZL + extraSQL;
        }

        List<DDZL> getDDZLList = lyserpJdbcTemplate.query(sqlGetDDZL, map, new DDZLRowMapper());
        if(getDDZLList.size() > 0){
            return getDDZLList;
        }else{
            return Collections.emptyList();
        }
    }
}
