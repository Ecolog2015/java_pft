package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()) {
            app.group().createGroup(new GroupData("test1", null, null)); //было "test1", "test2", "test3"
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().getGroupList();
        int index = before.size() -1;
        GroupData group = new GroupData(before.get(index).getId(), "test1", "test2", "test3");
        app.group().modifyGroup(index, group);
        List<GroupData> after = app.group().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
