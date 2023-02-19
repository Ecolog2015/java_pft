package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;


public class TestBase {
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    public boolean isIssueOpen(int issueId) throws IOException {
        String json =  app.rest().getExecutor().execute
                (Request.Get("https://bugify.stqa.ru/api/issues.json?limit=100")).returnContent().asString();
        JsonElement parsed= JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement first = issues.getAsJsonArray().get(0) ;
        String issueStatus = first.getAsJsonObject().get("state_name").getAsString();
        return !issueStatus.equals("Resolved");

    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
