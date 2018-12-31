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

public class Entry<O> {
	
	 public O value;
	 public Float frequency;
		
	 public Entry(O value, Float frequency) {
		 this.value = value;
		 this.frequency = frequency;
	 }
	 
	/**
	 * Enumeration set for class assertion, role assertions, negative role
	 * assertion and GCIs
	 */
	public enum AxiomType {
		CLASS_ASSERTION, OBJECT_PROPERTY_ASSERTION, NEGATIVE_OBJECT_PROPERTY_ASSERTION, SUBCLASS_OF
	}

	 /**
	 * Enumeration set for negation, next, conjunction and disjunction and 
	 * until
	 */
	public enum FormulaType {

		// ATOM_PROP,
		NEGATION, CONJECTION, DISJUNCTION, NEXT, UNTIL
	}

}

