package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserFromDBbyCar(String model, int series);
    User getUser(long id);
    public void getAllUserNames();
    public void getAllUsersNamesAndID();
}
