

package org.wolfgang.lambda.sample;

/**
 * This code shows the lambda equivalence capability.
 */
public class LambdaSwap {

    interface Exp1 {
        int evalExp1();
    }

    interface Exp2 {
        int evalExp2();
    }

    private static Exp2 swap(Exp1 e) {
        return e::evalExp1;
    }

    public static void main(String[] args) {
        final Exp1 exp1 = () -> 1;
        swap(exp1).evalExp2();
    }

}
