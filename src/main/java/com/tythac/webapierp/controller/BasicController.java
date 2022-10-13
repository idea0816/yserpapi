package com.tythac.webapierp.controller;

import com.tythac.webapierp.model.Bdepartment;
import com.tythac.webapierp.service.BdepartmentService;
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
 * @version Create Time: 2022/10/13
 * @Description 基本資料
 */

@Tag(name = "基本資料")
@RestController
@CrossOrigin
public class BasicController {
    @Resource
    private BdepartmentService bdepartmentService;

    // 取得單位資料
    @Operation(summary = "部門資料", description = "部門列表、不傳值時預設列出所有部門")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Bdepartment.class))
                            )
                    })
    })
    @GetMapping("/getDepList")
    public List<Bdepartment> getDepList(@RequestParam(value = "GXLB", defaultValue = "ALL") String GXLB) {
        return bdepartmentService.getDepList(GXLB);
    }
}
