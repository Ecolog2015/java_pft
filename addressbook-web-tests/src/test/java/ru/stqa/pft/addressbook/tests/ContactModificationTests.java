package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNewContactForm(new NewContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToNewContactPage();
    }
}
