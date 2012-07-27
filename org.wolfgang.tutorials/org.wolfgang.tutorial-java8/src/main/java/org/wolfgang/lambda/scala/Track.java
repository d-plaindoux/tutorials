package org.wolfgang.lambda.scala;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Tour of Scala: Anonymous Function Syntax
 * Created by admin on 2008-07-05. Updated: 2009-08-24, 20:05
 * Scala provides a relatively lightweight syntax for defining anonymous functions. The following expression creates a successor function for integers:
 * (x: Int) => x + 1
 * This is a shorthand for the following anonymous class definition:
 * new Function1[Int, Int] {
 * def apply(x: Int): Int = x + 1
 * }
 * It is also possible to define functions with multiple parameters:
 * (x: Int, y: Int) => "(" + x + ", " + y + ")"
 * or with no parameter:
 * () => { System.getProperty("user.dir") }
 * There is also a very lightweight way to write function types. Here are the types of the three functions defined above:
 * Int => Int
 * (Int, Int) => String
 * () => String
 * This syntax is a shorthand for the following types:
 * Function1[Int, Int]
 * Function2[Int, Int, String]
 * Function0[String]
 */
public final class Track {

    private Track() {
    }

    public static <T> Function0<T> track(Function0<Void> trackerIn, Function0<Void> trackerOut, Function0<T> e) {
        return  () -> {
                trackerIn.apply();
                try {
                    return e.apply();
                } finally {
                    trackerOut.apply();
                }
        };
    }

    public static <E,T> Function1<E,T> track(Function0<Void> trackerIn, Function0<Void> trackerOut, Function1<E,T> e) {
        return  (p) -> {
                trackerIn.apply();
                try {
                    return e.apply(p);
                } finally {
                    trackerOut.apply();
                }
        };
    }

    public static <E1,E2,T> Function2<E1,E2,T> track(Function0<Void> trackerIn, Function0<Void> trackerOut, Function2<E1,E2,T> e) {
        return  (p1,p2) -> {
                trackerIn.apply();
                try {
                    return e.apply(p1,p2);
                } finally {
                    trackerOut.apply();
                }
        };
    }
}
