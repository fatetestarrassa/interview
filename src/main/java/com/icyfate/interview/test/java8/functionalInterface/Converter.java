package com.icyfate.interview.test.java8.functionalInterface;

@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);
}
