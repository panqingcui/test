package cjy.gc;

public class ReferenceCountingGc {
    public Object instance = null;
    private static final int _1M = 1024 * 1024;
    private byte[] bigSize = new byte[_1M * 2];

    public static void testGc() {
        ReferenceCountingGc gcA = new ReferenceCountingGc();
        ReferenceCountingGc gcB = new ReferenceCountingGc();
        gcA.instance = gcB;
        gcB.instance = gcA;
        gcA = null;
        gcB = null;
        System.gc();
    }

    public static void main(String[] args) {
        testGc();
    }
}
