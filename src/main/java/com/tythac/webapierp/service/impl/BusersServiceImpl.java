package com.tythac.webapierp.service.impl;

import com.tythac.webapierp.dao.BusersDao;
import com.tythac.webapierp.model.Busers;
import com.tythac.webapierp.service.BusersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

/**
 * @author CXY
 * @version Create Time: 2022/11/4
 * @Description 帳號資料
 * <p>
 * 帳號資料-Authorize
 */
@Component
public class BusersServiceImpl implements BusersService {
    private final static Logger log = LoggerFactory.getLogger(BusersServiceImpl.class);
    @Resource
    private BusersDao busersDao;

    // 帳號資料-Authorize
    @Override
    public Busers authorize(Busers busers) {
        Busers busersCheck = busersDao.authorize(busers);
        if (busersCheck == null) {
            log.warn("查無此帳號 : {}", busers.getUSERID());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else if (busersCheck.getPWD().equals(busers.getPWD())) {
            return busersCheck;
        } else {
            log.warn("{} 密碼不正確", busers.getUSERID());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
