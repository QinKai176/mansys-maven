package site.qinkai.service;

import site.qinkai.pojo.User;

public interface UserService {

  User selectUserByName(String name);

  void insertUser(User user);

  User selectUserByPhoneNumber(String phoneNumber);
}
