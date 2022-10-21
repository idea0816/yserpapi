package com.tythac.webapierp.service.impl;

import com.tythac.webapierp.dao.DDZLDao;
import com.tythac.webapierp.model.DDZL;
import com.tythac.webapierp.service.DDZLService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/15
 * @Description 訂單資料
 * <p>
 * 取得訂單資料
 */
@Component
public class DDZLServiceImpl implements DDZLService {
    @Resource
    private DDZLDao ddzlDao;

    // 取得訂單資料
    @Override
    public List<DDZL> getDDZL(String extra) {
        String extraSQL = "";
        switch (extra) {
            // 品檢訂單
            case "qcDDZL":
                extraSQL = " LEFT JOIN YWDD ON DDZL.DDBH = YWDD.DDBH " +
                        "WHERE YWDD.EXESB IS NULL AND SHIPDATE > GETDATE() - 3 " +
                        "AND DDZL.DDBH NOT LIKE 'FJHS%'";
                break;
            case "ALL":
                extraSQL = "ALL";
                break;
        }
        return ddzlDao.getDDZL(extraSQL);
    }
}
