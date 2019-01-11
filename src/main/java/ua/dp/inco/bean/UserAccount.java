package ua.dp.inco.bean;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";

    private long userId;
    private String userName;
    private String gender;
    private String password;
    private String firstName;
    private String lastName;
    private int age;

    private List<String> roles;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, String gender, String... roles) {
        this.userName = userName;
        this.gender = gender;
        this.password = password;

        this.roles = new ArrayList<String>();
        if(roles != null)
            for (String r: roles) {
                this.roles.add(r);
            }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
