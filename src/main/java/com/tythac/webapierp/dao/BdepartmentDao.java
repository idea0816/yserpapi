package com.tythac.webapierp.dao;

import com.tythac.webapierp.model.Bdepartment;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 鞋廠部門資料(部份)
 *
 * 取得鞋廠部門資料
 */
public interface BdepartmentDao {

    // 取得鞋廠部門資料
    List<Bdepartment> getDepList(String GXLB);
}
