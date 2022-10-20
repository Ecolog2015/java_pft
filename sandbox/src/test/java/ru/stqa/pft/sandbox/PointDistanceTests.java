package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointDistanceTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(6, 6);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testDistanceZero() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testDistanceNegativeValues() {
    Point p1 = new Point(1, 0);
    Point p2 = new Point(0, 1);
    Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
  }
}
