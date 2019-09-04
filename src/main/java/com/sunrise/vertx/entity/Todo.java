package com.sunrise.vertx.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.concurrent.atomic.AtomicInteger;

@DataObject(generateConverter = true)
public class Todo {

    private static  final AtomicInteger acc = new AtomicInteger(0);

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     *完成状态
     */
    private Boolean completed;
    /**
     * 顺序
     */
    private Integer order;
    /** url */
    private String url;

    public Todo() {
    }


    public Todo(Todo otherTodo) {
        this.id = otherTodo.id;
        this.title = otherTodo.title;
        this.completed = otherTodo.completed;
        this.order = otherTodo.order;
        this.url = otherTodo.url;
    }

    public Todo(Integer id, String title, Boolean completed, Integer order, String url) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
        this.url = url;
    }

    /**
     * @description: json converte
     * @param:   null
     * @return:
     * @auther: lzhaoyang
     * @date: 2019/9/4 10:16 PM
     */
    // 还未生成Converter的时候需要先注释掉，生成过后再取消注释
    public Todo(JsonObject obj) {
        TodoConverter.fromJson(obj, this);
    }

    public Todo(String jsonStr) {
        TodoConverter.fromJson(new JsonObject(jsonStr), this);
    }


    public static AtomicInteger getAcc() {
        return acc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public  void setIncId() {
        this.id = acc.getAndIncrement();
    }
    public static int  getIncId() {
        return acc.get();
    }
    public static void setIncIdWith(int n){
        acc.set(n);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order=" + order +
                ", url='" + url + '\'' +
                '}';
    }
}
