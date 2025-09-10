package service;

import dao.CategoryDaoJPA;
import model.Category;
import model.User;

import java.util.List;

public class CategoryService {
    private CategoryDaoJPA categoryDao = new CategoryDaoJPA();

    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    public void deleteCategory(int id) {
        categoryDao.delete(id);
    }

    public Category getCategoryById(int id) {
        return categoryDao.findById(id);
    }

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    public List<Category> getCategoriesByUser(User user) {
        return categoryDao.findCategoriesByUser(user);
    }
}