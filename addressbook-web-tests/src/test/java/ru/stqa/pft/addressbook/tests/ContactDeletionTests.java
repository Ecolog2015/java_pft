package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1"));
        }
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectContact();
        app.getContactHelper().submitDeletionContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
