/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.QueryOperators;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月27日<br> { 　　"database" : "mkyongDB", 　　"table" : "hosting", 　　"detail" : 　　{ 　　records : 99, 　　index :
 * "vps_index1", 　　active : "true" 　　} 　　} 　　}
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class MongoDBImpl {
    private Logger logger = Logger.getLogger(MongoDBImpl.class);

    public void save(DBObject object, String collectionName) {
        MongoDBClientSingleton.INSTANCE.getCollection(collectionName).insert(object).getN();
    }

    /**
     * { 　　"database" : "mongodb", 　　"table" : "mongodb", 　　"detail" : 　　{ 　　records : 99, 　　index : "vps_index1",
     * 　　active : "true" 　　} 　　} 　　}
     **/
    // 第一种保存方式 实现DBObject接口
    public void testSaveByDBObject() {
        MongoDBImpl m = new MongoDBImpl();
        Tweet t = new Tweet();
        t.put("database", "DBObject");
        t.put("table", "mongodb");
        Tweet detail = new Tweet();
        detail.put("records", 99);
        detail.put("index", "vps_index1");
        detail.put("active", true);
        t.put("detail", detail);
        m.save(t, "databaseinfo");
    }

    // 第二种保存方式 BasicDBObject
    public void testSaveByBasicDBObject() {
        BasicDBObject document = new BasicDBObject();
        document.put("database", "BasicDBObject");
        document.put("table", "hosting");
        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("records", "99");
        documentDetail.put("index", "vps_index1");
        documentDetail.put("active", "true");
        document.put("detail", documentDetail);
        MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").save(document);
    }

    // 第三种方式 BasicDBObjectBuilder
    public void testSaveBasicDBObjectBuilder() {
        BasicDBObjectBuilder bulider = new BasicDBObjectBuilder().start().add("database", "BasicDBObjectBuilder")
                .add("table", "hosting");
        BasicDBObjectBuilder detail = new BasicDBObjectBuilder().start().add("records", 99).add("index", "vps_index1")
                .add("active", "true");
        bulider.add("detail", detail.get());
        MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").save(bulider.get());
    }

    // 第四中方式Map
    @SuppressWarnings({"unchecked", "rawtypes" })
    public void testSaveByMap() {
        Map map = new HashMap();
        map.put("database", "Map");
        map.put("table", "hosting");
        map.put("time", new Date());
        Map detail = new HashMap();
        detail.put("records", "99");
        detail.put("index", "vps_index1");
        detail.put("active", "true");
        map.put("detail", detail);
        MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").save(new BasicDBObject(map));
    }

    // 第五种
    public void testSaveByJson() {
        String json = "{'database':'JSON','table':'hosting','detail':{'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";
        DBObject object = (DBObject) JSON.parse(json);
        MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").save(object);
    }

    // 第六种 序列化对象（采用谷歌GSON）
    public void testSaveObject() {
        Users user = new Users();
        user.setUserName("管理员");
        user.setUserId(30);
        user.setPhone("18615183726");
        user.setAge(40);
        Address ress = new Address();
        ress.setAddressName("上海百脑汇");
        user.setAddress(ress);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        logger.info(json);
        DBObject object = (DBObject) JSON.parse(json);
        MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").save(object);
    }

    public void testSaveDevice() {
        long l = System.currentTimeMillis();
        Device device = new Device();
        device.setDeviceId(1);
        device.setAlias("机房测试");
        device.setDeviceFqdn("a");
        device.setDeviceName("IBM");
        Server server = new Server();
        server.setServerId(1);
        server.setRuntimeAutodiscovery(true);
        // server.setZyDevice(device);
        Set<Server> s = new HashSet<Server>();
        s.add(server);
        device.setZyServers(s);
        Gson gson = new Gson();
        String json = gson.toJson(device);
        // logger.info(json);
        DBObject object = (DBObject) JSON.parse(json);
        MongoDBClientSingleton.INSTANCE.getCollection("test").save(object);
        // 44800
        logger.info("用时：" + (System.currentTimeMillis() - l));
    }

    public void getDevice() {
        DBObject db = new BasicDBObject();
        db.put("userId", 30);
        db.put("address.addressId", 0);
        DBObject cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").findOne(db);
        logger.info(cursor);
        Gson gson = new Gson();
        Users users = gson.fromJson(cursor.toString(), Users.class);
        logger.info("" + users.getAddress().getAddressName());
    }

    // 返回age=30,的userName 的值
    public void getUser() {
        // db.databaseinfo.find({"age":30});
        DBObject db = new BasicDBObject();
        db.put("age", 30);
        DBObject cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").findOne(db,
                new BasicDBObject("userName", "userName"));
        logger.info(cursor);
    }

    // 小于等于
    public void getUserByLte() {
        // db.databaseinfo.find({"age":{$lte:30}})
        DBObject db = new BasicDBObject();
        db.put("age", new BasicDBObject("$lte", 30));
        DBCursor cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").find(db);
        List<DBObject> os = cursor.toArray();
        for (DBObject o : os) {
            logger.info(o);
        }
    }

    // 大于等于
    public void getUserByGte() {
        // db.databaseinfo.find({"age":{$gte:30}})
        DBObject db = new BasicDBObject();
        db.put("age", new BasicDBObject("$gte", 30));
        DBCursor cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").find(db);
        List<DBObject> os = cursor.toArray();
        for (DBObject o : os) {
            logger.info(o);
        }
    }

    // 不等于
    public void getUserByNe() {
        // db.databaseinfo.find({"age":{$ne:30}})
        DBObject db = new BasicDBObject();
        db.put("age", new BasicDBObject("$ne", 30));
        DBCursor cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").find(db);
        List<DBObject> os = cursor.toArray();
        for (DBObject o : os) {
            logger.info(o);
        }
    }

    // in 用法
    public void getUserByIn() {
        // db.databaseinfo.find({"age":{$in:[20,30]}})
        DBObject db = new BasicDBObject();
        db.put("age", new BasicDBObject("$in", new int[] {20, 30 }));
        DBCursor cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").find(db);
        List<DBObject> os = cursor.toArray();
        for (DBObject o : os) {
            logger.info(o);
        }
    }

    // not in
    public void getUserByNIn() {
        DBObject db = new BasicDBObject();
        db.put("age", new BasicDBObject(QueryOperators.NIN, new int[] {20, 30 }));
        DBCursor cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").find(db);
        List<DBObject> os = cursor.toArray();
        for (DBObject o : os) {
            logger.info(o);
        }
    }

    // or
    public void getUserByOr() {
        DBObject query = new BasicDBObject();
        BasicDBList dbList = new BasicDBList();
        dbList.add(new BasicDBObject("age", 20));
        dbList.add(new BasicDBObject("age", 30));
        query.put("$or", dbList);
        DBCursor cursor = MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").find(query);
        List<DBObject> os = cursor.toArray();
        for (DBObject o : os) {
            logger.info(o);
        }
    }

    /*
     * public void getUserWhere() { BasicDBList condList = new BasicDBList(); // DBObject db = new BasicDBObject(); //
     * db.put("age", 30); String ageStr =
     * "function (){return parseFloat(this.age) > 20 && parseFloat(this.age) <= 40};"; DBObject db = new
     * BasicDBObject(); db.put("$where", ageStr); condList.add(db); DBCursor cursor =
     * MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").find(condList); logger.info(cursor); }
     */
    public void saveUser() {
        DBObject db = new BasicDBObject();
        db.put("_id", 1);
        db.put("user_name", "NJ早安");
        MongoDBClientSingleton.INSTANCE.getCollection("databaseinfo").insert(db);
    }

    public void saveDBRef() {
        DBRef addressRef = new DBRef(MongoDBClientSingleton.INSTANCE.getDB(), "dbref", new ObjectId());
        DBObject address = addressRef.fetch();
        DBObject person = BasicDBObjectBuilder.start().add("name", "Fred").add("address", addressRef).get();
        MongoDBClientSingleton.INSTANCE.getCollection("dbref").save(person);
    }

    public void getDBRef() {
        DBObject fred = MongoDBClientSingleton.INSTANCE.getCollection("students").findOne();
        Student stu = (Student) JSONUtil.toObject(fred, Student.class);
        DBRef test = new DBRef(MongoDBClientSingleton.INSTANCE.getDB(), JSONUtil.toDBObject(stu.getDb().get(0)));
        logger.info(test.fetch());
        // Student s = JSONUtil.toObject(fred, Student.class);
        // DBref ref = s.getDb().get(0);
        // DBRef dbref = new DBRef(MongoDBClientSingleton.INSTANCE.getDB(), ref.get$ref(), ref.get$id());
        // logger.info(dbref.fetch());
    }

    public void updateUser() {
        DBObject db = new BasicDBObject();
        db.put("_id", 1);
        db.put("user_name", "NJ早安");
        db.put("user_age", 30);
        MongoDBClientSingleton.INSTANCE.getCollection("testUser").update(
                new BasicDBObject().append("user_name", "NJ早安"), new BasicDBObject("$set", db), false, true,
                WriteConcern.JOURNAL_SAFE);
    }

    public static void main(String[] args) {
        MongoDBImpl m = new MongoDBImpl();
        // m.testSaveByDBObject();
        // m.testSaveByBasicDBObject();
        // m.testSaveBasicDBObjectBuilder();
        // m.testSaveByJson();
        // m.testSaveObject();
        // m.testSaveDevice();
        // m.getDevice();
        // m.getUserWhere();
        m.updateUser();
    }
}
