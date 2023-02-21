package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (groups.size() == 0 || app.contact().groupExists(groups, "test1")) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (!app.contact().contactWithoutGroupExists(app.db().contacts(), "test1")) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("TestName")
                    .withMiddlename("TestMiddle")
                    .withLastname("TestName")
                    .withNickname("TestNickname")
                    .withCompany("TestCompany")
                    .withAddress("TestAddress")
                    .withHomephone("45654")
                    .withMobilephone("654645")
                    .withWorkphone("456456")
                    .withFaxphone("456546"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        GroupData groupData = app.contact().getGroupByName(groups, "test1");
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            Groups contactGroups = contact.getGroups();
            if (app.contact().groupExists(contactGroups, "test1")) {
                assert groupData != null;
                app.contact().addToGroup(contact, groupData);
                app.goTo().homePage();
            }
            GroupData afterGroup = app.db().groupById(groupData.getId());
            ContactData contactWithGroup = app.db().contactById(contact.getId());
            Assert.assertTrue(contactWithGroup.getGroups().contains(afterGroup));
            Assert.assertTrue(afterGroup.getContacts().contains(contactWithGroup));
        }
    }
}