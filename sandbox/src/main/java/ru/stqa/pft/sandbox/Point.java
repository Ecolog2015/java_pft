package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;


  public Point(double x1, double y1) {
    this.x = x1;
    this.y = y1;
  }


  public double distance(Point p2) {
    double d = Math.sqrt(Math.pow((p2.x - this.x), 2) + Math.pow((p2.y - this.y), 2));
    return d;
  }
}
