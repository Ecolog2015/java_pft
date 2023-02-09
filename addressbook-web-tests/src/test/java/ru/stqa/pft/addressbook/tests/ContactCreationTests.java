package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
        Contacts before = app.contact().all();
        app.goTo().newContactPage();
        ContactData contact = new ContactData().withFirstname("TestName").withMiddlename("TestMiddle").withLastname("TestName").withNickname("TestNickname").withCompany("TestCompany").withAddress("TestAddress").withHomephone("45654").withMobilephone("654645").withWorkphone("456456").withFaxphone("456546").withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
