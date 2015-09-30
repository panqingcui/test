package neo4j;

import org.neo4j.graphdb.RelationshipType;

enum UserRelationship implements RelationshipType {
    FELLOW, BELONG
}
