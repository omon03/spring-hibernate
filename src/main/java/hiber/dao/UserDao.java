package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUserByCar(String model, int series);
    User getUser(long id);
    public void getAllUserNames();
    public void getAllUsersNamesAndID();
}
