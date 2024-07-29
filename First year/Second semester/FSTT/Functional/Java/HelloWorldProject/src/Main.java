public class Main {
public static void main(String[] args) {
    Shape rectangle = new Rectangle(5, 10);
    Shape circle = new Circle(5);
    System.out.println(rectangle.area());
    System.out.println(rectangle.perimeter());
    System.out.println(circle.area());
    System.out.println(circle.perimeter());

    assert rectangle.area() == 50;
    assert rectangle.perimeter() == 30;
    assert circle.area() == 78.53981633974483;
    assert circle.perimeter() == 31.41592653589793;
}
}
