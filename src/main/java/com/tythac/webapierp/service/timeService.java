package com.tythac.webapierp.service;

/**
 * @author CXY
 * @version Create Time: 2022/02/16
 * @Description 時間控制
 * <p>
 * 現在年月日
 * 現在年月
 * 現在年月日-Web
 */
public interface timeService {
    // 現在年月日
    String now();

    // 現在年月
    String nowYM();

    // 現在年月日-Web
    String nowWeb();

    // 現在年月日時-Web
    String nowWebT();

}
