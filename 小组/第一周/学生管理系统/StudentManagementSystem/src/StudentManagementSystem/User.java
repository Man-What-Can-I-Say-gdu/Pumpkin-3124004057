package StudentManagementSystem;



enum UserType{
    TypeIsStudent,
    TypeIsAdmin;
}

public class User {

    /*
    *
    *
    *
    *
    *
    *
    *
     */



    public String user_name;
    private String password;
    private int user_type;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type - 1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", user_type=" + user_type +
                '}';
    }
}
