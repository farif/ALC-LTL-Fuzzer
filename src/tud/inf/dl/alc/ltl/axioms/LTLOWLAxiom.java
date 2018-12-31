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
 * The expressions are defined by the BNF rules:
 * 
 * <expression> ::= [ "-" | "X" ] <term> [ [ "+" | "-" | "U" ] <term> ]
 * <term> 		::= <OWLAxiom> | "(" <expression> ")".
 * 
 **/

public interface LTLOWLAxiom {

	public LTLOWLAxiom getLeftAxiom();

	public LTLOWLAxiom getRightAxiom();

	public OWLAxiom getOWLAxiom();

	public String accept(LTLOWLAxiomVisitor visitor);

	public void accept(LTLXMLAxiomVisitior visitor);


}
