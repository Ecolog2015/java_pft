package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroupTests extends TestBase {

    //предусловия теста
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("TestName").withMiddlename("TestMiddle").withLastname("TestName").withNickname("TestNickname").withCompany("TestCompany").withAddress("TestAddress").withHomephone("45654").withMobilephone("654645").withWorkphone("456456").withFaxphone("456546").withGroup("test1"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        app.contact().findContact(contacts, groups, true);
        ContactData selectedContact = app.contact().findContact(contacts, groups, true);
        GroupData selectedGroup = app.contact().getGroupData(groups, selectedContact, true);
        verifyContactListInUI();
    }
}