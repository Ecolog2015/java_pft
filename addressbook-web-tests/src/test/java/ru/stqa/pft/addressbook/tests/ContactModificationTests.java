package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.goTo().groupPage();
            if (!app.group().theGroupExists()) {
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.contact().create(new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1"));
            ;
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1"));
        }
        List<ContactData> before = app.contact().list();
        app.contact().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "TestName1", "TestMiddle", "TestName1", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", null);
        app.contact().fillNewContactForm(contact, false);
        app.contact().submitContactModification();
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
