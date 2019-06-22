package com.jnshu.tiles.controller;


import com.jnshu.tiles.entity.*;
import com.jnshu.tiles.service.CompanyService;
import com.jnshu.tiles.service.impl.ProfessionServiceImpl;
import com.jnshu.tiles.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private ProfessionServiceImpl professionService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/one")
    public ModelAndView one() {
        List<Student> students = studentService.selectAll();
        List<Student> list = new ArrayList<Student>();
        if (students.size()>4){
            list = students.subList(0, 4);
        }else {
            list= students;
        }
        ModelAndView model = new ModelAndView("myView1");
        model.addObject("list",list);
       // model.setViewName("myView1");
        //model.addAttribute("head","head1");
       // model.addAttribute("body","body1");
        //model.addAttribute("foot","foot1");
        return model; //这里的myView为layout.xml中配置的视图名称，根据返回值，去匹配对应的jsp页面
    }

    @RequestMapping("/two")
    public ModelAndView two() {
        ProfessionExample professionExample = new ProfessionExample();
        ProfessionExample.Criteria criteria = professionExample.createCriteria();
        criteria.andDirectionEqualTo("前端开发方向");
        List<Profession> flist =professionService.select(professionExample);
        professionExample.clear();
        ProfessionExample.Criteria criteria1 = professionExample.createCriteria();
        criteria1.andDirectionEqualTo("后端开发方向");
        List<Profession> blist = professionService.select(professionExample);
        professionExample.clear();
        ProfessionExample.Criteria criteria2 = professionExample.createCriteria();
        criteria2.andDirectionEqualTo("移动方向");
        List<Profession> mlist = professionService.select(professionExample);
        ModelAndView modelAndView = new ModelAndView("myView2");
        professionExample.clear();
        ProfessionExample.Criteria criteria3 = professionExample.createCriteria();
        criteria3.andDirectionEqualTo("运维方向");
        List<Profession> klist = professionService.select(professionExample);
        modelAndView.addObject("flist",flist);
        modelAndView.addObject("blist",blist);
        modelAndView.addObject("mlist",mlist);
        modelAndView.addObject("klist",klist);
        System.out.println(flist);
        return modelAndView; //这里的myView为layout.xml中配置的视图名称，根据返回值，去匹配对应的jsp页面
    }

    @RequestMapping("/three")
    public ModelAndView three(@RequestParam(defaultValue = "1") Long id) {
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andIdEqualTo(id);
        Company company =companyService.selectById(id);
        companyExample.clear();
        List<Company> companyList = companyService.selectAll(companyExample);
        // model.addAttribute("head","head1");
       // model.addAttribute("body","bod3");
       // model.addAttribute("foot","foot1");
        ModelAndView modelAndView = new ModelAndView("myView3");
        modelAndView.addObject("company",company);
        modelAndView.addObject("companyList",companyList);
        return modelAndView; //这里的myView为layout.xml中配置的视图名称，根据返回值，去匹配对应的jsp页面
    }
}
