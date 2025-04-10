package com.Controller.ControllerImp;

import com.Controller.userController;
import com.Service.ServiceImp.UserServiceImp;
import com.alibaba.fastjson2.JSON;
import com.dao.DaoImp.UserDaoImp;
import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Objects;

@WebServlet({"/login","/register"})
public class userControllerImp extends HttpServlet implements userController, HttpSession {
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
        try {
            doPost(req,resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 重写doPost方法，对传入的request的post请求进行判断并执行相应的功能
     * @param req 传入的请求
     * @param resp 返回的相应
     * @throws IOException 抛出异常的输入输出流
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCorsHeaders(resp,req.getHeader("Origin"));
        //获取user对象
        //没有正确发送Post的请求体
        User user = parseToUser(req);
        //判断请求的页面
        if("/login".equals(req.getServletPath()) && user != null){
            LoginController(resp, user);
            //使用session传递密码和用户名

        }else if("/register".equals(req.getServletPath()) && user != null){
            RegisterController(resp, user);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request,HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp,request.getHeader("Origin"));
        resp.setStatus(200);
    }


    /**
     * 获取request中的User数据
     * @param request 传入的request对象
     * @return 返回从请求中提取的user对象
     * @throws UnsupportedEncodingException 不允许的编码异常
     */
    private User parseToUser(HttpServletRequest request) throws UnsupportedEncodingException {
        //先设置字符输入流编码
        request.setCharacterEncoding("UTF-8");
        //使用StringBuilder读取json对象
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = request.getReader()){
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return JSON.parseObject(stringBuilder.toString(),User.class);
    }

    //设置响应头
    private void setCorsHeaders(HttpServletResponse resp, String origin) {
        resp.setHeader("Access-Control-Allow-Origin", origin);
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
    }



    @Override
    public void LoginController(HttpServletResponse resp , User user) {
        //请求页面为登录页面并且传入的用户数据不为空
        //调用service,获得登录对象
        user =  userServiceImp.Login(user.getUser_name(), user.getPassword());
        //设置返回结果格式和编码集
        resp.setContentType("application/json;charset=utf-8");
        if(user ==null){
            //登录失败，设置401状态码
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                resp.getWriter().print("{\"success\": false, \"message\": \"No Such User\"}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
        }
        if (user != null) {
            // 登录成功，设置200状态码
            resp.setStatus(HttpServletResponse.SC_OK);
            try {
                resp.getWriter().print("{\"success\": true}");
                //返回一个session对象
                HttpSession session = this;
                session.setAttribute("user", user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 登录失败，设置401状态码
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                resp.getWriter().print("{\"success\": false, \"message\": \"用户名或密码错误\"}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void RegisterController(HttpServletResponse resp , User user) {
        //请求页面为注册页面并且传入的用户数据不为空
        boolean IsRegister =  userServiceImp.Register(user);
        try{
            if(IsRegister){
                //注册成功，返回success
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().print("{\"success\": true}");
            }else{
                //注册失败返回false
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().print("{\"success\": false}");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DisplayMainWindow(User user) {

    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Object getValue(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void putValue(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public void removeValue(String s) {

    }

    @Override
    public void invalidate() {

    }

    @Override
    public boolean isNew() {
        return false;
    }
}
