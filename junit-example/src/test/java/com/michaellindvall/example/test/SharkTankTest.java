/**
 * 
 */
package com.michaellindvall.example.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.michaellindvall.example.SharkTank;

/**
 * @author mlindvall
 * 
 */
public class SharkTankTest {

  @Test
  public final void yearSalesStatusTest() {
    SharkTank st = new SharkTank();
    assertEquals(SharkTank.SALES_AWESOME, st.yearlySalesStatus(1000000));
    assertEquals(SharkTank.SALES_GREAT, st.yearlySalesStatus(100000));
    assertEquals(SharkTank.SALES_OK, st.yearlySalesStatus(50000));
    assertEquals(SharkTank.SALES_NON, st.yearlySalesStatus(0));
  }
}
