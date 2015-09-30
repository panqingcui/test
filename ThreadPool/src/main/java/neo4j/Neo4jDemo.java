package neo4j;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.Traversal;
import org.neo4j.rest.graphdb.RestGraphDatabase;

public class Neo4jDemo {
    private final static Logger logger = Logger.getLogger(Neo4jDemo.class);

    private static enum RelationshipTypes implements RelationshipType {
        PUBLISH, CONTAIN, ROOT, HAS, CONNECTION
    }

    // 这个方法的意思就是在jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，当系统执行完这些钩子后，jvm才会关闭。所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作
    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    /**
     * 创建节点 边
     */
    public static void useNodeAndRelationship() {
        GraphDatabaseService db = new RestGraphDatabase("http://192.168.2.186:7474/db/data");
        Transaction tx = db.beginTx();
        registerShutdownHook(db);
        try {
            Node node1 = db.createNode();
            node1.setProperty("name", "歌手 ");
            Node node2 = db.createNode();
            node2.setProperty("name", "专辑 ");
            node1.createRelationshipTo(node2, RelationshipTypes.PUBLISH);
            Node node3 = db.createNode();
            node3.setProperty("name", "歌曲 ");
            node2.createRelationshipTo(node3, RelationshipTypes.CONTAIN);
            tx.success();
        } finally {
            tx.finish();
        }
    }

    // 索引
    public static void useIndex() {
        GraphDatabaseService db = new RestGraphDatabase("http://192.168.2.186:7474/db/data");
        registerShutdownHook(db);
        Index<Node> index = db.index().forNodes("nodes");
        Transaction tx = db.beginTx();
        try {
            Node node1 = db.createNode();
            String name = "歌手 1";
            node1.setProperty("name", name);
            index.add(node1, "name", name);
            node1.setProperty("gender", "男");
            tx.success();
        } finally {
            tx.finish();
        }
        Object result = index.get("name", "歌手 1").getSingle().getProperty("gender");
        logger.debug(result); // 输出为“男”
    }

    public static void useTral() {
        GraphDatabaseService db = new RestGraphDatabase("http://192.168.2.186:7474/db/data");
        registerShutdownHook(db);
        Index<Node> index = db.index().forNodes("AutoDiscoveryGmap" + "nodes");
        TraversalDescription td = Traversal.description().relationships(RelationshipTypes.CONNECTION).depthFirst();
        Node node = index.get("mapid", "AutoDiscoveryGmap").getSingle();
        Traverser traverser = td.traverse(node);
        logger.info("node:" + node);
        int numberOfFriends = 0;
        for (Path path : traverser) {
            logger.info("节点：" + path.endNode().getProperty("nodeid"));
            numberOfFriends++;
        }
    }

    public static void main(String[] args) {
        // 创建节点 边
        // useNodeAndRelationship();
        // useIndex();
        useTral();
    }
}
