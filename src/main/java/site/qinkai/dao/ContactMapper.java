package site.qinkai.dao;

import java.util.List;
import site.qinkai.pojo.Contact;

public interface ContactMapper {

  List<Contact> selectContactList(Contact contact);

  void deleteContactById(int id);

  Contact selectContactById(int id);

  void updateContact(Contact contact);

  void insertContact(Contact contact);
}
