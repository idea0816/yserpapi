package com.tythac.webapierp.service;

import com.tythac.webapierp.model.Bdepartment;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 鞋廠部門資料(部份)
 *
 * 鞋廠部門資料
 */
public interface BdepartmentService {

    // 取得鞋廠部門資料
    List<Bdepartment> getDeps(String extra);
}
