package com.bism.gen.controller;

import com.bism.common.core.web.controller.BaseController;
import com.bism.gen.domain.GenTable;
import com.bism.gen.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/gen")
@RestController
public class GenController extends BaseController {

    @Autowired
    private IGenTableService genTableService;


    /**
     * 查询代码生成列表
     * @param genTable
     * @return
     */
    @GetMapping("/db/list")
    public TableDataInfo genList(GenTable genTable){
        startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);




    }





}
