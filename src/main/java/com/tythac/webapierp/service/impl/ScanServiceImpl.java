package com.tythac.webapierp.service.impl;

import com.tythac.webapierp.dao.BdepartmentDao;
import com.tythac.webapierp.dao.ZreportDao;
import com.tythac.webapierp.model.Bdepartment;
import com.tythac.webapierp.model.Zreport;
import com.tythac.webapierp.service.ScanService;
import com.tythac.webapierp.service.timeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2023/3/27
 * @Description 掃描類相關資料
 *
 * 取得線上掃描數量(單日總合)、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷
 */
@Component
public class ScanServiceImpl implements ScanService {
    @Resource
    private ZreportDao zreportDao;
    @Resource
    private BdepartmentDao bdepartmentDao;
    @Resource
    private com.tythac.webapierp.service.timeService timeService;

    // 取得線上掃描數量(單日總合)、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷
    @Override
    public List<Zreport> getSMDDSSs(String depName, String dateTime, String GXLB) {
        // 取得部門 ID
        String extraSQL = " WHERE DepName = '" + depName + "' AND GSBH = 'VDH' ";
        List<Bdepartment> getBdepartments = bdepartmentDao.getDeps(extraSQL);
        // 判斷日期是否已超過 15 天
        int dayDiff = 0;
        List<Zreport> getSMDDSSs = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(timeService.nowWeb());
            Date date2 = format.parse(dateTime);
            dayDiff = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dayDiff < 15) {
            getSMDDSSs = zreportDao.getSMDDSSs(getBdepartments.get(0).getID(), dateTime + "%", GXLB);
        } else {
            getSMDDSSs = zreportDao.getSMDDSSOlds(getBdepartments.get(0).getID(), dateTime + "%", GXLB);
        }
        return getSMDDSSs;
    }
}
