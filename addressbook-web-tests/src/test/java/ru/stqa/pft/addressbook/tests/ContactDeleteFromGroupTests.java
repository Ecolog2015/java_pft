
package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (groups.size() == 0 || !app.contact().groupExists(groups, "test1")) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (!app.contact().contactsExists(groups, "test1")) {
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
                    .withFaxphone("456546")
                    .inGroup(app.contact().getGroupByName(groups, "test1")));
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        Groups groups = app.db().groups();
        GroupData groupData = app.contact().getGroupByName(groups, "test1");
        assert groupData != null;
        ContactData contact = groupData.getContacts().stream().findFirst().get();
        app.contact().deleteContactFromGroup(contact, groupData);
        app.goTo().homePage();
        ContactData contactWithOutGroup = app.db().contactById(contact.getId());
        GroupData afterGroup = app.db().groupById(groupData.getId());
        Assert.assertFalse(contactWithOutGroup.getGroups().contains(afterGroup));
        Assert.assertFalse(afterGroup.getContacts().contains(contactWithOutGroup));
    }

}
