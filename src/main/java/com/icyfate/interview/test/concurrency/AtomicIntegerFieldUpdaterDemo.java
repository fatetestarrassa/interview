package com.icyfate.interview.test.concurrency;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/5 16:46
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");
        User user = new User("tom",22);
        System.out.println(a.getAndIncrement(user));
        System.out.println(a.get(user));
    }
}

class User {
    private String name;
    public volatile int age;

    public User(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
