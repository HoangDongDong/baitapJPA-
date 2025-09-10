package controller.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.User;
import service.CategoryService;

import java.io.IOException;

@WebServlet("/manager/category/add")
public class CategoryAddController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/manager/addCategory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            String categoryName = request.getParameter("categoryName");
            Category newCategory = new Category(categoryName, loggedInUser);
            categoryService.addCategory(newCategory);
            response.sendRedirect(request.getContextPath() + "/manager/home");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}