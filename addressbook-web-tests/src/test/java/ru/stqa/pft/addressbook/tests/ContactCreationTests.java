package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

    @DataProvider //для JSON
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";

        String line = reader.readLine();

        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>() {
        }.getType());

        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testNewContact(ContactData contact) {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().newContactPage();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
