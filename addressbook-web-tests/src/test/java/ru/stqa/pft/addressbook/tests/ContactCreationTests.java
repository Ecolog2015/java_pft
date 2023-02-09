package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (!app.group().theGroupExists()) {
            app.group().create(new GroupData());
        }
    }

    @Test
    public void testNewContact() throws Exception {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        app.goTo().newContactPage();
        ContactData contact = new ContactData().withFirstname("TestName").withMiddlename("TestMiddle").withLastname("TestName").withNickname("TestNickname").withCompany("TestCompany").withAddress("TestAddress").withHomephone("45654").withMobilephone("654645").withWorkphone("456456").withFaxphone("456546").withGroup("test1");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
