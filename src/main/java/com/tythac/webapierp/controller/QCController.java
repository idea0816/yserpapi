package com.tythac.webapierp.controller;

import com.tythac.webapierp.model.QCBLYY;
import com.tythac.webapierp.service.QCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/10/10
 * @Description 品檢作業
 *
 * 取得品檢不良原因-QCBLYY
 * 寫入檢驗不良數量資料-QCR, QCRD
 * Test
 */

@Tag(name = "品檢作業")
@RestController
@CrossOrigin
public class QCController {
    @Resource
    private QCService qcService;

    // 取得品檢不良原因
    @Operation(summary = "品檢不良原因", description = "品檢不良原因列表、不傳值或不在限定內預設(ALL)列出所有原因. QC1, QC2, QC3, TQC")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = QCBLYY.class))
                            )
                    })
    })
    @GetMapping("/QC/getQcblyys")
    public ResponseEntity<List<QCBLYY>> getBllys(@RequestParam(value = "extra", defaultValue = "ALL") String extra){
        List<QCBLYY> qcblyyList = qcService.getQcbllys(extra);
        return ResponseEntity.status(HttpStatus.OK).body(qcblyyList);
//        return qcService.getBlyyList(extra);
    }

    // 寫入檢驗不良數量資料
    @Operation(summary = "寫入檢驗不良數量資料", description = "以 JSON 傳入 insQCR:QCR.class & insQCRD:QCRD.class 資料")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    })
    })
    @PostMapping("/QC/insQCRs")
    public String insQCR(@RequestBody Map<String, Object> insQCRs){
        qcService.insQCRs(insQCRs);
        return "success";
    }

    // Test
    @GetMapping("/QC/test")
    public String test(){
        qcService.test();
        return "test";
    }
}
