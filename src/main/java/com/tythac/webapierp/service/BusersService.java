package com.tythac.webapierp.service;

import com.tythac.webapierp.model.Busers;

import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/11/4
 * @Description 帳號資料
 * <p>
 * 帳號資料-get Token Authorize
 */
public interface BusersService {
    // 帳號資料-get Token
    Map<String, Object> authorize(Busers busers);
}
