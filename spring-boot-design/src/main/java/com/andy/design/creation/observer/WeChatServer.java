package com.andy.design.creation.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 被观察者，也就是微信公众号服务
 * 实现了BeObserver接口，对BeObserver接口的三个方法进行了具体实现
 *
 * @author: Leone
 * @since: 2018-08-22
 **/
public class WeChatServer implements BeObserver {

    //注意到这个List集合的泛型参数为Observer接口，设计原则:面向接口编程而不是面向实现编程

    private List<Observer> list;

    private String message;

    public WeChatServer() {
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!list.isEmpty())
            list.remove(o);
    }

    //遍历
    @Override
    public void notifyObserver() {
        for (int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update(message);
        }
    }

    public void setInfomation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }

}