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
 * LTLXMLAxiomVisitior does the following task(s): 
 * a. LTL XML visitor interface 
 *   
 * @version
 * @author
 */

public interface LTLXMLAxiomVisitior {

	public void visit(AtomicAxiom at_axiom);
	public void visit(ConjunctiveAxiom conj_axiom);
	public void visit(DisjunctiveAxiom disj_axiom);
	public void visit(NegativeAxiom neg_axiom);
	public void visit(NextAxiom nex_axiom);
	public void visit(UntilAxiom until_axiom);

}
