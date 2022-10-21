package com.tythac.webapierp.service;

import com.tythac.webapierp.model.DDZL;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/15
 * @Description 訂單資料
 *
 * 取得訂單資料
 */
public interface DDZLService {
    // 取得訂單資料
    List<DDZL> getDDZL(String extra);
}
