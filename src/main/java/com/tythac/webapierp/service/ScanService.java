package com.tythac.webapierp.service;

import com.tythac.webapierp.model.Zreport;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2023/3/27
 * @Description 掃描類相關資料
 *
 * 取得線上掃描數量(單日總合)、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷
 */
public interface ScanService {
    // 取得線上掃描數量(單日總合)、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷
    List<Zreport> getSMDDSSs(String depName, String dateTime, String GXLB);
}
