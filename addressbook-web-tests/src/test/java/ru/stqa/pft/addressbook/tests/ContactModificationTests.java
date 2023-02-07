package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1"));
        }
        app.getContactHelper().initContactModification(0);
        app.getContactHelper().fillNewContactForm(new ContactData("TestName1", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToNewContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
