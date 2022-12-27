package com.tythac.webapierp.controller;

import com.tythac.webapierp.model.DDZL;
import com.tythac.webapierp.service.DDZLService;
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
 * @version Create Time: 2022/10/15
 * @Description 訂單資料
 *
 * 取得訂單資料
 */
@Tag(name = "訂單資料")
@RestController
@CrossOrigin
public class DDZLController {
    @Resource
    private DDZLService ddzlService;

    // 取得訂單資料
    @Operation(summary = "訂單資料", description = "訂單列表：不傳值時預設(ALL)列出所有訂單(資料量很大、要特別注意). " +
            "品檢訂單：qcDDZL, ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DDZL.class))
                            )
                    })
    })
    @GetMapping("/getDDZLs")
    public List<DDZL> getDDZLList(@RequestParam(value = "extra", defaultValue = "ALL") String extra){
        return ddzlService.getDDZL(extra);
    }

}
