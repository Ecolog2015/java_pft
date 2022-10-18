package ru.stqa.pft.sandbox;

public class PointDistance {
  public static void main(String[] args) {
    Point p1 = new Point();
    p1.x1 = 2;
    p1.y1 = 3;
    Point p2 = new Point();
    p2.x2 = 6;
    p2.y2 = 6;
    System.out.println("Pасстояние между двумя точками " + "p1(" + p1.x1 + ";" + p1.y1 + ") и " + "p2(" + p2.x2 + ";" + p2.y2 + ")" + " = " + distance(p1, p2));
  }
  public static double distance(Point p1, Point p2){
    double d = Math.sqrt(Math.pow((p2.x2 - p1.x1),2) + Math.pow((p2.y2 - p1.y1),2));
    return d;
  }
}
