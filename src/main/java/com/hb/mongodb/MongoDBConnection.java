package com.hb.mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MongoDBConnection {

    public static void main(String arg[]) {
        MongoDatabase mgdb = getDatabase();
        MongoCollection<Document> coll = getColl(mgdb);
        insertOneToMGDB(coll);
    }


    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(Document document) {
            System.out.println(document.toJson());
        }
    };

    //===================================================================
    //6.更新
//    更新文档
//
//    要从集合更新文档，使用com.mongodb.DBCollection类的update()和updateMany()方法。以下是选择第一个文档的代码片段 -

    //5.插入多行(本质为插入一个集合
    public static void insertMany(MongoCollection<Document> coll){
        Document doc1 = new Document("name", "Amarcord Pizzeria")
                .append("contact", new Document("phone", "264-555-0193")
                        .append("email", "amarcord.pizzeria@example.net")
                        .append("location", Arrays.asList(-73.88502, 40.749556)))
                .append("stars", 2)
                .append("categories", Arrays.asList("Pizzeria", "Italian", "Pasta"));


        Document doc2 = new Document("name", "Blue Coffee Bar")
                .append("contact", new Document("phone", "604-555-0102")
                        .append("email", "bluecoffeebar@example.com")
                        .append("location",Arrays.asList(-73.97902, 40.8479556)))
                .append("stars", 5)
                .append("categories", Arrays.asList("Coffee", "Pastries"));

        List<Document> documents = new ArrayList<>();
        documents.add(doc1);
        documents.add(doc2);
        coll.insertMany(documents);
    }

    //插入一条进文档
    public static void insertOneToMGDB(MongoCollection<Document> coll) {
        Document document = new Document().append("title", "MongoDB Insert Demo")
                .append("description", "database")
                .append("likes", 300)
                .append("by", "yiibai point")
                .append("url", "www");

        coll.insertOne(document);
        coll.find().forEach(printBlock);
        System.out.println("doc插入成功");
    }


    //创建集合
    public static MongoCollection<Document> getColl(MongoDatabase mgdb) {
        MongoCollection<Document> coll = mgdb.getCollection("myTestColl");

        System.out.println("当前集合是");
        for (String name : mgdb.listCollectionNames()) {
            System.out.println(name);
        }
        return coll;
    }


    //获取连接
    public static MongoDatabase getDatabase() {
        //1.连接服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //2.连接数据库
        MongoDatabase mgdb = mongoClient.getDatabase("hbdb");

        System.out.println("连接成功");
        System.out.println("数据库名为：" + mgdb.getName());

        //如果是安全模式
//          boolean auth = db.authenticate(myUsername,myPassword);
//          System.out.println(auth);

        System.out.println("当前数据库的所有集合");
        for (String name : mgdb.listCollectionNames()) {
            System.out.println(name);
        }

        return mgdb;

    }
}
