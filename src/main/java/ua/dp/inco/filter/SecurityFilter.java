package ua.dp.inco.filter;

import ua.dp.inco.bean.UserAccount;
import ua.dp.inco.request.UserRoleRequestWrapper;
import ua.dp.inco.utils.AppUtils;
import ua.dp.inco.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebFilter("/*")
public class SecurityFilter implements Filter {
    public SecurityFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());

        if(servletPath.equals("/login")) {
            filterChain.doFilter(request,response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if(loginedUser != null) {
            //User name
            String userName = loginedUser.getUserName();

            //Role
            List<String> roles = loginedUser.getRoles();

            // Старый пакет request с помощью нового Request с информацией userName и Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

        // Страницы требующие входа в систему.
        if(SecurityUtils.isSecurityPage(request)) {

            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if(loginedUser == null) {

                String requestUri = request.getRequestURI();

                // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(),requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission = SecurityUtils.hasPermission(request);
            if(!hasPermission) {
                RequestDispatcher dispatcher
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
                dispatcher.forward(request,response);
                return;
            }
        }
        filterChain.doFilter(wrapRequest,response);
    }

    @Override
    public void destroy(){

    }

}
