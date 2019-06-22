package com.jnshu.tiles.service.impl;

import com.jnshu.tiles.dao.CompanyMapper;
import com.jnshu.tiles.entity.Company;
import com.jnshu.tiles.entity.CompanyExample;
import com.jnshu.tiles.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    @Override
    public Company selectById(Long id) {
        Company company = companyMapper.selectByPrimaryKey(id);
        return company;
    }

    @Override
    public List<Company> selectAll(CompanyExample companyExample) {
        List<Company> companyList = companyMapper.selectByExample(companyExample);
        return companyList;
    }
}
