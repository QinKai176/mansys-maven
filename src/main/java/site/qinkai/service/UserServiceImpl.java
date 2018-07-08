package site.qinkai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.qinkai.dao.UserMapper;
import site.qinkai.pojo.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  public User selectUserByName(String name) {
    User user = userMapper.selectUserByName(name);
    return user;
  }

  public void insertUser(User user) {

    userMapper.insertUser(user);

  }

  public User selectUserByPhoneNumber(String phoneNumber) {
    User user = userMapper.selectUserByPhoneNumber(phoneNumber);
    return user;
  }

}
