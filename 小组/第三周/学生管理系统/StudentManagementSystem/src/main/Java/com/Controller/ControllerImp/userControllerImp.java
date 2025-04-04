package com.Controller.ControllerImp;

import com.Service.ServiceImp.UserServiceImp;
import com.alibaba.fastjson2.JSON;
import com.dao.DaoImp.UserDaoImp;
import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@WebServlet({"/login","/register"})
public class userControllerImp extends HttpServlet {
    private UserServiceImp userServiceImp;
    @Override
    public void init() {
        //手动创建依赖链
        UserDao userDao = new UserDaoImp();
        this.userServiceImp = new UserServiceImp(userDao);
    }

    public userControllerImp(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        setCorsHeaders(resp,req.getHeader("Origin"));



        //获取user对象
        //没有正确发送Post的请求体
        User user = parseToUser(req);

        //判断请求的页面
        if("/login".equals(req.getServletPath()) && user != null){
            //请求页面为登录页面并且传入的用户数据不为空
            //调用service,获得登录对象
            user =  userServiceImp.Login(user.getUser_name(), user.getPassword());
            System.out.println(user);
            //设置返回结果格式和编码集
            resp.setContentType("application/json;charset=utf-8");
            if(user==null){
                System.out.println("1");
            }else{
                System.out.println("2");
            }
            if (user != null) {
                // 登录成功，设置200状态码
                resp.setStatus(HttpServletResponse.SC_OK);
                System.out.println("登录成功");
                resp.getWriter().print("{\"success\": true}");
            } else {
                // 登录失败，设置401状态码
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().print("{\"success\": false, \"message\": \"用户名或密码错误\"}");
                System.out.println("登录失败");
            }
        }else if("/register".equals(req.getServletPath()) && user != null){
            //请求页面为注册页面并且传入的用户数据不为空
            boolean IsRegister =  userServiceImp.Register(user);
            System.out.println(user);
            System.out.println(IsRegister);
            if(IsRegister){
                //注册成功，返回success
                System.out.println("注册成功");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().print("{\"success\": true}");
            }else{
                //注册失败返回false
                System.out.println("注册失败");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().print("{\"success\": false}");
            }
        }
    }
    @Override
    protected void doOptions(HttpServletRequest request,HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp,request.getHeader("Origin"));
        resp.setStatus(200);
        System.out.println("成功返回了");
    }





    private User parseToUser(HttpServletRequest request) throws UnsupportedEncodingException {
        //先设置字符输入流编码
        request.setCharacterEncoding("UTF-8");
        //使用StringBuilder读取json对象
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = request.getReader()){
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
                System.out.println("line:"+line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return JSON.parseObject(stringBuilder.toString(),User.class);
    }


    private void setCorsHeaders(HttpServletResponse resp, String origin) {
        resp.setHeader("Access-Control-Allow-Origin", origin);
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
    }

}
