/*
 * Copyright (C) ALC-LTL Formula Generator
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tud.inf.dl.alc.ltl.util;

import java.util.Set;

/**
 * SymbolGenerator does the following tasks: 
 * 1. Generate Names according to provided pattern.
 * 
 * @version
 * @author
 */

public class RandomGenerator {

	/**
	 * Generate and store string according to provided simple pattern
	 * 
	 * @param pattern
	 * @param symbol_set
	 * @param index
	 */
	public static void fixfillSet(String pattern, Set<String> symbol_set,
			int index) {

		while (index > 0) {
			symbol_set.add(pattern + index);
			index--;
		}

	}
	
}
