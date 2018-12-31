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

package tud.inf.dl.alc.ltl.axioms;

import org.semanticweb.owlapi.model.OWLAxiom;

import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.visitor.LTLOWLAxiomVisitor;
import tud.inf.dl.alc.ltl.visitor.LTLXMLAxiomVisitior;
/**
 * NextAxiom does the following task(s): 
 * 1. Define the Next LTLAxiom
 * 
 * @version
 * @author
 */

public class NextAxiom implements LTLOWLAxiom {

	private String operand;
	private LTLOWLAxiom ltl_axiom;

	public NextAxiom(LTLOWLAxiom left) {
		this.operand = "NEXTOF";
		this.ltl_axiom = left;
	}

	public String toString() {

		StringBuffer str_bf = new StringBuffer();

		str_bf.append(operand);
		str_bf.append("(");
		str_bf.append(ltl_axiom);
		str_bf.append(")");

		return str_bf.toString();
	}

	public String accept(LTLOWLAxiomVisitor visitor) {
		ltl_axiom.accept(visitor);
		return visitor.visit(this);
	}

	public void accept(LTLXMLAxiomVisitior visitor) {

		visitor.visit(this);

	}

	public LTLOWLAxiom getLeftAxiom() {
		return ltl_axiom;
	}

	public LTLOWLAxiom getRightAxiom() {
		return null;
	}

	public OWLAxiom getOWLAxiom() {
		return null;
	}

}
