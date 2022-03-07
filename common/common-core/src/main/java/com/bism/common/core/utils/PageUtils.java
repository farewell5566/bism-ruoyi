package com.bism.common.core.utils;

import com.bism.common.core.web.page.PageDomain;
import com.bism.common.core.web.page.TableSupport;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class PageUtils extends PageHelper {

    public static void startPage(){
        PageDomain pageDomain =TableSupport.getPageDomain();
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderByColumn()).
                setReasonable(pageDomain.isReasonable());
    }


}
