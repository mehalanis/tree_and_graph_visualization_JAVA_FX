package Graphe;

public class Point {

    private double x;
    private double y;
    private double mt;
    private double r = 23;
    private double k;
    protected double x1, x2;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point a) {
        double dx = this.x - a.x;
        double dy = this.y - a.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double vecteur(Point b) {
        mt = ((b.y - this.y) / (b.x - this.x));
        return mt;
    }

    public double kVal() { // value of k for the line y = mt*x +k
        k = ((this.y) - (mt * this.x));
        return k;
    }

    /*
    public double vecteur(Point a ,Point b){
        mt = ((b.y-a.y)/(b.x-a.x));
        return mt;
    }*/
    public Point Center(Point p){
        return new Point();
    }
    public Point delta(Point z) {
        vecteur(z);
        kVal();
        double a, b, c, delta,x;
        a = 1 + mt * mt;
        b = ((-2 * this.x) + (-2 * this.y * mt) + (2 * mt * k));
        c = ((this.x * this.x) + (this.y * this.y) + (k * k) + (-2 * this.y * k) - r * r);

        delta = (b * b) - 4 * a * c;
        System.out.println("delta est : " + delta);
        System.out.println("a est : " + a);
        System.out.println("b est : " + b);
        System.out.println("c : " + c);
        if (delta < 0) {
            System.out.println("\n\nIl n'y a pas de racines reelle a l'equation.");
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            if(this.getX()>z.getX()){
                x=x1;
            }else{
                x=x2;
            }
            return new Point(x, mt*x +k); // l9itha x2 , x1 normalment x1 x2 no ??

        }
        return new Point(0, 0);
    }
    //public double 

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
