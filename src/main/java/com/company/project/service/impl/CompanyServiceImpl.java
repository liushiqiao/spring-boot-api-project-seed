package com.company.project.service.impl;

import com.company.project.mapper.CompanyMapper;
import com.company.project.entry.model.Company;
import com.company.project.service.CompanyService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/05/23.
 */
@Service
@Transactional
public class CompanyServiceImpl extends AbstractService<Company> implements CompanyService {
    @Resource
    private CompanyMapper companyMapper;

}
