package pers.wmx.test;

import java.lang.reflect.Field;

import com.alibaba.fastjson.JSON;

/**
 * @author wangmingxin03
 * Created on 2022-08-01
 */
public class JsonTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String json = "{ \"id\": \"A10001\", \"name\": \"Arthur.Zhang\", \"score\": 100 }";

        String jsonStr = "{ \"redPackPassword\": \"你好啊\"}";
        String str = json.substring(1, json.length() - 1);

        String []filedStr = str.split(",");
        MyBean bean = new MyBean();

        for (String item : filedStr) {
            String []param = item.split(":");

            String key = param[0].replaceAll("\"", "").trim();
            String value = param[1].replaceAll("\"", "").trim();

            Field filed = MyBean.class.getDeclaredField(key);
            if (filed.getType() == String.class) {
                filed.set(bean, value);
            } else if (filed.getType() == Integer.class) {
                filed.set(bean, Integer.valueOf(value));
            }
        }

        // MyBean{id='A10001', name='Arthur.Zhang', score=100}
        System.out.println(bean);

        MyBean bean1 = JSON.parseObject(json, MyBean.class);
        System.out.println(bean1);

    }
}
