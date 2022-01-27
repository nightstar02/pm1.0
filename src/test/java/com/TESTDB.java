package com;

import com.peoplemoney.util.DBUtil;
import org.junit.Test;

public class TESTDB {
@Test
    public  void testDB(){
        System.out.println(DBUtil.getConnection());
    }
}
