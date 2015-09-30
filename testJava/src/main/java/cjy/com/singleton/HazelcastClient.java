package cjy.com.singleton;

public class HazelcastClient {
    private static class HazelcastClientHolder {
        private static final HazelcastClient instance = new HazelcastClient();
    }

    private HazelcastClient() {
        System.out.println("HazelcastClient Create ...");
    }

    public static final HazelcastClient getInstance() {
        return HazelcastClientHolder.instance;
    }

    public void getClient() {
        System.out.println("getClient run ...");
    }
}
