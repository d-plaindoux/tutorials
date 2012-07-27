package org.wolfgang.lambda.sample;

/**
 * Recursive lambda definition.
 * Warning such code is not efficient since the stack grows
 * even if the funtion is tail-recursive
 */
public class LambdaRecursion {

    interface FunInt {
        int apply(int i);
    }

    interface FunIntTR {
        int apply(int i,int j);
    }

    public static void main(String[] args) {
        final FunInt fact = x -> {
            if (x == 0) {
                return 1;
            } else {
                return x * fact.apply(x-1);
            }
        };

        fact.apply(20);

	// Tail recursive
        final FunIntTR factTR = (x,y) -> {
            if (y == 0) {
                return x;
            } else {
                return factTR.apply(x*y,y-1);
            }
        };

        factTR.apply(1,20);
   }

}
