package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

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

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }
    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click(); //By.xpath("//img[@alt='Edit']")
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

    public List<ContactData> getContactList() {
        //список который будем заполнять
        List<ContactData> contacts = new ArrayList<ContactData>();

        //получаем список объектов
        List<WebElement> elements = wd.findElements(By.name("entry"));

        //пройти в цикле по элементам (строкам таблицы)
        for (WebElement element : elements) {
            //и из каждого получить text:  имя + фамилия контакта
            List<WebElement> cells = element.findElements(By.tagName("td"));
            ContactData contact = new ContactData(cells.get(2).getText(), cells.get(1).getText(), null, null, null, null, null,null,null,null,null);

            //добавляем созданный объект в contact
            contacts.add(contact);
        }
        return contacts;
    }
}
