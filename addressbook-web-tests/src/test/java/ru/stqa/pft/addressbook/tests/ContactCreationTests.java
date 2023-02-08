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
            app.group().create(new GroupData());
            app.goTo().newContactPage();
        }
    }
    @Test
    public void testNewContact() throws Exception {
        List<ContactData> before = app.contact().list();
        app.goTo().newContactPage();
        ContactData contact = new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
