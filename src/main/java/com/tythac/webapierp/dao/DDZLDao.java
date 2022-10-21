package com.tythac.webapierp.dao;

import com.tythac.webapierp.model.DDZL;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 訂單資料
 *
 * 取得訂單資料
 */
public interface DDZLDao {

    // 取得訂單資料
    List<DDZL> getDDZL(String extraSQL);
}
