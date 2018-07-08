package site.qinkai.pojo;

public class Contact {

  private Integer id;

  private Integer owner_id;

  private String name;

  private Integer gender;// 0代表未知，1代表男，2代表女

  private String phone_num;

  private long created_at;

  private long updated_at;

  private String remark;// 备注信息

  private int status;// -1代表已删除

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOwner_id() {
    return owner_id;
  }

  public void setOwner_id(Integer owner_id) {
    this.owner_id = owner_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getPhone_num() {
    return phone_num;
  }

  public void setPhone_num(String phone_num) {
    this.phone_num = phone_num;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public long getCreated_at() {
    return created_at;
  }

  public void setCreated_at(long created_at) {
    this.created_at = created_at;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public long getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(long updated_at) {
    this.updated_at = updated_at;
  }

}
