/*
 * Copyright (C)2012 D. Plaindoux.
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; see the file COPYING.  If not, write to
 * the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package org.wolfgang.lambda.brainf_ck;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Stack;

/**
 * <code>Compiler</code> is dedicated to the code generation. For this purpose
 * the main behavior takes a program (a byte array) and returns a BrainF_ck like
 * object code.
 * 
 * @author D. Plaindoux
 */
public class Compiler {

	/**
	 * <code>Debug</code> defines the debug functional interface
	 * 
	 * @author D. Plaindoux
	 */
	interface Debug {
		BrainFun apply(byte b, BrainFun fun);
	}

	/**
	 * The compiler
	 * 
	 * @param program
	 *            The program to compile
	 * @param isDebug
	 *            The debug switch
	 */
	public static BrainFun compile(byte[] program, boolean isDebug) {
        final Stack<BrainFun> code = new Stack<>();
        code.push(e -> e);

        final Debug debug;

        if (isDebug) {
            debug = (b, fun) -> env -> { 
            	System.err.println("executing <" + (char)b + "> with " + Arrays.toString(env.memory)); 
            	return fun.apply(env); 
            };
        } else {
            debug = (b, fun) -> fun;
        }

        for(byte b: program) {
            assert code.size() > 0;
            switch (b) {
                case '>': code.push(code.pop().seq(debug.apply(b, Runtime::right))); break;
                case '<': code.push(code.pop().seq(debug.apply(b, Runtime::left))); break;
                case '+': code.push(code.pop().seq(debug.apply(b, Runtime::incr))); break;
                case '-': code.push(code.pop().seq(debug.apply(b, Runtime::decr))); break;
                case '.': code.push(code.pop().seq(debug.apply(b, Runtime::write))); break;
                case ',': code.push(code.pop().seq(debug.apply(b, Runtime::read))); break;
                case '[': code.push(debug.apply(b, e -> e)); break;
                case ']':
                    final BrainFun loop = code.pop().doLoop();
                    code.push(code.pop().seq(debug.apply(b, loop)));
                    break;
            }
        }

        return code.pop();
    }
	/*
	 * public static void main(String[] args) throws IOException { BrainFun
	 * program = compile(args[0].getBytes(), System.getProperty("bf.debug") !=
	 * null); program.apply(new Runtime(System.out, System.in, new byte[Integer
	 * .getInteger("bf.buffer", 30000)], 0)); System.out.println(); }
	 */

}
