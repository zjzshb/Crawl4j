package com.hb.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import jdk.internal.dynalink.MonomorphicCallSite;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class RMongoDBUtil {
    public static void main(String[] args){
        RMongoDBUtil rMongoDBUtil = new RMongoDBUtil();
        rMongoDBUtil.connect();
    }

    public void connect(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase database = mongoClient.getDatabase("hbdb");
        System.out.println("连接数据库成功");

        //-查看--------------------------------------

//        database.createCollection("colName"); //创建数据库

        MongoCollection<Document> collection = database.getCollection("colName");

        //-插入--------------------------------------
        Document document = new Document("username","MongoDB").
                append("description","a user").
                append("age",10);
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        collection.insertMany(documents);

        //-迭代--------------------------------------
        FindIterable<Document> findIterable = collection.find();//获取迭代器
        MongoCursor<Document> mongoCursor = findIterable.iterator();//获取游标
        while(mongoCursor.hasNext()){
            System.out.println(mongoCursor.next());
        }

        //-更新--------------------------------------
        collection.updateMany(Filters.eq("age",10),new Document("$set",new Document("age",20)));

        //-删除符合的第一个--------------------------------------
        collection.deleteOne(Filters.gt("age",10));//删除age>10 greater than

        //-删除符合的所有--------------------------------------
        collection.deleteOne(Filters.lt("age",20));//删除age<20 greater than


        //-迭代--------------------------------------

    }
}
