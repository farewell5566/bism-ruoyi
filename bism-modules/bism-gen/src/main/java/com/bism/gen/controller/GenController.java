package com.bism.gen.controller;

import com.bism.gen.domain.GenTable;
import com.bism.gen.page.TableDataInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gen")
@RestController
public class GenController {

    public TableDataInfo genList(GenTable genTable){
        startPage();

    }





}
