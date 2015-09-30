package neo4j;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.Traversal;
import org.neo4j.rest.graphdb.RestGraphDatabase;

public class HelloWorldTest {
    private static Logger log = Logger.getLogger(HelloWorldTest.class);

    private static enum RelTypes implements RelationshipType {
        KNOWS
    };

    GraphDatabaseService graphDb;
    Node firstNode;
    Node secondNode;
    Relationship relationship;
    private static final String DB_PATH = "demo1/db1";// "neo4j-db";//

    public static void main(String[] args) {
        HelloWorldTest hwt = new HelloWorldTest();
        hwt.run();
    }

    public void run() {
        // 创建连接，生成图
        // GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
        GraphDatabaseService graphDb = new RestGraphDatabase("http://192.168.2.186:7474/db/data");
        registerShutdownHook(graphDb);
        // 创建事务，
        Transaction tx = graphDb.beginTx();
        try {
            // 添加节点
            firstNode = graphDb.createNode();
            firstNode.setProperty("message", "Hello,Hello");
            secondNode = graphDb.createNode();
            secondNode.setProperty("message", "HelloHelloHello");
            relationship = firstNode.createRelationshipTo(secondNode, RelTypes.KNOWS);
            relationship.setProperty("message", "brave neo4j");
            // print();
            // delete();
            // 索引
            TraversalDescription td = Traversal.description()
                    .relationships(TopoMapRelationshipTypes.HAS, Direction.BOTH)
                    .evaluator(Evaluators.excludeStartPosition());
            Traverser t = td.traverse(firstNode);
            for (Node tn : t.nodes()) {
                log.debug("message" + tn);
            }
            tx.success();// 提交
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            tx.finish();
            graphDb.shutdown();
        }
        // delete();
    }

    private void delete() {
        firstNode.getSingleRelationship(RelTypes.KNOWS, Direction.OUTGOING).delete();
        firstNode.delete();
        secondNode.delete();
    }

    private void print() {
        System.out.println(firstNode.getProperty("message"));
        System.out.println(relationship.getProperty("message"));
        System.out.println(secondNode.getProperty("message"));
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }
}
