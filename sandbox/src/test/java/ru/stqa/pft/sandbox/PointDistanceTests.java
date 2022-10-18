package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointDistanceTests {

  @Test
  public void testDistance() {
    Point p = new Point(2, 6, 3, 6);
    Assert.assertEquals(p.distance(), 5.0);
  }

  @Test
  public void testDistanceZero() {
    Point p = new Point(0, 0, 0, 0);
    Assert.assertEquals(p.distance(), 0.0);
  }

  @Test
  public void testDistanceNegativeValues() {
    Point p = new Point(1, 0, 1, 0);
    Assert.assertEquals(p.distance(), 1.4142135623730951);
  }
}
