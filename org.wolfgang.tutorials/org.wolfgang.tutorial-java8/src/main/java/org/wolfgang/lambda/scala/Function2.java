package org.wolfgang.lambda.scala;

/**
 * (E1,E2) -> T functional type representation [Scala]
 * @param <T>
 */
public interface Function2<E1,E2,T> {
    T apply(E1 e1, E2 e2);
}
