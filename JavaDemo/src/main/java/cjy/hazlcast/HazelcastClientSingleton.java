package cjy.hazlcast;

public class HazelcastClientSingleton {
/*    // HAZELCAST连接
    private HazelcastInstance hazelcastClient;

    HazelcastClientSingleton() {
        try {
            String host = "192.168.2.107";
            String port = "5701";
            String address = host + ":" + port;
            String groupName = "antrol";
            String groupPassword = "antrol-pass";
            // 连接到客户端
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.addAddress(address);
            clientConfig.setGroupConfig(new GroupConfig(groupName, groupPassword));
            clientConfig.setConnectionAttemptLimit(Integer.MAX_VALUE);
            clientConfig.setConnectionAttemptPeriod(6 * 1000);
            hazelcastClient = HazelcastClient.newHazelcastClient(clientConfig);
        } catch (Throwable t) {}
    }

    public void test() {
        hazelcastClient.getMap("metricsAggregation");
        IMap<String, Map<Integer, AggregationStatistics>> metricsAggregationMap = hazelcastClient
                .getMap("metricsAggregation1");
        Map<Integer, AggregationStatistics> sixHourAggregationMap;
        sixHourAggregationMap = metricsAggregationMap.get("6hAggregation");
        System.out.println(sixHourAggregationMap.toString());
    }

    public static void main(String[] args) {
        new HazelcastClientSingleton().test();
    }*/
}
