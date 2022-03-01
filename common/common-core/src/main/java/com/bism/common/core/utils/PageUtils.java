package com.bism.common.core.utils;

import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class PageUtils extends PageHelper {

    public static void startPage(){
        int pageNum = 0;
        int pageSize = 0;
        String orderBy = "";
        Boolean reasonable = true;
        PageHelper.startPage(pageNum,pageSize,orderBy).setReasonable(reasonable);

    }


}
