package site.qinkai.dao;

import site.qinkai.pojo.User;

public interface UserMapper {

  User selectUserByName(String name);

  void insertUser(User user);

  User selectUserByPhoneNumber(String phoneNumber);
}
