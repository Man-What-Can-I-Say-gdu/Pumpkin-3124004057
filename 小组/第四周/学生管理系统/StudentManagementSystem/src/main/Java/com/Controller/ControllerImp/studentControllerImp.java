package com.Controller.ControllerImp;
import com.Service.ServiceImp.StudentServiceImp;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.dao.DaoImp.StudentDaoImp;
import com.entity.Course;
import com.entity.Student;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//接收来自StudentMajorWindows的所有url
@WebServlet("/StudentMajorWindow/*")
public class studentControllerImp extends HttpServlet implements HttpSession {
    //创建接口实现类的对象，通过实现类调用实现类中的父类UserService对象获取用户id
    private StudentServiceImp ServiceImp;
    //初始化，手动添加依赖链
    @Override
    public void init(){
        StudentDaoImp studentDao = new StudentDaoImp();
        this.ServiceImp = new StudentServiceImp(studentDao);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        this.doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {


        //设置响应头
        setCorsHeaders(req, res);
        System.out.println("设置响应头");
        if("/MyCourse".equals(req.getPathInfo())){
            //返回课程数据
            ReturnChosenCourse(req,res);
        }else if("/ChoseCourse/GetUnChosenCourse".equals(req.getPathInfo())){
            //进入选课界面，先展示可以选择的课程，并根据可以选择的课程返回可以选择的课程的数量
            GetUnChosenCourse(req,res);
        }else if("/ChoseCourse/ChoseCourse".equals(req.getPathInfo())){
            //选课

        }else if("/SubmitLoginUser".equals(req.getPathInfo())){
            //将用户数据存放到session中
            //获取学生对象，通过学生对象获取课程数据
            Student student;
            try {
                student = ParseToStudent(req);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            //设置响应头
            setCorsHeaders(req, res);
            //获取学生对象的id
            try {
                student.setId(ServiceImp.getStudentDao().getAllByName(student.getUser_name()).getId());
                //没有正确获取全部信息
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            SaveStudent(student,req,res);
            Map<String,String> map = new HashMap<>();
            map.put("success","true");
            res.setContentType("application/json;charset=utf-8");
            String json = JSON.toJSONString(map);
            try {
                PrintWriter out = res.getWriter();
                out.write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取学生未选课程
     * @param resp 响应的数据
     */
    private void GetUnChosenCourse(HttpServletRequest req, HttpServletResponse resp) {
        //获取未选择的course数据
        ArrayList<Course> UnChosenCourse;
        //TakeOutStudent为空
        System.out.println("Session ID: " + req.getSession().getId());
        System.out.println("Session contains MyStudent: " +(req.getSession().getAttribute("MyStudent") != null));

        UnChosenCourse = ServiceImp.GetUnChosenCourse(TakeOutStudent(req).getId());

        //设置返回信息
        resp.setContentType("application/json;charset=utf-8");
        Map<String,Object> ResponseData = new HashMap<>();
        ResponseData.put("UnChosenCourse",UnChosenCourse);
        ResponseData.put("success",true);
        ResponseData.put("RestChosenNumb",6-UnChosenCourse.size());
        //转化为Json字符串
        String ResponseJson = JSON.toJSONString(ResponseData);
        try{
            resp.setStatus(200);
            PrintWriter out = resp.getWriter();
            out.print(ResponseJson);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    //设置相应头
    private static void setCorsHeaders(HttpServletRequest req, HttpServletResponse res) {
        String origin = req.getHeader("Origin");
        res.setHeader("Access-Control-Allow-Origin", origin);
        // 其他必要头部
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Max-Age", "3600");
    }

    /**
     * 实现选课功能
     * @param req 传入数据
     * @param resp 响应数据
     */
    private void ChoseCourse(HttpServletRequest req, HttpServletResponse resp) {
        //获取学生信息
        Student student = TakeOutStudent(req);
        //获取需要选的课程Id
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = req.getReader()){
            String line;
            while((line = reader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        int course_id = JSON.parseObject(stringBuilder.toString(),int.class);
        //调用选课方法并返回成功信息
        resp.setContentType("application/json;charset=utf-8");
        resp.setStatus(200);
        setCorsHeaders(req,resp);
        if(ServiceImp.ChooseCourse(course_id,student.getId())){
            try {
                Map<String,Object> ResponseData = new HashMap<>();
                ResponseData.put("success",true);
                String ResponseJson = JSON.toJSONString(ResponseData);
                PrintWriter out = resp.getWriter();
                out.print(ResponseJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取session中的student对象
     * @param req 前端发送的请求
     * @return 学生信息
     */
    private Student TakeOutStudent(HttpServletRequest req) {
        return (Student)(req.getSession().getAttribute("MyStudent"));
    }

    /**
     * 将已选的课程信息返回给浏览器
     * @param response 相应信息
     */
    private void ReturnChosenCourse(HttpServletRequest req,HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> ResponseInfor = new HashMap<String,Object>();
        ArrayList<Course> MyCourses;
        //获取课程对象
        MyCourses = GetChosenCourses(TakeOutStudent(req));
        //设置响应码
        ResponseInfor.put("Courses",MyCourses);
        ResponseInfor.put("success",true);
        //将成功消息与课程消息封装到Map对象中返回给客户端
        String MyCoursesJSONString = JSON.toJSONString(ResponseInfor);
        //返回给客户端
        try {
            //设置返回成功
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.println(MyCoursesJSONString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 取出页面传入的学生对象
     * @param req 页面传入的请求
     * @return 页面传入的学生对象的信息
     * @throws UnsupportedEncodingException 不允许的编码异常
     */
    private static Student ParseToStudent(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = req.getReader()){
            String line;
            while((line = reader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return JSON.parseObject(stringBuilder.toString(), Student.class);
    }

    /**
     * 将得到的student对象存放到session中
     * @param student 接收的学生数据
     */
    private void SaveStudent(Student student,HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("MyStudent",student);
    }

    /**
     * 调用Service获取课程数组并返回
     * @param student 获取课程信息的学生对象
     * @return 课程数据
     */
    private ArrayList<Course> GetChosenCourses(Student student){
        //student未正确初始化：没有获取Id
        return this.ServiceImp.CheckChosenCourse(student.getId());
    }



    @Override
    protected void doOptions(HttpServletRequest request,HttpServletResponse resp) throws ServletException, IOException {
        //设置响应头
        setCorsHeaders(request, resp);
        //直接返回200
        resp.setStatus(200);
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
