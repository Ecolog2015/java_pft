package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;


public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.getNavigationHelper().goToNewContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        if (!app.getGroupHelper().theGroupExists()) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
            app.getNavigationHelper().goToNewContactPage();
        }
        app.getContactHelper().createContact(new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1"));
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }


}
