package com.example.infra;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangmingqiang <wangmingqiang@kuaishou.com>
 * Created on 2020-06-09
 */
public class LambdaDemo {
    public static void main(String[] args) {
        new Thread(()->{System.out.println("hello");}).start();
        Runnable run = ()-> System.out.println("Hello world");
        ActionListener listener = event -> System.out.println("button clicked");
        reduceFun();

    }

    private static void reduceFun(){
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1,s2)->s1.length()>=s2.length()?s1:s2);
        //Optional<String> longest = stream.max((s1, s2) -> s1.length()-s2.length());
        System.out.println(longest.get());
        Stream<String> stream1 = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream1.reduce(0,(sum,str)->sum+str.length(),(a,b)->a+b);
        System.out.println("lengthSum="+lengthSum);
    }

    private static void collectFun(){
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        List<String> list = stream.collect(Collectors.toList());
        Set<String> set = stream.collect(Collectors.toSet());
        Map<String,Integer> map = stream.collect(Collectors.toMap(Function.identity(),String::length));
    }
}
