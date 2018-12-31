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

import tud.inf.dl.alc.ltl.visitor.LTLOWLAxiomVisitor;
import tud.inf.dl.alc.ltl.visitor.LTLXMLAxiomVisitior;

/**
 * AtomicAxiom does the following task(s): 
 * 1. Define the Atomic LTLAxiom which contains OWLAxiom.
 * 
 * @version
 * @author
 */

public class AtomicAxiom implements LTLOWLAxiom {

	private OWLAxiom owl_axiom;

	public AtomicAxiom(OWLAxiom axiom) {
		this.owl_axiom = axiom;
	}

	public OWLAxiom getOWLAxiom() {
		return this.owl_axiom;
	}

	public OWLAxiom setOWLAxiom(OWLAxiom ax) {
		return this.owl_axiom = ax;
	}

	public String toString() {
		return this.owl_axiom.toString();
	}

	public LTLOWLAxiom getLeftAxiom() {
		return null;
	}

	public LTLOWLAxiom getRightAxiom() {
		return null;
	}

	public String accept(LTLOWLAxiomVisitor visitor) {
		return visitor.visit(this);
	}

	public void accept(LTLXMLAxiomVisitior visitor) {
		visitor.visit(this);
	}

}
