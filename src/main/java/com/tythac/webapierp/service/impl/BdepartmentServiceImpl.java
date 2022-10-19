package com.tythac.webapierp.service.impl;

import com.tythac.webapierp.dao.BdepartmentDao;
import com.tythac.webapierp.model.Bdepartment;
import com.tythac.webapierp.service.BdepartmentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 鞋廠部門資料(部份)
 *
 * 取得鞋廠部門資料
 */
@Component
public class BdepartmentServiceImpl implements BdepartmentService {
    @Resource
    private BdepartmentDao bdepartmentDao;

    // 取得鞋廠部門資料
    @Override
    public List<Bdepartment> getDepList(String extra) {
        String extraSQL = "";
        switch (extra) {
            // 成型 A
            case "assembly" :
                extraSQL = " WHERE GSBH = 'VDH' AND GXLB = 'A' ";
                break;
            case "ALL" :
                extraSQL = "ALL";
                break;
        }
        return bdepartmentDao.getDepList(extraSQL);
    }
}
