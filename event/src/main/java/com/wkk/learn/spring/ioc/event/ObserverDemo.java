package com.wkk.learn.spring.ioc.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * Java事件监听示例
 *
 * @author Wangkunkun
 * @date 2021/6/17 16:00
 * @see java.util.Observer
 * @see java.util.Observable
 */
public class ObserverDemo {

    public static void main(String[] args) {
        Observable observable = new EventObservable();
        observable.addObserver(new EventObserver());
        observable.notifyObservers(new EventObject("Hello"));
    }

    static class EventObservable extends Observable {

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("收到消息：" + arg);
        }
    }
}
