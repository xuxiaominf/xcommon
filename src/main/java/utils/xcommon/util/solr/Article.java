/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.solr;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * @author xuxiaoming
 * @version $Id: Article.java, v 0.1 2016年8月9日 下午3:11:18 xuxiaoming Exp $
 */
public  class Article {
    @Field("id")
    private int id;
    @Field("title")
    private String title;
    @Field("dateTime")
    private Date dateTime;
    @Field("user")
    private String user;
    @Field("content")
    private String content;
    
    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public int getId() {
        return id;
    }
    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter method for property <tt>title</tt>.
     * 
     * @return property value of title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Setter method for property <tt>title</tt>.
     * 
     * @param title value to be assigned to property title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Getter method for property <tt>date</tt>.
     * 
     * @return property value of date
     */
    public Date getDate() {
        return dateTime;
    }
    /**
     * Setter method for property <tt>date</tt>.
     * 
     * @param date value to be assigned to property date
     */
    public void setDate(Date dateTime) {
        this.dateTime = dateTime;
    }
    /**
     * Getter method for property <tt>user</tt>.
     * 
     * @return property value of user
     */
    public String getUser() {
        return user;
    }
    /**
     * Setter method for property <tt>user</tt>.
     * 
     * @param user value to be assigned to property user
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * Getter method for property <tt>content</tt>.
     * 
     * @return property value of content
     */
    public String getContent() {
        return content;
    }
    /**
     * Setter method for property <tt>content</tt>.
     * 
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @param title
     * @param date
     * @param user
     * @param content
     */
    public Article(int id,String title, Date dateTime, String user, String content) {
        super();
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.user = user;
        this.content = content;
    }
    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"title\":\"%s\",\"dateTime\":\"%s\",\"user\":\"%s\",\"content\":\"%s\"}  ", id, title, dateTime, user,
                content);
    }
    
    
}
