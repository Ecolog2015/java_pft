package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    private Contacts contactCache = null;

    public void returnToNewContactPage() {
        click(By.linkText("home page"));
    }

    public void submitNewContactCreation() {
        click(By.name("submit"));
    }

    public void fillNewContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("fax"), contactData.getFaxphone());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } else new Select(wd.findElement(By.name("new_group"))).getFirstSelectedOption();
        } else Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    public void initContactModification(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "'" + "]")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitDeletionContact() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        click(By.linkText("add new"));
        fillNewContactForm((contact), true);
        submitNewContactCreation();
        contactCache = null;
        returnToNewContactPage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillNewContactForm(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectContact();
        contactCache = null;
        submitDeletionContact();
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));

            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmail(allEmail).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withAddress(address)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withHomephone(home)
                .withMobilephone(mobile)
                .withWorkphone(work);
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void addToGroup(ContactData toBeAddContact, GroupData group) {
        selectContactById(toBeAddContact.getId());
        wd.findElement(By.name("to_group")).click();
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
        wd.findElement(By.name("add")).click();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(group.getId()));
        selectContactById(contact.getId());
        wd.findElement(By.name("remove")).click();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public boolean groupExists(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean contactsExists(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name) && group.getContacts().size() > 0) {
                return true;
            }
        }
        return false;
    }

    public GroupData getGroupByName(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }
    public boolean contactWithoutGroupExists(Contacts contacts, String name) {
        for (ContactData contact : contacts) {
            if (!groupExists(contact.getGroups(), name)) {
                return true;
            }
        }
        return false;
    }
    public  ContactData selectContactId (Contacts contacts, String name) {
        for (ContactData contact : contacts) {
            if (!groupExists(contact.getGroups(), name)) {
                return contact;
            }
        }
        return null;
    }
}
