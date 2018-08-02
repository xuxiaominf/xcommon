/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.solr;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author xuxiaoming
 * @version $Id: SolrTest.java, v 0.1 2016年8月10日 下午3:58:00 xuxiaoming Exp $
 */
public class SolrTest {
    public static void main(String[] args) {
        Article article = new Article(13,"韩媒:韩正式对华阐明立场 绝不取消部署萨德", new Date(), "xuxiaoming", "【环球时报综合报道】在部署萨德的问题上，韩国似乎铁了心。据韩国外交消息人士透露，韩国驻华大使金章洙8日会晤了中国政府朝鲜半岛事务特别代表武大伟。韩联社称，这是中国官媒批评韩国部署萨德后，韩国政府首次正式通过外交渠道向中方阐明立场。据推测，金章洙很可能向武大伟传达了韩方决不可能取消部署计划等内容，同时强调韩中应继续对朝施压");
        
//        SolrUtils.save(article);
        
        List<Article> articles = SolrUtils.list();
        for (Article article2 : articles) {
            System.out.println(article2.toString());
        }
    }
}
