package com.tythac.webapierp.controller;

import com.tythac.webapierp.model.QCBLYY;
import com.tythac.webapierp.service.QCBLYYService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/10
 * @Description 品檢作業
 */

@Tag(name = "品檢作業")
@RestController
@CrossOrigin
public class QCController {

    @Resource
    private QCBLYYService qcblyyService;

    // 取得品檢不良原因
    @Operation(summary = "品檢不良原因", description = "品檢不良原因列表、不傳值時預設(ALL)列出所有原因. QC1, QC2, QC3, TQC")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = QCBLYY.class))
                            )
                    })
    })
    @GetMapping("/getBllyList")
    public List<QCBLYY> getBllyList(@RequestParam(value = "extra", defaultValue = "ALL") String extra){
        return qcblyyService.getBlyyList(extra);
    }

}
