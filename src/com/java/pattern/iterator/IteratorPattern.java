package com.java.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Iterator : 行为设计模式之三 迭代器模式
 *
 * 定义：提供一种方法顺序访问一个容器(聚合)对象中各个元素，而又不暴露该对象的内部表示
 *
 * Iterator：迭代器角色，定义访问和遍历元素的接口；
 * ConcreteIterator：具体迭代器角色，实现接口中的方法，并且记录遍历的当前位置；
 * Container：容器角色，提供创建具体迭代器角色的接口；
 * ConcreteContainer：具体容器角色，具体迭代器角色与容器相关联。
 * Created by Administrator on 2017/11/11.
 */
public class IteratorPattern {
}

//容器中的元素
class Song{
    public Song(String name,String singer){
        this.name = name;
        this.singer = singer;
    }
    private String name;
    private String singer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }
}

//抽象迭代器
interface Iterator{
    Song first();
    Song next();
    boolean hasNext();
    Song currentItem();
}

//抽象容器
interface SongList{
    Iterator getIterator();
}

//具体容器，集成抽象容器，并定义一个具体迭代器内部类
class MyStoreList implements  SongList{

    private List<Song> songList = new ArrayList<>();

    public  MyStoreList(List<Song> songs){
        this.songList = songs;
    }
    @Override
    public Iterator getIterator() {
        return null;
    }

    private class SongListIterator implements Iterator{

        private int cursor;
        @Override
        public Song first() {
            cursor = 0;
            return songList.get(cursor);
        }

        @Override
        public Song next() {
            Song song = null;
            cursor++;
            if(hasNext()){
                song = songList.get(cursor);
            }
            return song;
        }

        @Override
        public boolean hasNext() {
            return !(cursor == songList.size());
        }

        @Override
        public Song currentItem() {
            return songList.get(cursor);
        }
    }
}