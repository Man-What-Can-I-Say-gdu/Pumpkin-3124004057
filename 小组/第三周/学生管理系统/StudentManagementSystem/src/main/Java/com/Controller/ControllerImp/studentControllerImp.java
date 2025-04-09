package com.Controller.ControllerImp;
import com.Service.ServiceImp.StudentServiceImp;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.dao.DaoImp.StudentDaoImp;
import com.entity.Course;
import com.entity.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
//接收来自StudentMajorWindow的数据
@WebServlet("/MyCourse")
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
        System.out.println("进入Post");
        //获取学生对象，通过学生对象获取课程数据
        Student student;
        try {

            student = ParseToStudent(req);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        setCorsHeaders(req, res);


        //获取学生对象的id
        try {
            student.setId(ServiceImp.getStudentDao().getAllByName(student.getUser_name()).getId());
            //没有正确获取全部信息
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if("/MyCourse".equals(req.getServletPath())){
            //返回课程数据
            ReturnChosenCourse(student,res);
        }
    }
    //设置相应头
    private static void setCorsHeaders(HttpServletRequest req, HttpServletResponse res) {
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "content-type");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Credentials", "true");
    }

    /**
     * 将已选的课程信息返回给浏览器
     * @param student 获取课程信息的学生对象
     * @param response 相应信息
     */
    private void ReturnChosenCourse(Student student,HttpServletResponse response) {
        ArrayList<Course> MyCourses;
        //获取课程对象
        MyCourses = GetChosenCourses(student);
        //设置响应码
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        //获得JSON字符串并将字符串替换成Json对象，用于返回会给客户端
        String CourseJsonString = JSONObject.toJSONString(MyCourses);
        //返回给客户端
        try {
            //设置返回成功
            response.getWriter().print("{\"success\":true}");
            response.getWriter().write("{\"Courses\":"+CourseJsonString+"}");
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
     * 调用Service获取课程数组并返回
     * @param student 获取课程信息的学生对象
     * @return 课程数据
     */
    private ArrayList<Course> GetChosenCourses(Student student){
        //student未正确初始化：没有获取Id
        System.out.println(student.getId());
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
