package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/manager/*", "/user/*"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // Don't create if not exists

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        // Cho phép truy cập các trang công khai
        if (path.startsWith("/login") || path.startsWith("/register") || path.startsWith("/css/") || path.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("loggedInUser") == null) {
            httpResponse.sendRedirect(contextPath + "/login"); // Chuyển hướng về trang login nếu chưa đăng nhập
            return;
        }

        Integer roleId = (Integer) session.getAttribute("roleId");
        if (roleId == null) {
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

        // Logic lọc theo RoleId
        if (path.startsWith("/admin")) {
            if (roleId != 3) { // Chỉ Admin (roleId = 3) được truy cập
                httpResponse.sendRedirect(contextPath + "/403.jsp"); // Chuyển hướng đến trang 403 Forbidden
                return;
            }
        } else if (path.startsWith("/manager")) {
            if (roleId != 2 && roleId != 3) { // Manager (roleId = 2) và Admin (roleId = 3) được truy cập
                httpResponse.sendRedirect(contextPath + "/403.jsp");
                return;
            }
        } else if (path.startsWith("/user")) {
            if (roleId != 1 && roleId != 2 && roleId != 3) { // User (roleId = 1), Manager (roleId = 2) và Admin (roleId = 3) được truy cập
                httpResponse.sendRedirect(contextPath + "/403.jsp");
                return;
            }
        }

        // Nếu hợp lệ, tiếp tục chuỗi filter hoặc đến servlet đích
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên
    }
}