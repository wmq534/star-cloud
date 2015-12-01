package com.github.reflect;

class MyClass {
  public int count;

  public MyClass(int start) {
    count = start;
  }

  public void increase(int step) {
    count = count + step;
  }
}
