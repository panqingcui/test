package cjy.com.singleton;

public enum Elvis {
    INSTANCE("wangfabo", 1);
    private final int id;
    private final String name;

    Elvis(String n, int i) {
        System.out.println("Elvis create ... ");
        id = i;
        name = n;
    }

    public void leaveTheBuilding() {
        System.out.println("name:" + name + " id:" + id);
    }

    public Elvis getInstance() {
        return INSTANCE;
    }
}
