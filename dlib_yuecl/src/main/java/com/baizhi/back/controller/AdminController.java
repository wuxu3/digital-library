package com.baizhi.back.controller;

import com.baizhi.back.service.AdminService;
import com.baizhi.common.entity.Admin;
import com.baizhi.common.entity.Result;
import com.baizhi.common.util.VerifyCodeUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping("/register")
    @ResponseBody
    public Result register(Admin admin, HttpSession session, String code) {

        Result result = new Result();

        try {
            String vcode = (String) session.getAttribute("code");

            if (vcode.equalsIgnoreCase(code)) {

                adminService.register(admin);

                session.setAttribute("admin", admin);

                result.setSuccess(true);
                result.setMsg("注册成功");

            } else {

                throw new RuntimeException("验证码输入错误");
            }


        } catch (Exception e) {
            e.printStackTrace();

            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }


    @RequestMapping("login")
    @ResponseBody
    public Result login(Admin admin, HttpSession session) {

        Result result = new Result();

        try {
            Admin adminDB = adminService.login(admin);

            session.setAttribute("admin", adminDB);

            result.setSuccess(true);
            result.setMsg("登录成功");
        } catch (Exception e) {
            e.printStackTrace();

            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }


    @RequestMapping("loginout")
    public String loginout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);  //防止创建session

        session.removeAttribute("admin");  //登出以后将session 里面的user移除

        return "redirect:/back/main/menu.jsp";
    }


    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {

        //生成验证码
        String validatecode = VerifyCodeUtil.generateVerifyCode(4);

        //放入session中
        session.setAttribute("code", validatecode);
        //生成验证码图片
        BufferedImage image = VerifyCodeUtil.getImage(120, 35, validatecode);

        //响应图片到页面
        //设置响应类型
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        //输出图片
        ImageIO.write(image, "png", os);

        //关闭流
        IOUtils.closeQuietly(os);
    }

}
