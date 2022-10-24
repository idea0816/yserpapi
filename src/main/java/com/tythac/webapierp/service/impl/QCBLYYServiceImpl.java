package com.tythac.webapierp.service.impl;

import com.tythac.webapierp.dao.QCBLYYDao;
import com.tythac.webapierp.model.QCBLYY;
import com.tythac.webapierp.service.QCBLYYService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 *
 * 取得BC品原因
 */
@Component
public class QCBLYYServiceImpl implements QCBLYYService {
    @Resource
    private QCBLYYDao qcblyyDao;

    @Override
    public List<QCBLYY> getBlyyList(String extra) {
        String extraSQL = "";
        switch (extra) {
            // QC1
            case "QC1" :
                extraSQL = " WHERE YYBH LIKE 'Q1%' ";
                break;
            case "QC2" :
                extraSQL = " WHERE YYBH LIKE 'Q2%' ";
                break;
            case "QC3" :
                extraSQL = " WHERE YYBH LIKE 'Q3%' ";
                break;
            case "TQC" :
                extraSQL = " WHERE YYBH LIKE 'TQ%' ";
                break;
            case "ALL" :
                extraSQL = "ALL";
                break;
        }
        return qcblyyDao.getBlyyList(extraSQL);
    }
}
