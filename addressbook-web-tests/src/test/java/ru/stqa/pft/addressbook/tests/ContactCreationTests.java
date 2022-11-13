package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillNewContactForm(new ContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546", "test1"), true);
        app.getContactHelper().submitNewContactCreation();
        app.getContactHelper().returnToNewContactPage();
    }


}
