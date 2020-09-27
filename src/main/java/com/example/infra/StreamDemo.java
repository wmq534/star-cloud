package com.example.infra;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * @author wangmingqiang <wangmingqiang@kuaishou.com>
 * Created on 2020-05-23
 */
public class StreamDemo {

    public static void main(String[] args) {
        //Git is a distributed version control system.
        //Git is free software distributed under the GPL.
        //Git has a mutable index called stage.
        // Git tracks changes.
        // Git tracks changes new.

        // filter
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtedList = strings.stream().filter(str->!str.isEmpty()).collect(Collectors.toList());
        filtedList.forEach(str-> System.out.println(str));
        // forEach
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        //map
        List<Integer> numbers = Arrays.asList(3,2,3,3,7,5);
        List<Integer> squareList = numbers.stream().map(i->i*i).distinct().collect(Collectors.toList());
        squareList.forEach(System.out::println);

        // sorted
        Random random2 = new Random();
        random2.ints().limit(10).sorted().forEach(System.out::println);

        // parallelStream
        List<String> lists = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count2 = lists.parallelStream().filter(str -> str.isEmpty()).count();
        System.out.println("筛选列表: " + lists);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

    }
}
