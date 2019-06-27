package jnshu.tiles.controller;


import jnshu.tiles.entity.*;
import jnshu.tiles.service.CompanyService;
import jnshu.tiles.service.UserService;
import jnshu.tiles.service.impl.ProfessionServiceImpl;
import jnshu.tiles.service.impl.StudentServiceImpl;
import jnshu.tiles.utils.JWTUtil;
import jnshu.tiles.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @Autowired
    private UserService userService;

    @RequestMapping("/u/one")
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

    @RequestMapping("/u/two")
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

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("myView4");
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView four(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        password= MD5.getMd5(password);
        criteria.andPasswordEqualTo(password);
       /* HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*30);
        Object number = session.getAttribute("number");
        if (null==number){
            session.setAttribute("number",1);
        }else {
            Object num = session.getAttribute("number");
            int num1 = (int) num;
            session.setAttribute("num",num1+1);
        }*/


        List<User> list =userService.findUser(userExample);


        if (list.size()>0){
            User user = list.get(0);
           /* user.getUsername();
            user.getPassword();*/
            String token = JWTUtil.generToken(String.valueOf(user.getId()), user.getUsername(), user.getPassword());
            Cookie c = new Cookie("token",token);
            c.setPath("/");
            c.setMaxAge(60);
            response.addCookie(c);
            ModelAndView modelAndView = new ModelAndView("myView5");
            /*HttpSession session = request.getSession(); seesion 统计人数 未完成
            session.setAttribute("peopel",);*/
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("myView4");
            modelAndView.addObject("msg","登录失败，用户名或密码错误，请重新输入");
            return modelAndView;
        }

    }

    @RequestMapping("/u/five")
    public ModelAndView five(){
        /*Cookie c = new Cookie();*/
        ModelAndView modelAndView = new ModelAndView("myView5");
        return modelAndView;
    }

    @RequestMapping("/u/loginOut")
    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        /*Cookie c = new Cookie();*/
        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("token",null);
        /*if (null != cookies){
            for (Cookie c:cookies){
                if (c.getName().equals("toekn")){
                    Cookie cookie =new Cookie("token",null);
                }
            }
        }*/
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        ModelAndView modelAndView = new ModelAndView("myView4");
        modelAndView.addObject("msg","已退出，请重新登录");
        return modelAndView;

    }

    @RequestMapping("/register")
    public ModelAndView register(String username,String password){
        ModelAndView modelAndView = new ModelAndView();
        password=MD5.getMd5(password);
        if (null==username||("").equals(username.trim())){
            modelAndView.addObject("msg","用户名不能为空");
            modelAndView.setViewName("myView6");
            return modelAndView;
        }
        if (null==password||("").equals(password.trim())){
            modelAndView.addObject("msg","密码不能为空");
            modelAndView.setViewName("myView6");
            return modelAndView;
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> user1 = userService.findUser(userExample);
        if (user1.size()>0){
            modelAndView.addObject("msg","该账户已被使用");
            modelAndView.setViewName("myView6");
            return modelAndView;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int i =userService.add(user);
        if (i<=0){
            modelAndView.addObject("msg","注册失败");
            modelAndView.setViewName("myView6");
            return modelAndView;
        }
        modelAndView.addObject("msg","注册成功，请登录");
        modelAndView.setViewName("myView4");
        return modelAndView;
    }
}
