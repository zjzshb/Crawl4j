package com.hb.mongodb;

import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.management.Query;
import java.util.*;
import java.util.concurrent.locks.Condition;

import static com.mongodb.client.model.Filters.eq;

public class Dao {

    private static String dbName = "hbdb";
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    static {
        mongoClient = MongoDBUtil.getMongoClient();
        database = mongoClient.getDatabase(dbName);
    }

    //增删改====================================================
    //1.插入一个
    public void insertMongo(String table, Document document) {
        try {
            collection = database.getCollection(table);
            collection.insertOne(document);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //1.1.插入多个
    public void insertMongo(String table, List<Document> documents) {
        collection = database.getCollection(table);
        collection.insertMany(documents);
    }

    //2.更新
//    db.collection.update( criteria, objNew, upsert, multi )
//    条件（where)，更新对象，true时为不存在则插入，false为只更新一条
    public void updateMongo(String table, Document document, Map<String, Object> conditions) {
        collection = database.getCollection(table);
        Set<Map.Entry<String, Object>> entries = conditions.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        String key = null;
        Object value = null;
        while (iterator.hasNext()) {
            key = iterator.next().getKey();
            value = iterator.next().getValue();
        }
        if (key == null && value == null) {
            return;
        }
        collection.updateOne(eq(key, value), new Document("$set", document));
    }

    //3.删除
    public void deleteMongo(String table, Map<String, Object> conditions) {
        collection = database.getCollection(table);
        Set<Map.Entry<String, Object>> entries = conditions.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        String key = null;
        Object value = null;
        while (iterator.hasNext()) {
            key = iterator.next().getKey();
            value = iterator.next().getValue();
        }
        if (key == null && value == null) {
            return;
        }
        collection.deleteOne(eq(key,value));
    }

    //===============================================================
    //4.查找
    //4.1.显示全部集合名
    public List<String> selectAllCollName(){
        List<String> strs = new ArrayList<>();
        for(String name:database.listCollectionNames()){
            strs.add(name);
        }
        return strs;
    }


    //4.2.查找所有
    public List<Document> selectAllDoc(String table){
        FindIterable<Document> iterable = database.getCollection(table).find();
        MongoCursor<Document> iterator = iterable.iterator();
        List<Document> list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        iterator.close();
        return list;
    }

    //4.3.带条件
//    public List<Document> selectByCondition(String table,Map<>)

}
