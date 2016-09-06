package cjy.stringbuilder;

public class StringBuilderUtil {
    private StringBuilder sb = new StringBuilder();

    public void add() {
        sb.append("test1");
        sb.append("test2");
        sb = new StringBuilder();
        System.out.println(sb.toString());
    }
}
