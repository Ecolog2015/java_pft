package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.group().theGroupExists()) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData("test1", "test2", "test3"));
            app.goTo().newContactPage();
        }
    }
    @Test
    public void testNewContact() throws Exception {
        List<ContactData> before = app.contact().getContactList();
        app.goTo().newContactPage();
        ContactData contact = new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1");
        app.contact().createContact(contact);
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
