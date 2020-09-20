package org.geekbang.thinking.in.spring.event.my;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Observer
 * @since
 */
public class MyObserverDemo {

    public static void main(String[] args) {
        //1.发布者
        Observable observable = new EventObservable();
        //2.添加监听者
        observable.addObserver(new EventObserver());
        //3.发布消息
        observable.notifyObservers("hello world");

    }

    //----第二版
    //发布者
    static class EventObservable extends Observable{
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }

        @Override
        protected  void setChanged() {
            super.setChanged();
        }
    }

    //监听者 实现 EventListener 标识是监听者
    static class EventObserver implements Observer, EventListener{

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;

            System.out.println("接收到消息 : " + eventObject);
        }
    }

    //----第一版
    //发布者
//    static class EventObservable extends Observable{
//        @Override
//        public void notifyObservers(Object arg) {
//            setChanged();
//            super.notifyObservers(arg);
//            clearChanged();
//        }
//
//        @Override
//        protected  void setChanged() {
//            super.setChanged();
//        }
//    }
//
//    //监听者
//    static class EventObserver implements Observer{
//
//        @Override
//        public void update(Observable o, Object arg) {
//            System.out.println("接收到消息 : " + arg);
//        }
//    }
}
