package site.qinkai.pojo;

public class User {

  private Integer id;

  private String username;

  private String password;

  private String phone_num;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone_num() {
    return phone_num;
  }

  public void setPhone_num(String phone_num) {
    this.phone_num = phone_num;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password
        + ", phone_num=" + phone_num + "]";
  }


}
