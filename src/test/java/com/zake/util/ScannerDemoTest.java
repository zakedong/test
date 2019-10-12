//package com.zake.util;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * @author: zake
// * @Description:
// * @Date: Created in 15:15 2019-10-10
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ScannerDemoTest {
//
//    @Autowired
//    private DataUtil dataUtil;
//
//    @Test
//    public void addAppPermission() {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList ns = new ArrayList<>();
//        do {
//            String string = scanner.nextLine();
//            if (string.equals("")) {
//                break;
//            }
//            ns.add(string);
//        } while (true);
//        System.out.println(ns);
//        dataUtil.verifyData(ns);
//        dataUtil.doData(ns);
//    }
//}