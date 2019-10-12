package com.zake.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 11:02 2019-10-10
 **/
public class ScannerDemo {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList ns = new ArrayList<>();

        do {
            String string = scanner.nextLine();
            if (string.equals("") || string.equals("quit")) {
                break;
            }
            ns.add(string);
        } while (true);


        DataUtil.verifyData(ns);
        Map map = DataUtil.doData(ns);
        map.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
    }
}