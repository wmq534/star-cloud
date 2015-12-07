package com.github.requestserialid;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToString {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory
            .getLogger(ObjectToString.class);

    static {
//        DateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.YMDHMS);
//        SerializationConfig serConfig = mapper.getSerializationConfig().withDateFormat(dateFormat);
////        mapper.setSerializationConfig(serConfig);
//        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        // serConfig.setDateFormat(dateFormat);
        // DeserializationConfig deserializationConfig = mapper
        // .getDeserializationConfig().withDateFormat(dateFormat);
        // deserializationConfig.setDateFormat(dateFormat);
        // mapper.setDeserializationConfig(deserializationConfig);
//        mapper.configure(SerializationConfig.,false);
    }

    // 转换对象为String格式
    public static String convertToString(Object obj) {
        if (obj == null)
            return null;
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("{}转换为json出错,出错原因:{}", obj.toString(), e.getMessage());
        }
        return null;
    }

    public static void main(String[] agrs) throws Exception {
        System.out.println(convertToString(new java.sql.Date(System.currentTimeMillis())));
        System.out.println(convertToString(new Date()));
        System.out.println(convertToString(new Timestamp(System.currentTimeMillis())));
        System.out.println(convertToString(new BigDecimal(1212.2)));
    }

    // public class JsonBinder {
    // private ObjectMapper mapper = null;
    //
    // private JsonBinder(JsonSerialize.Inclusion inclusion) {
    // mapper = new ObjectMapper();
    // // 设置输出包含的属性
    // mapper.getSerializationConfig().withSerializationInclusion(
    // inclusion);
    // // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
    // mapper.configure(
    // SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,
    // false);
    // }
    //
    // private JsonBinder() {
    //
    // }
    //
    // /**
    // * 创建输出全部属性到Json字符串的Binder.
    // */
    // public JsonBinder buildNormalBinder() {
    // return new JsonBinder(JsonSerialize.Inclusion.ALWAYS);
    // }
    //
    // /**
    // * 创建只输出非空属性到Json字符串的Binder.
    // */
    // public JsonBinder buildNonNullBinder() {
    // return new JsonBinder(JsonSerialize.Inclusion.NON_NULL);
    // }
    //
    // /**
    // * 创建只输出初始值被改变的属性到Json字符串的Binder.
    // */
    // public JsonBinder buildNonDefaultBinder() {
    // return new JsonBinder(JsonSerialize.Inclusion.NON_DEFAULT);
    // }
    //
    // /**
    // * 创建只输出初始值被改变的属性到Json字符串的Binder.
    // */
    // public JsonBinder buildNonEmptyBinder() {
    // return new JsonBinder(JsonSerialize.Inclusion.NON_EMPTY);
    // }
   }