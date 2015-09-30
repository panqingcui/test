// package neo4j;
//
// import java.net.URI;
// import java.util.Map;
//
// import javax.transaction.TransactionManager;
//
// import org.neo4j.graphdb.DynamicRelationshipType;
// import org.neo4j.graphdb.Node;
// import org.neo4j.graphdb.Relationship;
// import org.neo4j.graphdb.RelationshipType;
// import org.neo4j.graphdb.Transaction;
// import org.neo4j.rest.graphdb.BatchTransactionManager;
// import org.neo4j.rest.graphdb.RestAPI;
// import org.neo4j.rest.graphdb.RestAPIFacade;
// import org.neo4j.rest.graphdb.index.RestIndexManager;
// import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
// import org.neo4j.rest.graphdb.util.ResultConverter;
//
// public class RestGraphDb {
// private RestAPI restAPI;
// private RestRequest restRequest;
// private final RestCypherQueryEngine cypherQueryEngine;
//
// public RestGraphDb(URI uri, RestAPI api) {
// restRequest = new RestRequest(uri);
// this.restAPI = api;
// cypherQueryEngine = new RestCypherQueryEngine(restAPI);
// }
//
// public RestGraphDb(URI uri) {
// restRequest = new RestRequest(uri);
// this(uri, new RestAPIFacade(uri));
// }
//
// public RestAPI getRestAPI() {
// return this.restAPI;
// }
//
// public RestIndexManager index() {
// return this.restAPI.index();
// }
//
// public Node createNode() {
// return this.restAPI.createNode(null);
// }
//
// public Node getNodeById(long id) {
// return this.restAPI.getNodeById(id);
// }
//
// public Node getReferenceNode() {
// return this.restAPI.getReferenceNode();
// }
//
// public Iterable<Node> getAllNodes() {
// return cypherQueryEngine.query("start n=node(*) return n", null).to(Node.class);
// }
//
// public Iterable<RelationshipType> getRelationshipTypes() {
// return cypherQueryEngine.query("start n=node(*) match n-[r]->() return distinct type(r) as rel_type", null).to(
// RelationshipType.class, new ResultConverter<Map<String, Object>, RelationshipType>() {
// @Override
// public RelationshipType convert(Map<String, Object> row, Class<RelationshipType> type) {
// return DynamicRelationshipType.withName((String) row.get("rel_type"));
// }
// });
// }
//
// public Relationship getRelationshipById(long id) {
// return this.restAPI.getRelationshipById(id);
// }
//
// public String getStoreDir() {
// return restAPI.getBaseUri();
// }
//
// public TransactionManager getTxManager() {
// return new BatchTransactionManager(restAPI); // new NullTransactionManager();
// }
//
// public Transaction beginTx() {
// return restAPI.beginTx();
// }
//
// public void shutdown() {
// restAPI.close();
// }
// }
