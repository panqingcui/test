public class Test3 {
    public static void main(String[] args) {
        // String str = "yst.d.exe";
        // String reverse = reverse(str);
        // int len = reverse.indexOf(".");
        // String fx = reverse.substring(len + 1);
        // String zx = reverse(fx);
        // System.out.println(zx);
        String s = "123456";
        System.out.println(s.substring(s.length() - 1));
    }

    public static String reverse(String s) {
        int length = s.length();
        String reverse = "";
        for (int i = 0; i < length; i++)
            reverse = s.charAt(i) + reverse;
        return reverse;
    }
}
