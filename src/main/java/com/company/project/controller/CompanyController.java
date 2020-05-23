package com.company.project.controller;
import com.company.project.entry.vo.Result;
import com.company.project.core.ReponseEntry;
import com.company.project.entry.model.Company;
import com.company.project.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/05/23.
*/
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @PostMapping("/add")
    public Result add(Company company) {
        companyService.save(company);
        return ReponseEntry.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        companyService.deleteById(id);
        return ReponseEntry.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Company company) {
        companyService.update(company);
        return ReponseEntry.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Company company = companyService.findById(id);
        return ReponseEntry.genSuccessResult(company);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Company> list = companyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ReponseEntry.genSuccessResult(pageInfo);
    }
}
