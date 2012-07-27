package org.wolfgang.lambda.trait;

import java.util.*;
import java.util.functions.*;

/**
 * Trait like  java proposition. This relies  on method implementation
 * in an interface.
 */
public interface Traits {

    interface A {
        int m();

        void n() default {
            m();
        }
    }

    interface B {
        int m() default {
            return 1;
        }
    }

    static class C implements A, B {

        public int m() {
            return A.super.m();
        }

    };

    interface SList<E> {
        int size();

        boolean isEmpty() default {
            return this.size() == 0;
        }

        // ...
    }
}
