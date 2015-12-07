package com.github.requestserialid;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 流水号生成工具类
 * 
 * @author wangmingqiang
 *
 */
public class GenerateSequenceUtils {

  private GenerateSequenceUtils() {};

  private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

  private static final Format dateFormat = new SimpleDateFormat("MMddHHmmssS");

  private static final NumberFormat numberFormat = new DecimalFormat("0000");

  private static int seq = 0;

  private static final int MAX = 9999;

  /**
   * 根据时间格式生成序列
   * 
   * @return String
   */
  public static synchronized String generateSequenceNo() {

    Calendar rightNow = Calendar.getInstance();

    StringBuffer sb = new StringBuffer();

    dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

    numberFormat.format(seq, sb, HELPER_POSITION);

    if (seq == MAX) {
      seq = 0;
    } else {
      seq++;
    }

    return sb.toString();
  }

  public static void main(String[] args) {

  }
}
