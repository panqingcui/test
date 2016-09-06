// package neo4j;
//
// import java.net.URI;
//
// import javax.ws.rs.core.Response;
//
// import com.sun.jersey.api.client.ClientResponse;
//
// public class RequestResult {
// private final int status;
// private final URI location;
// private final String entity;
//
// RequestResult(int status, URI location, String entity) {
// this.status = status;
// this.location = location;
// this.entity = entity;
// }
//
// public static RequestResult extractFrom(ClientResponse clientResponse) {
// final int status = clientResponse.getStatus();
// final URI location = clientResponse.getLocation();
// final String data = status != Response.Status.NO_CONTENT.getStatusCode() ? clientResponse
// .getEntity(String.class) : null;
// clientResponse.close();
// return new RequestResult(status, location, data);
// }
//
// public int getStatus() {
// return status;
// }
//
// public URI getLocation() {
// return location;
// }
//
// public String getEntity() {
// return entity;
// }
// }
