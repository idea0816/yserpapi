package com.tythac.webapierp.service.impl;

import com.tythac.webapierp.service.timeService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author CXY
 * @version Create Time: 2022/2/16
 * @Description 時間控制
 * <p>
 * 現在年月日
 * 現在年月
 * 現在年月日-Web
 * 現在年月日時-Web
 */
@Component
public class timeServiceImpl implements timeService {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter dateFormatWeb = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dateFormatWebT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // LocalDate中不含時分秒
    LocalDate nowDate;
    LocalDateTime nowDateTime;

    // 現在年月日
    @Override
    public String now() {
        nowDate = LocalDate.now();
        return nowDate.format(dateFormat);
    }

    // 現在年月
    @Override
    public String nowYM() {
        nowDate = LocalDate.now();
        return nowDate.format(dateFormat).substring(0, 6);
    }

    // 現在年月日-Web
    @Override
    public String nowWeb() {
        nowDate = LocalDate.now();
        return nowDate.format(dateFormatWeb);
    }

    // 現在年月日時-Web
    @Override
    public String nowWebT() {
        nowDateTime = LocalDateTime.now();
        return nowDateTime.format(dateFormatWebT);
    }
}
