package ru.stqa.pft.sandbox;

public class PointDistance {
  public static void main(String[] args) {
    Point p = new Point(2, 6, 3, 6);
    System.out.println("Pасстояние между двумя точками " + "p1(" + p.x1 + ";" + p.y1 + ") и " + "p2(" + p.x2 + ";" + p.y2 + ")" + " = " + p.distance());
  }
}
