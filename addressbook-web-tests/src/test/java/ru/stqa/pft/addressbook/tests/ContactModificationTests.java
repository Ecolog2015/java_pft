package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNewContactForm(new ContactData("TestName1", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToNewContactPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
