package com.hb.solrJ;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class SolrJTest {

    //添加文档索引
    //增与改
    @Test
    public void addDocument() throws IOException, SolrServerException {
        //1.连接上solr
        //[1]获取连接
        // HttpSolrClient client= new HttpSolrClient.Builder("http://127.0.0.1:8080/solr/core1").build();
//        String solrUrl = "http://127.0.0.1:8887/solr/index.html#/corename";
        String solrUrl = "http://127.0.0.1:8887/solr/corename";//注意！！！！！！！！！！！！！

        //创建solrClient同时指定超时时间，不指定走默认配置
        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();

        //[2]创建文档doc
        SolrInputDocument doc = new SolrInputDocument();
        //[3]添加内容
        String str = UUID.randomUUID().toString();
        System.out.println(str);
        doc.addField("id", str);
        doc.addField("name", "Amazon Kindle Paperwhite");
        doc.addField("authorName", "沙发");

        //[4]添加到client
        UpdateResponse updateResponse = client.add(doc);
        System.out.println(updateResponse.getElapsedTime());

        //[5] 索引文档必须commit
        client.commit();
    }

    //删除文档
    //1.
    @Test
    public void deleteById() throws IOException, SolrServerException {
        String solrUrl = "http://127.0.0.1:8887/solr/corename";//注意！！！！！！！！！！！！！

        //创建solrClient同时指定超时时间，不指定走默认配置
        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();

        //指定id删除
        client.deleteById("1d21db17-27d2-41cc-9eeb-afe53c6f06eb");

        client.commit();
    }
    //2.查询语句删除
    @Test
    public void deleteByQuery() throws IOException, SolrServerException {
        String solrUrl = "http://127.0.0.1:8887/solr/corename";//注意！！！！！！！！！！！！！

        //创建solrClient同时指定超时时间，不指定走默认配置
        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();

        //查询语句删除
        client.deleteById("id:1d21db17-27d2-41cc-9eeb-afe53c6f06eb");

        client.commit();
    }

    //2.查询语句查找
    @Test
    public void selectByQuery() throws IOException, SolrServerException {
        String solrUrl = "http://127.0.0.1:8887/solr/corename";//注意！！！！！！！！！！！！！

        //创建solrClient同时指定超时时间，不指定走默认配置
        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();

        //[2]封装查询参数
        SolrQuery query = new SolrQuery("authorName:李幼萌");
        System.out.println(query.toString());
        //[3]添加需要回显得内容
//        query.setFilterQueries("authorName:李幼萌~");


        //查询语句
        QueryResponse response = client.query(query);
        SolrDocumentList documents = response.getResults();

        for(SolrDocument doc:documents){
            System.out.println("authorName"+doc.get("authorName"));
        }

        client.commit();
    }
}
