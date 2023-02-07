package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

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
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("fax"), contactData.getFaxphone());

        if (creation) {
            if (isElementPresent(By.name(contactData.getGroup()))) {                                             //пытаемся выбрать из списка групп элемент
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else new Select(wd.findElement(By.name("new_group"))).getFirstSelectedOption();
        } else Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void submitDeletionContact() {
        wd.switchTo().alert().accept();
    }

    public void createContact(ContactData contact) {
        click(By.linkText("add new"));
        fillNewContactForm((contact), true);
        submitNewContactCreation();
        returnToNewContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]")); //проверка наличия хоть 1 контакта
    }

    public int getContactCount() {
        return wd.findElements(By.name("entry")).size();
    }
}
