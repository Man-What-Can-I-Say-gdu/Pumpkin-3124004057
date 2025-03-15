public class user {
    public String user_name;
    private String password;
    public String user_type;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int[] getUserId() {
        return UserId;
    }

    public void setUserId(int[] userId) {
        UserId = userId;
    }

    public int[] UserId;
}
