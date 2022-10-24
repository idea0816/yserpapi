package com.tythac.webapierp.service;

import com.tythac.webapierp.model.QCBLYY;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 *
 * 取得BC品原因
 */
public interface QCBLYYService {
    // 取得BC品原因
    List<QCBLYY> getBlyyList(String extra);
}
