package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getRequestURI().matches(".*(css|jpg|png|gif|js)")) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login.jsp";
        String registerURI = request.getContextPath() + "/Register.jsp";
        String loginServlet = request.getContextPath() + "/login";
        String registerServlet = request.getContextPath() + "/register";
        System.out.println("Login URI : " + loginURI);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        System.out.println(loggedIn);
        boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(loginServlet);
        boolean registerRequest = request.getRequestURI().equals(registerURI) || request.getRequestURI().equals(registerServlet);
        System.out.println("Request URI : " + request.getRequestURI());
        if (loggedIn || loginRequest || registerRequest) {
            chain.doFilter(request, response);
        } else {
            System.out.println("not logged in");
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }

}