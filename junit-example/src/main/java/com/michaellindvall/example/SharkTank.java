/**
 * 
 */
package com.michaellindvall.example;

/**
 * @author mlindvall
 * 
 */
public class SharkTank {

  public static final String SALES_AWESOME = "awesome sales";
  public static final String SALES_GREAT = "great sales";
  public static final String SALES_NON = "no sales";
  public static final String SALES_OK = "ok sales";
  private static final int SALES_AWESOME_TARGET = 1000000;
  private static final int SALES_GOOD_TARGET = 50000;
  private static final int SALES_OK_TARGET = 100000;

  public String yearlySalesStatus(int yearlySales) {
    String answer = SALES_NON;
    if (yearlySales >= SALES_AWESOME_TARGET) {
      answer = SALES_AWESOME;
    } else if (yearlySales >= SALES_OK_TARGET) {
      answer = SALES_GREAT;
    } else if (yearlySales >= SALES_GOOD_TARGET) {
      answer = SALES_OK;
    } else {
      answer = SALES_NON;
    }
    return answer;
  }
}
