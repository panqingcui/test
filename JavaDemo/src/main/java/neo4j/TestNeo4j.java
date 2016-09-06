// package neo4j;
//
// import java.util.Iterator;
//
// import org.apache.log4j.Logger;
// import org.neo4j.graphdb.Direction;
// import org.neo4j.graphdb.GraphDatabaseService;
// import org.neo4j.graphdb.Node;
// import org.neo4j.graphdb.Relationship;
// import org.neo4j.graphdb.RelationshipType;
// import org.neo4j.graphdb.Transaction;
// import org.neo4j.graphdb.index.Index;
// import org.neo4j.graphdb.index.IndexManager;
// import org.neo4j.graphdb.index.RelationshipIndex;
// import org.neo4j.graphdb.traversal.Evaluators;
// import org.neo4j.graphdb.traversal.TraversalDescription;
// import org.neo4j.graphdb.traversal.Traverser;
// import org.neo4j.kernel.Traversal;
// import org.neo4j.rest.graphdb.RestGraphDatabase;
//
// public class TestNeo4j {
// private static Logger log = Logger.getLogger(TestNeo4j.class);
// private static GraphDatabaseService graphDb = null;
// /* 边索引常量 */
// private static final String LINESINDEXNAME = "lines";
// /* 节点索引常量 */
// private static final String NODESINDEXNAME = "nodes";
// /**
// * 线程内的事务
// */
// private static final ThreadLocal<Transaction> transaction = new
// ThreadLocal<Transaction>();
//
// public static void setGraphDb() {
// graphDb = new Activator().getDb();
// }
//
// public static void main(String[] args) {
// // setGraphDb();
// // List<GNode> gnodes = new ArrayList<GNode>();
// // GNode f = new GNode();
// // f.setId("马云");
// // gnodes.add(f);
// // GNode e = new GNode();
// // e.setId("马化腾");
// // gnodes.add(e);
// // List<GLine> lines = new ArrayList<GLine>();
// // GLine line = new GLine();
// // line.setNodeFrom(f.getId());
// // line.setNodeTo(e.getId());
// // lines.add(line);
// // GMap map = new GMap();
// // map.setGLineList(lines);
// // map.setGNodeList(gnodes);
// // map.setId(map.DEFAULT_GMAP);
// // new TestNeo4j().saveGMap(map);
// // // String DB_PATH = "http://localhost:7474/db/data";
// // // GraphDatabaseFactory factory = new GraphDatabaseFactory();
// // // gds = factory.newEmbeddedDatabase(DB_PATH);
// // // GlobalGraphOperations ggo = GlobalGraphOperations.at(gds);
// // // RequestResult r = new RequestResult(gds);
// // // System.out.println("*************" + gds.isAvailable(1));
// // ;
// GraphDatabaseService graphDb = new RestGraphDatabase("http://localhost:7474/db/data");
// Transaction tx = graphDb.beginTx();
// try {
// Node fromNode = graphDb.createNode();
// fromNode.setProperty("table", "person");
// fromNode.setProperty("name", "马云");
// Node toNode = graphDb.createNode();
// toNode.setProperty("table", "person");
// toNode.setProperty("name", "李彦宏");
// Relationship relationship = fromNode.createRelationshipTo(toNode,
// UserRelationship.FELLOW);
// relationship.setProperty("event", "2013福布斯中国富豪榜:李彦宏第三、马化腾第五、马云第八 ");
// tx.success();
// } catch (Throwable e) {
// // System.err.println("异常:" + e);
// tx.failure();
// } finally {
// tx.finish();
// }
// // Index<Node> index = graphDb.index().forNodes("2");
// // IndexHits<Node> indexNode = index.get("table", "company");
// // for (Iterator<Node> iter = indexNode.iterator(); indexNode.hasNext();) {
// // System.out.println(iter.next());
// // }
// // IndexManager index = gds.index();
// // /* 节点索引 */
// // Index<Node> nodeIndex = index.getNodeAutoIndexer();
// // while (iterator.hasNext()) {
// // Node node = iterator.next();
// // Iterator<String> keysIterator = node.getPropertyKeys().iterator();
// // while (keysIterator.hasNext()) {
// // String key = keysIterator.next();
// // System.out.println(key + "->" + node.getProperty(key));
// // }
// // Iterator<Relationship> relationshipsIterator = node.getRelationships().iterator();
// // while (relationshipsIterator.hasNext()) {
// // Relationship relationships = relationshipsIterator.next();
// // System.out.println("关系：" + relationships.getType());
// // }
// // }
// // gds.shutdown();
// }
//
// public boolean saveGMap(GMap map) {
// setGraphDb();
// if (map == null || map.getId() == null) {
// log.debug("GMap or GMap's id is null!");
// return false;
// }
// Transaction tx = graphDb.beginTx();
// try {
// IndexManager index = graphDb.index();
// /* 节点索引 */
// Index<Node> nodeIndex = index.forNodes(map.getId() + NODESINDEXNAME);
// /* 边索引 */
// RelationshipIndex lineIndex = index.forRelationships(map.getId() + LINESINDEXNAME);
// Node viewport = this.getMapRootNode(map.getId());
// if (viewport == null) {
// // 创建图根节点，设置属性
// viewport = graphDb.createNode();
// log.info("viewport:" + viewport);
// viewport.setProperty(PropertyKey.MAP_ID, map.getId());
// viewport.setProperty(PropertyKey.MAP_AUTO, map.getAutoLayout());
// // 创建默认节点到根节点的关联
// graphDb.getReferenceNode().createRelationshipTo(viewport,
// TopoMapRelationshipTypes.ROOT);
// } else {
// log.debug("viewport is already exists!");
// }
// if (map.getGNodeList() != null) {
// // 遍历生成所有节点并放入图中
// for (int i = 0; i < map.getGNodeList().size(); i++) {
// GNode gnode = map.getGNodeList().get(i);
// log.info("保存节点信息：" + gnode.getId());
// Node node = this.createAndIndexNode(graphDb, nodeIndex, gnode, map.getId());
// viewport.createRelationshipTo(node, TopoMapRelationshipTypes.HAS);
// }
// }
// if (map.getGLineList() != null) {
// // 遍历边数据，生成边并生成索引
// for (int i = 0; i < map.getGLineList().size(); i++) {
// GLine gline = map.getGLineList().get(i);
// Node sn = nodeIndex.get(PropertyKey.NODE_ID, gline.getNodeFrom()).getSingle();
// Node en = nodeIndex.get(PropertyKey.NODE_ID, gline.getNodeTo()).getSingle();
// if (sn != null & en != null) {
// if (sn.getProperty(PropertyKey.MAP_ID).equals(map.getId())
// && en.getProperty(PropertyKey.MAP_ID).equals(map.getId())) {
// this.createAndIndexLine(sn, en, TopoMapRelationshipTypes.CONNECTION, map.getId(),
// gline,
// lineIndex);
// }
// } else {
// log.error("边关系节点存在空值：" + "id=" + gline.getId() + ",nodeFrom=" + gline.getNodeFrom()
// + ",nodeTo=" + gline.getNodeTo());
// }
// }
// }
// log.debug("Save GMap " + map.getId() + " successfully!");
// tx.success();
// } catch (Exception e) {
// log.error(e);
// tx.failure();
// } finally {
// tx.finish();
// }
// return true;
// }
//
// /**
// * 创建节点并生成索引
// * @param graphDb 图
// * @param gn 节点数据
// * @param mapid 图唯一标示
// * @return 节点对象
// */
// private Node createAndIndexNode(GraphDatabaseService graphDb, Index<Node> nodeIndex,
// GNode gn, String mapid) {
// Node node = graphDb.createNode();
// node.setProperty(PropertyKey.MAP_ID, mapid);
// node.setProperty(PropertyKey.NODE_ID, gn.getId());
// node.setProperty(PropertyKey.NODE_X, gn.getX());
// node.setProperty(PropertyKey.NODE_Y, gn.getY());
// node.setProperty(PropertyKey.NODE_NET, gn.getNet());
// nodeIndex.add(node, PropertyKey.NODE_ID, gn.getId());
// nodeIndex.add(node, PropertyKey.MAP_ID, mapid);
// return node;
// }
//
// /**
// * 创建边
// * @param sn
// * @param en
// * @param relType
// * @param mapid
// * @param gline
// * @return
// */
// private Relationship createAndIndexLine(Node sn, Node en, RelationshipType relType,
// String mapid, GLine gline,
// RelationshipIndex lineIndex) {
// Relationship relation = sn.createRelationshipTo(en, relType);
// relation.setProperty(PropertyKey.MAP_ID, mapid);
// relation.setProperty(PropertyKey.LINE_ID, gline.getId());
// relation.setProperty(PropertyKey.LINE_SNODEID, gline.getNodeFrom());
// relation.setProperty(PropertyKey.LINE_ENODEID, gline.getNodeTo());
// relation.setProperty(PropertyKey.LINE_TYPE, gline.getType());
// lineIndex.add(relation, PropertyKey.LINE_ID, gline.getId());
// lineIndex.add(relation, PropertyKey.MAP_ID, mapid);
// return relation;
// }
//
// /**
// * 根据mapid，获取map的根节点。
// * @param mapid
// * @return
// */
// private Node getMapRootNode(String mapid) {
// if (mapid == null) {
// log.warn("Parameter mapid of method getMapRootNode in GraphDbDaoImpl  is null!");
// return null;
// }
// Node root = null;
// // 搜索根节点
// Iterable<Relationship> rootIterable =
// graphDb.getReferenceNode().getRelationships(Direction.OUTGOING,
// TopoMapRelationshipTypes.ROOT);
// // 找到图
// for (Iterator<Relationship> iter = rootIterable.iterator(); iter.hasNext();) {
// Node node = iter.next().getEndNode();
// if (node.getProperty(PropertyKey.MAP_ID) != null &&
// node.getProperty(PropertyKey.MAP_ID).equals(mapid)) {
// root = node;
// break;
// }
// }
// return root;
// }
//
// public static void DeleteRelativeNode(Node n) {
// TraversalDescription td =
// Traversal.description().relationships(TopoMapRelationshipTypes.HAS, Direction.BOTH)
// .evaluator(Evaluators.excludeStartPosition());
// Traverser t = td.traverse(n);
// for (Node tn : t.nodes()) {
// Iterable<Relationship> relationships = tn.getRelationships();
// for (Relationship r : relationships) {
// if (r.getStartNode().equals(n) || r.getEndNode().equals(n)) {
// r.delete();
// }
// }
// if (!tn.hasRelationship()) {
// tn.delete();
// }
// }
// }
// }
