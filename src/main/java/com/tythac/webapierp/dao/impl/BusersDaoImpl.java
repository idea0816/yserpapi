package com.tythac.webapierp.dao.impl;

import com.tythac.webapierp.dao.BusersDao;
import com.tythac.webapierp.mapper.BusersRowMapper;
import com.tythac.webapierp.model.Busers;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/11/4
 * @Description 帳號資料
 * <p>
 * 帳號資料-Authorize id
 */
@Component
public class BusersDaoImpl implements BusersDao {
    @Resource
    private NamedParameterJdbcTemplate lyserpJdbcTemplate;
    Map<String, Object> map;

    // 帳號資料-Authorize id
    @Override
    public Busers authorize(Busers busers) {
        String sqlAuth = "SELECT USERID, USERNAME, PWD, EMAIL, LASTDATETIME, YN, passwordchend, fromIP, depid, Engname, Report, SupervisorID " +
                "FROM Busers " +
                "WHERE USERID = :USERID";
        map = new HashMap<>();
        map.put("USERID", busers.getUSERID());
        List<Busers> getBusers = lyserpJdbcTemplate.query(sqlAuth, map, new BusersRowMapper());
        if (getBusers.size() > 0) {
            return getBusers.get(0);
        } else {
            return null;
        }
    }
}
