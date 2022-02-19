package com.bism.xctest;

import javafx.beans.binding.ObjectExpression;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

@SpringBootTest
//@RunWith(SpringRunner.class)
class XctestApplicationTests {

    public  <T> T cast(Object obj){
        return (T)obj;
    }

    public  <T> T convert(String value,Class<T>clazz){
        return cast(value);
    }

    @Test
    void test(){
        String numStr = "123";
        Integer dd =Integer.parseInt(numStr);
        //Integer num = this.cast(numStr);
        Integer aa = this.convert(numStr,Integer.class);
        System.out.println(dd);
        //System.out.println(num);
        System.out.println(aa);

    }


    @Test
    void contextLoads() {
        String xc = "this is String";
        Object obj = xc;
        System.out.println(obj);
        System.out.println(obj instanceof String);
        System.out.println(obj.toString());
        System.out.println((String)obj);


    }

}
