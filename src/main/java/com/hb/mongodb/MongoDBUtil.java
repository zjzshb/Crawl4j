package com.hb.mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoDBUtil {

    private static final String HOST = "localhost";
    private static final int PORT = 27017;

    private static MongoClient mongoClient = null;

    static {
        mongoClient = new MongoClient(HOST, PORT);
    }

    public static MongoClient getMongoClient(){
        return mongoClient;
    }

    public static void closeMongo(MongoDatabase mongoDatabase, MongoClient mongoClient){
        if(mongoDatabase!=null){
            mongoDatabase = null;
        }
        if(mongoClient!=null){
            mongoClient = null;
        }
    }

    //        map转document
    public static void mapToDocument(MongoCollection<Document> collection){



//        MongoCursor<Document> cursor = findIter.iterator();
//        List<Map<String, Object>> list = new ArrayList<>();
//        while (cursor.hasNext()) {
//            Map<String, Object> map = new HashMap<>();
//            map.putAll(cursor.next());
//            list.add(map);
//        }
//
//        findIter.forEach(new Block<Document>() {
//            @Override
//            public void apply(Document doc) {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.putAll(doc);
//                list.add(map);
//            }
//        });
    }

}
