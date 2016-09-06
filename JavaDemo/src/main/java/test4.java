/*
 * public class test4 { public static void main(String[] args) { double n = 6.0; double
 * sum = 0.0; double s = Math.pow(2, 5) * 6; int f = 1; for (int i = 0; i < 15; i++) { if
 * (i <= 5) { sum = sum + Math.pow(2, i) * 6; } if (i > 5) { sum = sum + s * Math.pow(1.5,
 * f); System.out.println("本期：" + s * Math.pow(1.5, f)); System.out.println(sum); f++; } }
 * System.out.println(sum); } public static long stringToLong(String ipaddress) { long[]
 * ip = new long[4]; int position1 = ipaddress.indexOf("."); int position2 =
 * ipaddress.indexOf(".", position1 + 1); int position3 = ipaddress.indexOf(".", position2
 * + 1); ip[0] = Long.parseLong(ipaddress.substring(0, position1)); ip[1] =
 * Long.parseLong(ipaddress.substring(position1 + 1, position2)); ip[2] =
 * Long.parseLong(ipaddress.substring(position2 + 1, position3)); ip[3] =
 * Long.parseLong(ipaddress.substring(position3 + 1)); return (ip[0] << 24) + (ip[1] <<
 * 16) + (ip[2] << 8) + ip[3]; } }
 */
