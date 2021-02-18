package cn.dreamplume.project.shopping.domain;

/**
 * @Classname ShoppingUser
 * @Description TODO
 * @Date 2021/2/18 9:07
 * @Created by 翊
 */
public class ShoppingUser {
    private String userName;
    private String userPassword;
    private String userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
