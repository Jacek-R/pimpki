package model.coordinates;

public class CoordsDemo {


    public static void main(String[] args) {

        Coordinates c0 = new Coords(10000, 10000);
        Coordinates c1 = new Coords(1, 5);
        Coordinates z1 = new Coords(2, 5);
        Coordinates c2 = new Coords(5, 1);
        Coordinates c3 = new Coords(1, 5);
        Coordinates c4 = new Coords(1, 5);


        System.out.println(c0.hashCode());
        System.out.println(c1.hashCode());
        System.out.println(z1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());


        System.out.println("******");
        System.out.println(c0);
        System.out.println(c0.getNE());
        System.out.println(c0);
        System.out.println(c0.getN());
        System.out.println(c0.getNE());
        System.out.println(c0.getNW());
        System.out.println(c0.getW());
        System.out.println(c0.getS());
        System.out.println(c0);

        c0.getNeighbors().forEach(System.out::println);





    }
}
