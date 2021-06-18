package com.icyfate.interview.test.container;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/18 16:03
 */
public class Person {

    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        TreeMap<Person,String> treeMap = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(),o2.getAge());
            }
        });

        treeMap.put(new Person(13,"张13"),"张13");
        treeMap.put(new Person(12,"张12"),"张12");
        treeMap.put(new Person(8,"张8"),"张8");
        treeMap.put(new Person(22,"张22"),"张22");

        treeMap.entrySet().stream().forEach(personStringEntry -> {
            System.out.println(personStringEntry.getValue());
        });

    }


}
