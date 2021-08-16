package com.github.sqlsalesproject.sale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseTest {

    @Test
    @DisplayName("Ensures the date of purchase is working correctly")
    public void testDateReturn() {
        //Simple test for date that would reasonably occur
        Purchase test1 = new Purchase("8:20 PM 08-20-2002", new ArrayList<>());
        assertEquals(test1.getDateAsString(),"8:20 PM 08-20-2002");
        //Wanted to make sure multi-digit hours still worked with only 1 h in pattern string
        Purchase test2 = new Purchase("12:20 AM 12-01-2021", new ArrayList<>());
        assertEquals(test2.getDateAsString(),"12:20 AM 12-01-2021");
        //Was curious how using numbers outside of hour and month range would affect output
        Purchase test3 = new Purchase("14:20 AM 13-10-2028", new ArrayList<>());
        assertEquals(test3.getDateAsString(),"2:20 PM 01-10-2029");
    }
}