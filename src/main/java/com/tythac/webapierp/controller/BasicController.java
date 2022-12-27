package com.tythac.webapierp.controller;

import com.tythac.webapierp.model.Bdepartment;
import com.tythac.webapierp.model.Busers;
import com.tythac.webapierp.service.BdepartmentService;
import com.tythac.webapierp.service.BusersService;
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
import javax.validation.Valid;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/13
 * @Description 基本資料
 * <p>
 * 單位資料
 * 帳號資料-Authorize
 */

@Tag(name = "基本資料")
@RestController
@CrossOrigin
public class BasicController {
    @Resource
    private BdepartmentService bdepartmentService;
    @Resource
    private BusersService busersService;

    // 單位資料
    @Operation(summary = "部門資料", description = "部門列表、不傳值時預設(ALL)列出所有部門. " +
            "裁斷：Cutting, " +
            "底加：Outsole, " +
            "針車：Sting, " +
            "成型：Assembly ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Bdepartment.class))
                            )
                    })
    })
    @GetMapping("/getDeps")
    public List<Bdepartment> getDeps(@RequestParam(value = "extra", defaultValue = "ALL") String extra) {
        return bdepartmentService.getDeps(extra);
    }

    @Operation(summary = "帳號資料-Login", description = "帳號資料-Login ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Busers.class))
                            )
                    })
    })
    // 帳號資料-Authorize
    @PostMapping("/login")
    public ResponseEntity<Busers> login(@RequestBody @Valid Busers busers) {
        return ResponseEntity.status(HttpStatus.OK).body(busersService.authorize(busers));
    }
}
