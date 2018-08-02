/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.solr;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import utils.xcommon.util.date.DateUtils;

/**
 * 
 * @author xuxiaoming
 * @version $Id: TestSolr.java, v 0.1 2016年8月9日 下午3:08:31 xuxiaoming Exp $
 */
public class SolrUtils {

    
    private static SolrClient client = null;
    static{
        client = new HttpSolrClient("http://115.28.103.239:8983/solr/article/");
    }
    
    public static int save(Object obj){
        int ret = 0;
        try {
            ret = client.addBean(obj).getStatus();
            client.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                client.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return ret;
    }
    
    public static List<Article> list(){
        List<Article> lists = new ArrayList<>();
           SolrQuery parameters = new SolrQuery();
           parameters.set("q", "*:*");
           parameters.set("sort", "id desc");
           parameters.set("start", "0");
           parameters.set("rows", "10");
           QueryResponse response;
           try {
               response = client.query(parameters);
               client.close();
               SolrDocumentList list = response.getResults();
               Iterator<SolrDocument> i = list.iterator();
               if (i.hasNext()) {
                SolrDocument document = i.next();
                int id = Integer.parseInt(document.getFieldValue("id").toString());
                String title = document.getFieldValue("title").toString();
                String user = document.getFieldValue("user").toString();
//                System.out.println(document.getFieldValue("dateTime"));
                Date dateTime = DateUtils.DEFAULT_DATE_FORMAT.parse((document.getFieldValue("dateTime").toString()));
                String content = document.getFieldValue("content").toString();
                
                Article article = new Article(id, title, dateTime, user, content);
                lists.add(article);
               }
           } catch (SolrServerException | IOException | ParseException e) {
            System.out.println("[ERROR] ConsoleStore.find() - e.Message: " + e.getMessage());
           }
        return lists;
    }
    
    public static int delete(String id){
        try {
            int ret = client.deleteById(id).getStatus();
            return ret;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    

}
