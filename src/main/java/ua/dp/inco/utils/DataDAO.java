package ua.dp.inco.utils;

import ua.dp.inco.bean.UserAccount;
import ua.dp.inco.config.SecurityConfig;

import java.util.HashMap;
import java.util.Map;

public class DataDAO {
    public static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

    static {
        initUsers();
    }

    private static void initUsers() {
        UserAccount student = new UserAccount("student1","123",UserAccount.GENDER_MALE,
                SecurityConfig.ROLE_STUDENT);
        UserAccount teacher = new UserAccount("teacher","123",UserAccount.GENDER_FEMALE,
                SecurityConfig.ROLE_TEACHER,SecurityConfig.ROLE_STUDENT);

        mapUsers.put(student.getUserName(),student);
        mapUsers.put(teacher.getUserName(),teacher);
    }

    public static UserAccount findUser(String userName, String password) {
        UserAccount user = mapUsers.get(userName);
        if(user != null && user.getPassword().equals(password))
            return user;
        return null;
    }
}
