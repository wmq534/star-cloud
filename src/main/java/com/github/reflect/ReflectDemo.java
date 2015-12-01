package com.github.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {
  public static void main(String[] args) {
    MyClass myClass = new MyClass(0); // 一般做法
    myClass.increase(2);
    System.out.println("Normal -> " + myClass.count);
    try {
      // 获取构造方法
      Constructor<MyClass> constructor = MyClass.class.getConstructor(int.class);
      // 创建对象
      MyClass myClassReflect = (MyClass) constructor.newInstance(10);
      // 获取方法
      Method method = MyClass.class.getMethod("increase", int.class);
      // 调用方法
      method.invoke(myClassReflect, 5);
      // 获取域
      Field field = MyClass.class.getField("count");
      // 获取域的值
      System.out.println("Reflect -> " + field.getInt(myClassReflect));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



}
