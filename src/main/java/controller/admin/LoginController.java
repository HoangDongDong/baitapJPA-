package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import service.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);
            session.setAttribute("roleId", user.getRoleId());

            switch (user.getRoleId()) {
                case 1: // User
                    response.sendRedirect(request.getContextPath() + "/user/home");
                    break;
                case 2: // Manager
                    response.sendRedirect(request.getContextPath() + "/manager/home");
                    break;
                case 3: // Admin
                    response.sendRedirect(request.getContextPath() + "/admin/home");
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid role.");
                    request.getRequestDispatcher("/views/admin/login.jsp").forward(request, response);
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/views/admin/login.jsp").forward(request, response);
        }
    }
}