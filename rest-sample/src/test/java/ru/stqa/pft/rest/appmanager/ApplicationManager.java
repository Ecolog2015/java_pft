package ru.stqa.pft.rest.appmanager;


import org.openqa.selenium.WebDriver;

import java.util.Properties;


public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RestHelper restHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        String target = System.getProperty("target", "local");
        properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RestHelper rest() {
        if (restHelper == null) {
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }
}