package site.qinkai.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.qinkai.pojo.Contact;
import site.qinkai.pojo.User;
import site.qinkai.service.ContactService;
import site.qinkai.util.ApplicationHelper;
import site.qinkai.util.JsonUtil;

@Controller
// @RequestMapping(value = "contact")
public class ContactController {

  @Autowired
  private ContactService contactService;

  // 查询通讯录列表
  @RequestMapping(value = "contact", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public List<Contact> findUserList(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Contact contact = new Contact();
    int ownerId = ApplicationHelper.StringToInt(request.getParameter("ownerId"));
    int id = ApplicationHelper.StringToInt(request.getParameter("id"));
    request.setCharacterEncoding("utf-8");
    contact.setOwner_id(ownerId);
    contact.setId(id);
    List<Contact> contactList = contactService.selectContactList(contact);
    return contactList;
  }

  // 删除不需要的联系人
  @RequestMapping(value = "contact/delete", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Map<String, String> delete(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    int contactId = 0;
    try {
      contactId = Integer.parseInt(request.getParameter("contactId"));
    } catch (Exception e) {
      response.setStatus(400);
      return null;
    }
    contactService.deleteContactById(contactId);
    return JsonUtil.okMsg();
  }

  // 修改联系人信息
  @RequestMapping(value = "contact/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Contact update(@RequestBody Map<String, String> map, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    request.setCharacterEncoding("utf-8");
    int id = 0;
    int gender = 0;
    try {
      System.out.println("here------");
      id = Integer.parseInt(map.get("id") == null ? "" : map.get("id").toString());
      gender = Integer.parseInt(map.get("gender") == null ? "" : map.get("gender").toString());
    } catch (Exception e) {
      response.setStatus(400);
      return null;
    }
    String name = (String) map.get("name");
    String phoneNum = (String) map.get("phoneNum");
    String remark = (String) map.get("remark");
    Contact contact = contactService.selectContactById(id);
    contact.setName(name);
    contact.setGender(gender);
    contact.setPhone_num(phoneNum);
    contact.setRemark(remark);
    contactService.updateContact(contact);
    return contact;
  }

  // 插入联系人信息
  @RequestMapping(value = "contact/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Contact insert(@RequestBody Map<String, Object> map, HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    request.setCharacterEncoding("utf-8");
    // 从session中获取到user对象
    User loginer = (User) session.getAttribute("user");
    int gender = 0;
    try {
      gender = Integer.parseInt(map.get("gender") == null ? "" : map.get("gender").toString());
    } catch (Exception e) {
      response.setStatus(400);
      return null;
    }
    String name = (String) map.get("name");
    String phoneNum = (String) map.get("phoneNum");
    String remark = (String) map.get("remark");
    Contact contact = new Contact();
    contact.setOwner_id(loginer.getId());
    contact.setName(name);
    contact.setGender(gender);
    contact.setPhone_num(phoneNum);
    contact.setRemark(remark);
    contact.setStatus(0);
    contactService.insertContact(contact);
    return contact;
  }

  @RequestMapping(value = "contact/export", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public void Export(HttpSession session) {
    User loginer = (User) session.getAttribute("user");
    String url = "/" + "通讯录联系人.xls";
    String filePath = ApplicationHelper.getFilePath();
    System.out.println("filepath: " + filePath);
    File file = new File(filePath + url);
    // 创建一个excel
    WritableWorkbook outbook;
    Label label = null;
    try {
      String fullPath = file.getAbsolutePath();
      if (!file.exists()) {
        File dir = null;
        if (fullPath.contains("\\")) {
          dir = new File(fullPath.substring(0, fullPath.lastIndexOf("\\")));
        } else {
          dir = new File(fullPath.substring(0, fullPath.lastIndexOf("/")));
        }
        dir.mkdirs();
        file.createNewFile();
      }

      outbook = Workbook.createWorkbook(file);
      // 创建一个表，参数为表名、位置
      WritableSheet sheet = outbook.createSheet("通讯表", 0);
      String[] contacts = new String[]{"序号", "用户名", "性别", "电话号码", "备注"};
      Contact contact = new Contact();
      contact.setOwner_id(loginer.getId());
      contact.setId(0);
      List<Contact> contactList = contactService.selectContactList(contact);
      // 添加表格的头部
      for (int first = 0; first < contacts.length; first++) {
        label = new Label(first, 0, contacts[first]);
        sheet.addCell(label);
      }
      for (int column = 0; column < contacts.length; column++) {
        for (int row = 0; row < contactList.size(); row++) {
          if (column == 0) {
            label = new Label(column, row + 1, " " + (row + 1));
          }
          if (column == 1) {
            label = new Label(column, row + 1, contactList.get(row).getName());
          }
          if (column == 2) {
            int gender = contactList.get(row).getGender();
            if (gender == 1) {
              label = new Label(column, row + 1, "男");
            }
            if (gender == 2) {
              label = new Label(column, row + 1, "女");
            }
          }
          if (column == 3) {
            label = new Label(column, row + 1, contactList.get(row).getPhone_num());
          }
          if (column == 4) {
            label = new Label(column, row + 1, contactList.get(row).getRemark());
          }
          sheet.addCell(label);
        }
      }
      System.out.print("here");
      outbook.write();
      outbook.close();

    } catch (Exception e) {
      System.out.println("wrong");
      e.printStackTrace();
    }
  }

}
