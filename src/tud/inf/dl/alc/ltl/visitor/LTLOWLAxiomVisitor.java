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

package tud.inf.dl.alc.ltl.visitor;

import tud.inf.dl.alc.ltl.axioms.AtomicAxiom;
import tud.inf.dl.alc.ltl.axioms.ConjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.DisjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.NegativeAxiom;
import tud.inf.dl.alc.ltl.axioms.NextAxiom;
import tud.inf.dl.alc.ltl.axioms.UntilAxiom;
/**
 * LTLOWLAxiomVisitor does the following task(s): 
 * a. LTLAxioms visitor interface 
 *   
 * @version
 * @author
 */


public interface LTLOWLAxiomVisitor {
	
	public String visit(AtomicAxiom at_axiom);
	public String visit(ConjunctiveAxiom conj_axiom);
	public String visit(DisjunctiveAxiom disj_axiom);
	public String visit(NegativeAxiom neg_axiom);
	public String visit(NextAxiom nex_axiom);
	public String visit(UntilAxiom until_axiom);

}
