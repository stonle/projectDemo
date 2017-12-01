package com.java.concurrent;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentNavigableMap：并发导航映射
 * Created by Administrator on 2017/11/22.
 * java.util.concurrent.ConcurrentNavigableMap 是一个支持并发访问的 java.util.NavigableMap，
 * 它还能让它的子 map 具备并发访问的能力。所谓的 "子 map" 指的是诸如 headMap()，subMap()，tailMap()
 * 之类的方法返回的 map。
 * NavigableMap 中的方法不再赘述，本小节我们来看一下 ConcurrentNavigableMap 添加的方法。
 *
 */
public class ConcurrentNavigableMapDemo {
    public static void main(String[] args) {
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        //headMap 将指向一个只含有键 "1" 的 ConcurrentNavigableMap，因为只有这一个键小于 "2"。关于这个方法及其重载
        ConcurrentNavigableMap headMao = map.headMap("2");
        System.out.println(headMao);
        //remove
        /**
         * if (map.containsKey(key) && map.get(key).equals(value)) {
         map.remove(key);
         return true;
         }
         return false;
         */
    }
}
