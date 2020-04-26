package com.atliwen.web;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 李文
 * @create 2020-04-17 10:48
 **/
public class dataTest
{
    @Test
    public void test1()
    {
        //Random random = new Random();
        //for (int i = 0; i < 100; i++) {
        //    int number = random.nextInt(4 - 1 + 1) + 1;
        //    System.out.println(number);
        //
        //}
        Date date = new Date();//获取当前时间
        for (int i = 0; i < 100; i++) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, -i);//当前时间减去一年，即一年前的时间
            //calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
            calendar.getTime();//获取一年前的时间，或者一个月前的时间
            System.out.println(calendar.getTime().toString());
        }
    }
}
