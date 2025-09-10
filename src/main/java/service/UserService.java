package service;

import dao.UserDaoJPA;
import model.User;

public class UserService {
    private UserDaoJPA userDao = new UserDaoJPA();

    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) { // Lưu ý: Trong thực tế, cần mã hóa mật khẩu
            return user;
        }
        return null;
    }

    public void register(User user) {
        userDao.save(user);
    }

    public User findUserById(int id) {
        return userDao.findById(id);
    }
}