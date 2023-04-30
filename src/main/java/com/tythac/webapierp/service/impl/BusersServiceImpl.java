package com.tythac.webapierp.service.impl;

import com.tythac.webapierp.dao.BusersDao;
import com.tythac.webapierp.model.Busers;
import com.tythac.webapierp.service.BusersService;
import com.tythac.webapierp.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/11/4
 * @Description 帳號資料
 * <p>
 * 帳號資料-get Token
 */
@Component
public class BusersServiceImpl implements BusersService {
    private final static Logger log = LoggerFactory.getLogger(BusersServiceImpl.class);
    @Resource
    private BusersDao busersDao;
    @Resource
    private JWTUtils jwtUtils;

    // 帳號資料-get Token
    @Override
    public Map<String, Object> authorize(Busers busers) {
        Map<String, Object> getAuthorize = new HashMap<>();
        Busers busersCheck = busersDao.authorize(busers);
        if (busersCheck == null) {
            log.warn("查無此帳號 : {}", busers.getUSERID());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else if (busersCheck.getPWD().equals(busers.getPWD().toUpperCase())) {    // 配合原始 Delphi 、ERP 字母都轉成大寫；DD 是否要轉大寫？
            getAuthorize.put("token", jwtUtils.getToken(busersCheck.getUSERID(), busersCheck.getUSERNAME()));
            getAuthorize.put("userName", busersCheck.getUSERNAME());
            getAuthorize.put("userID", busersCheck.getUSERID());
            getAuthorize.put("depid", busersCheck.getDepid());  // 這裡埋我自己的判斷、以depid去決定role
            return getAuthorize;
        } else {
            log.warn("{} 密碼不正確", busers.getUSERID());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
