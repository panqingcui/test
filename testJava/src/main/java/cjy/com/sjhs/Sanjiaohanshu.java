package cjy.com.sjhs;

public class Sanjiaohanshu {
    public static void main(String[] args) {
        int degree;
        double pi = Math.PI;
        System.out.println("degree" + '\t' + "sin" + '\t' + '\t' + "cos" + '\t' + '\t' + "tan" + '\t' + '\t' + "cot");
        for (degree = 0; degree <= 30; degree++) {
            double a = Math.sin(degree * pi / 180);
            double b = Math.cos(degree * pi / 180);
            double c = (a / b);
            double d = (b / a);
            System.out.printf("%d\t%f\t%f\t%f\t%f\t", degree, a, b, c, d);
            System.out.println();
        }
    }
}
