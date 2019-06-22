package com.jnshu.tiles.service;


import com.jnshu.tiles.entity.Company;
import com.jnshu.tiles.entity.CompanyExample;

import java.util.List;

public interface CompanyService {
    Company selectById(Long id);

    List<Company> selectAll(CompanyExample companyExample);
}
