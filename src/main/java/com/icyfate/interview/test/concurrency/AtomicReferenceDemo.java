package com.icyfate.interview.test.concurrency;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/5 15:18
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Person> ar = new AtomicReference<>();
        Person person = new Person("tom",21);
        ar.set(person);
        Person updatePerson = new Person("jerry",22);
        ar.compareAndSet(person,updatePerson);

        System.out.println(ar.get().getName());
        System.out.println(ar.get().getAge());
    }

}
class Person{
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
