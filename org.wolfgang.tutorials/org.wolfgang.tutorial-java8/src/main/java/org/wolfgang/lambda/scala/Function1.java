package org.wolfgang.lambda.scala;

/**
 * E -> T functional type representation [Scala]
 * @param <T>
 */
public interface Function1<E,T> {
    T apply(E e);
}
