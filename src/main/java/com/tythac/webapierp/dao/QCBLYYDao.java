package com.tythac.webapierp.dao;

import com.tythac.webapierp.model.QCBLYY;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 *
 * 取得BC品原因
 */
public interface QCBLYYDao {
    // 取得BC品原因
    List<QCBLYY> getBlyyList(String extraSQL);
}
