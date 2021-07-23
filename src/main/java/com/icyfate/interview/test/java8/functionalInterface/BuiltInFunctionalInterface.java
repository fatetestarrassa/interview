package com.icyfate.interview.test.java8.functionalInterface;

import com.icyfate.interview.test.container.Person;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/20 14:34
 */
public class BuiltInFunctionalInterface {

    public static void main(String[] args) {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));

        Consumer<Person> greeter = p -> System.out.println("hello " + p.getName());
        greeter.accept(new Person(11,"peter"));

        Optional<String> optional = Optional.of("bam");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));
        optional.ifPresent((s)-> System.out.println(s.charAt(0)));



    }

}
