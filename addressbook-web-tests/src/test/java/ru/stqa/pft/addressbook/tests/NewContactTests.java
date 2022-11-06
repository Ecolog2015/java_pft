package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactData;


public class NewContactTests extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.getNavigationHelper().goToNewContactPage();
        app.getNewContactHelper().fillNewContactForm(new NewContactData("TestName", "TestMiddle", "TestName", "TestNickname", "TestCompany", "TestAddress", "45654", "654645", "456456", "456546"));
        app.getNewContactHelper().submitNewContactCreation();
        app.getNewContactHelper().returnToNewContactPage();
    }


}
