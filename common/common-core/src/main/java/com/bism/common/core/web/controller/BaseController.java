package com.bism.common.core.web.controller;

import com.bism.common.core.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public  void startPage(){
        PageUtils.startPage();



    }






}
