package com.tythac.webapierp.controller;

import com.tythac.webapierp.model.Zreport;
import com.tythac.webapierp.service.ScanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2023/3/27
 * @Description ERP 掃描類相關資料
 *
 * 取得線上掃描數量(單日總合)、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷
 */
@Tag(name = "掃描類相關資料")
@RestController
@CrossOrigin
public class ScanController {
    @Resource
    private ScanService scanService;

    // 取得線上掃描數量(單日總合)、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷
    @Operation(summary = "取得線上掃描數量(成型、日)", description = "單日總合、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷(傳入：depName, dateTime, GXLB. 傳出：Zreport - DepNo, Date, Qty) ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Zreport.class))
                            )
                    })
    })
    @GetMapping("/scanData/getSMDDSSs")
    public List<Zreport> getSMDDSSs(@RequestParam String depName, String dateTime, String GXLB){
        return scanService.getSMDDSSs(depName, dateTime, GXLB);
    }

}
