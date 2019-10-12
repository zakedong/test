package com.zake.util;


import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sun.plugin.dom.html.ns4.NS4DOMObject;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRate;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 11:18 2019-10-10
 **/

public class DataUtil {


    /**
     * 参数校验
     *
     * @param ns
     */
    public static void verifyData(ArrayList<String> ns) {
        ns.forEach(key -> {
            String[] split = key.split("\\s+");
            for (int i = 0; i < split.length; i++) {
                if (i % 2 == 1) {
                    try {
                        new BigDecimal(split[i]).toString();
                    } catch (Exception e) {
                        throw new RuntimeException("货币金额格式错误");
                    }
                } else {
                    try {
                        Monetary.getCurrency(split[i]);
                    } catch (Exception e) {
                        throw new RuntimeException("货币种类格式错误" + "=======" + split[i]);
                    }
                }
            }
        });
    }


    public static Map doData(ArrayList<String> ns) {
        Set hashSet = new HashSet();
        Map map = new ConcurrentHashMap(16);
        ns.forEach(key -> {
            String[] split = key.split("\\s+");
            boolean add = hashSet.add(split[0]);
            if (add) {
                //添加成功表示之前没有这个货币种类
                String exchange = split[1];
                if (!("USD".equals(split[0]))) {
                    exchange = exchange(split[0], split[1]);
                }
                hashSet.add(split[0]);
                map.put(split[0], exchange);
            } else {
                String exchange = split[1];
                String string = "";
                if ("USD".equals(split[0])) {
                   //货币是美元
                    String value = (String) map.get(split[0]);
                    BigDecimal decimal = new BigDecimal(value);
                    BigDecimal decimalValue = new BigDecimal(split[1]);
                    string = decimal.add(decimalValue).toString();
                }else {
                    //货币不是美元
                    String value = (String) map.get(split[0]);
                    String str = value.substring(0, value.indexOf("("));
                    BigDecimal decimal = new BigDecimal(str);
                    BigDecimal decimalValue = new BigDecimal(split[1]);
                    string = decimal.add(decimalValue).toString();
                    string = exchange(split[0], string);
                }
                map.put(split[0], string);
            }
        });
        return map;
    }

    private synchronized static String exchange(String key, String value) {
        CurrencyConversion dollarConversion = MonetaryConversions.getConversion("USD");
        MonetaryAmount tenEuro = Money.of(new BigDecimal(value), key);
        MonetaryAmount inDollar = tenEuro.with(dollarConversion);
        value = value +"("+ "" + inDollar + ")";
        return value;
    }

}
