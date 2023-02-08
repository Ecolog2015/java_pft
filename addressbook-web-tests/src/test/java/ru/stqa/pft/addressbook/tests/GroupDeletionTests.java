package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()) {
            app.group().createGroup(new GroupData("test1", null, null));
        }
    }
    @Test
    public void testGroupDeletion() throws Exception {
        List<GroupData> before = app.group().getGroupList();
        app.group().selectGroup(before.size() - 1);
        app.group().deleteSelectGroups();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
