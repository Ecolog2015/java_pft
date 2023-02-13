package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {

    //предусловия теста
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("TestName").withMiddlename("TestMiddle").withLastname("TestName").withNickname("TestNickname").withCompany("TestCompany").withAddress("TestAddress").withEmail("email@email.ru").withEmail2("email2@email.ru").withEmail3("email3@email.ru").withHomephone("45654").withMobilephone("654645").withWorkphone("456456").withFaxphone("456546").withGroup("test1"));
        }
    }

    @Test
    public void testContactInformation() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
        assertThat(contact.getAllEmail(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactInformationTests::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    private String mergeAddress(ContactData contact) {
        return Stream.of(contact.getAddress()).filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).filter((s) -> !s.equals(""))
                .map(ContactInformationTests::cleanedEmail)
                .collect(Collectors.joining("\n"));
    }
    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
    public static String cleanedEmail(String email) {
        return email.replaceAll("\\s+", " ");
    }
}
