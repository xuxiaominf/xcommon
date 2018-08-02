/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.solr;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * 
 * @author xuxiaoming
 * @version $Id: TestSolr.java, v 0.1 2016年8月9日 下午3:08:31 xuxiaoming Exp $
 */
public class TestSolr {
    public static void main(String[] args) {
        SolrClient client = new HttpSolrClient("http://115.28.103.239:8983/solr/techproducts/");
        Article article = new Article(12,"财务办公室里的风水禁忌", new Date(), "xuxiaoming", "1、财务室里不可胡乱堆置物品，或不加清理，布满灰尘。不可放置会发热的电器，如电视、电扇、电炉、电源线等等。天花板不可漏水，墙壁或地板油漆不可脱落或磁砖斑驳。办公室装修效果图"
                +"2、保险柜不能位于梁下，以免造成财难进入的情况。也不能放在显眼的地方，以免漏财。 如果财务办公室中有横梁，就必须进行天花板吊顶的安装，否则一旦出现横梁位于保险柜上方的情况，会容易使公司出现财政困局。"
                +"3、保险柜的柜门不能对着大门，也不能顺着水流或风吹拂的方向，否则就会财来了就去，无法守财。在风的方向上，尤其要注意空调和风扇，空调和风扇吹拂的方向，都是漏财的方位，在摆放这些物品时，要特别注意。");
        
        try {
            client.addBean(article).getStatus();
            client.commit();
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
