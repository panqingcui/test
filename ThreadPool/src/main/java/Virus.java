public class Virus {
    public static void main(String args[]) {
        for (int i = 1;; i++) {
            double dv[][][][][][] = new double[i][i + 1][i + 1][i + 1][i + 1][i + 1];
            try {
                dv[i - 1][i - 1][i - 1][i - 1][i - 1][i - 1] = Double.MAX_VALUE;
                System.out.println(
                        "dv[" + i + "][" + i + "][" + i + "] = " + dv[i - 1][i - 1][i - 1][i - 1][i - 1][i - 1]);
            } catch (Exception e) {}
        }
        // recursiveMethod();
    }
    // public static void recursiveMethod() {
    // for (int i = 0; i < 10000; i++)
    // i += i;
    // recursiveMethod();
    // }
}
