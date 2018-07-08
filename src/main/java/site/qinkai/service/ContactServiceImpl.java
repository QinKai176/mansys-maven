package site.qinkai.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.qinkai.dao.ContactMapper;
import site.qinkai.pojo.Contact;

@Service
public class ContactServiceImpl implements ContactService {

  @Autowired
  private ContactMapper contactMapper;

  public List<Contact> selectContactList(Contact contact) {
    List<Contact> contactList = contactMapper.selectContactList(contact);
    return contactList;
  }


  public void deleteContactById(int id) {
    contactMapper.deleteContactById(id);
  }


  public Contact selectContactById(int id) {
    Contact contact = contactMapper.selectContactById(id);
    return contact;
  }

  public void updateContact(Contact contact) {
    contact.setUpdated_at(System.currentTimeMillis());
    contactMapper.updateContact(contact);
  }

  public void insertContact(Contact contact) {
    contact.setCreated_at(System.currentTimeMillis());
    contact.setUpdated_at(System.currentTimeMillis());
    contactMapper.insertContact(contact);

  }

}
