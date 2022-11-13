package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToNewContactPage() {
        click(By.linkText("home page"));
    }

    public void submitNewContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillNewContactForm(NewContactData newContactData) {
        type(By.name("firstname"), newContactData.getFirstname());
        type(By.name("middlename"), newContactData.getMiddlename());
        type(By.name("lastname"), newContactData.getLastname());
        type(By.name("nickname"), newContactData.getNickname());
        type(By.name("company"), newContactData.getCompany());
        type(By.name("address"), newContactData.getAddress());
        type(By.name("home"), newContactData.getHomephone());
        type(By.name("mobile"), newContactData.getMobilephone());
        type(By.name("work"), newContactData.getWorkphone());
        type(By.name("fax"), newContactData.getFaxphone());
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
        click(By.id("MassCB"));
    }

    public void submitDeletionContact() {
        wd.switchTo().alert().accept();
    }
}
