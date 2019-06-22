package com.jnshu.tiles.service.impl;

import com.jnshu.tiles.dao.ProfessionMapper;
import com.jnshu.tiles.entity.Profession;
import com.jnshu.tiles.entity.ProfessionExample;
import com.jnshu.tiles.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionMapper professionMapper;

    public List<Profession> select(ProfessionExample professionExample) {
        return professionMapper.selectByExample(professionExample);
    }
}
