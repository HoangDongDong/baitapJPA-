package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.UserService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Mặc định cho người dùng đăng ký là Role 1 (User)
        int roleId = 1; 

        User existingUser = userService.login(username, password); // Check if username already exists
        if (existingUser != null) {
            request.setAttribute("errorMessage", "Username already exists.");
            request.getRequestDispatcher("/views/admin/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(username, password, roleId);
        userService.register(newUser);

        request.setAttribute("successMessage", "Registration successful! Please login.");
        request.getRequestDispatcher("/views/admin/login.jsp").forward(request, response);
    }
}