package ua.dp.inco.config;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_TEACHER = "TEACHER";

    private static final Map<String, List<String>> mapConfig = new HashMap<String,List<String>>();

    static {
        init();
    }

    private static void init() {

        //Конфигурация роли студента
        List<String> urlStudentPatterns = new ArrayList<>();

        urlStudentPatterns.add("/questList");
        urlStudentPatterns.add("/userInfo");
        urlStudentPatterns.add("/spellsList");

        mapConfig.put(ROLE_STUDENT,urlStudentPatterns);

        //Конфигурация преподавателя
        List<String> urlTeacherPatterns = new ArrayList<>();

        urlTeacherPatterns.add("/userInfo");

        mapConfig.put(ROLE_TEACHER,urlTeacherPatterns);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role){
        return mapConfig.get(role);
    }
}
