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
import java.util.Random;
import java.util.Vector;

public class Probability<O> {

	private Vector<Entry<O>> table;	
	private Random gen;

	/**
	 * Setting user specified probability Table
	 * @param table
	 */
	public Probability(Vector<Entry<O>> table) {
		
		float freq = 0;
		this.table = table;
		this.gen = new Random();
		
		for(Entry<O> e: this.table) {
			freq += e.frequency;
		}
		
		if(freq == 0.0) {
			System.out.print("Probability Distribution is not defined Correctly");
			System.exit(-1);
	
		}
		
	}
	/**
	 * Return Random Probability for 
	 * a. Concept Description Operator selection
	 * b. ALC-assertion axiom Operator selection
	 * c. ALC-LTL formula Operator selection
	 * 
	 * @return
	 */
	public O getRandomElement() {

		float freq = (float)Math.random();
		int v_freq = this.gen.nextInt(this.table.size());
		
		Entry<O> e_0 = (Entry<O>) this.table.elementAt(v_freq);
		
		while(e_0.frequency == 0.0) {
			v_freq = this.gen.nextInt(this.table.size());
			e_0 = (Entry<O>) this.table.elementAt(v_freq);
		}
		
		for (Entry<O> e : this.table) {
			
			if(freq < e.frequency &&  e.frequency > e_0.frequency) {
				 return e.value;
			}
			  
		}

		return e_0.value; 

	}
		
}
