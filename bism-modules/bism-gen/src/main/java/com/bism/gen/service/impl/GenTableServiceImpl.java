package com.bism.gen.service.impl;

import com.bism.gen.domain.GenTable;
import com.bism.gen.mapper.GenTableMapper;
import com.bism.gen.service.IGenTableService;

import java.util.List;

public class GenTableServiceImpl implements IGenTableService {

    private GenTableMapper genTableMapper;

    @Override
    public List<GenTable> selectGenTableList(GenTable genTable) {
        return genTableMapper.selectGenTableList(genTable);
        return null;
    }
}
