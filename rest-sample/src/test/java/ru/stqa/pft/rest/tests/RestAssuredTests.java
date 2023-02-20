package ru.stqa.pft.rest.tests;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {
    @BeforeClass
    public void init(){
        RestAssured.authentication=RestAssured.basic("7172fcb5f1888f5fac3dced24caeaa6a","");
    }
    @Test
    public void testCreateIssue() throws IOException {
        //skipIfNotFixed(2);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("test issue1").withDescription("New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();

        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    private Set<Issue> getIssues() {

        String json= RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=500").asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }


    private int createIssue(Issue newIssue) {

        String json= RestAssured.given()
                .param("subject", newIssue.getSubject())
                .param("description",newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = JsonParser.parseString(json);
        return  parsed.getAsJsonObject().get("issue_id").getAsInt();

    }
}
