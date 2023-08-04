package pers.wmx.test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.github.phantomthief.pool.KeyAffinityExecutor;
import com.google.common.collect.Lists;

/**
 * @author wangmingxin03
 * Created on 2021-04-15
 */
public class AffinityThreadPool {
    static KeyAffinityExecutor executor =
            KeyAffinityExecutor.newSerializingExecutor(8, 200, "MY-POOL");

    static ExecutorService normalExecutor = Executors.newFixedThreadPool(8);


    public static void main(String[] args) {
        List<Person1> person1List = mockData();

        // 无法保证顺序性
//        personList.forEach(person -> {
//            normalExecutor.execute(() -> System.out.println(person.getName()));
//        });

        person1List.forEach(person1 -> {
            executor.executeEx(person1.getId(), () -> System.out.println(person1.getName()));
        });

    }

    private static List<Person1> mockData() {
        List<Person1> person1List = Lists.newArrayList();
        Person1 p1 = new Person1(1, "a");
        Person1 p2 = new Person1(2, "bbb");
        Person1 p3 = new Person1(1, "aaa");
        Person1 p4 = new Person1(1, "aa");
        Person1 p5 = new Person1(2, "b");
        person1List.add(p1);
        person1List.add(p2);
        person1List.add(p3);
        person1List.add(p4);
        person1List.add(p5);
        return person1List;
    }
}

class Person1 {
    private int id;

    private String name;

    public Person1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
