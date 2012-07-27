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

public final class Runtime {
	final PrintStream out;
	final InputStream in;
	final byte[] memory;
	final int cursor;

	Runtime(PrintStream out, InputStream in, byte[] memory, int cursor) {
		this.out = out;
		this.in = in;
		this.memory = memory;
		this.cursor = cursor;
	}

	public boolean isZero() {
		return memory[cursor] == 0;
	}

	public Runtime write() {
		out.print((char) memory[cursor]);
		return this;
	}

	public Runtime read() throws IOException {
		return this.set(in.read());
	};

	public Runtime set(int v) {
		memory[cursor] = (byte) v;
		return this;
	}

	public Runtime right() {
		return new Runtime(out, in, memory, cursor + 1);
	}

	public Runtime left() {
		return new Runtime(out, in, memory, cursor - 1);
	}

	public Runtime incr() {
		return this.set(memory[cursor] + 1);
	}

	public Runtime decr() {
		return this.set(memory[cursor] - 1);
	}

}
