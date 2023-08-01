package pers.wmx.test;

import com.alibaba.fastjson.JSON;

/**
 * @author wangmingxin03
 * Created on 2023-07-27
 */
public class Main2 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setCar(null);

        System.out.println(JSON.toJSONString(person));
    }
}
