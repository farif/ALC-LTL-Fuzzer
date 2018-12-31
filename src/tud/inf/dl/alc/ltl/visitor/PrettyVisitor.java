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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.util.OWLObjectVisitorExAdapter;

import tud.inf.dl.alc.ltl.axioms.AtomicAxiom;
import tud.inf.dl.alc.ltl.axioms.ConjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.DisjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.NegativeAxiom;
import tud.inf.dl.alc.ltl.axioms.NextAxiom;
import tud.inf.dl.alc.ltl.axioms.UntilAxiom;

/**
 * This class is used to convert an axiom or class expression to a human
 * readable string.
 * 
 * @author Marcel Lippmann
 * @coauthor M. Fareed Arif
 */
public class PrettyVisitor extends

OWLObjectVisitorExAdapter<String> implements LTLOWLAxiomVisitor {

	
	public String visit(OWLClass arg0) {

		if (arg0.isOWLThing()) {

			return "⊤";

		} else if (arg0.isOWLNothing()) {

			return "⊥";

		} else {

			return arg0.getIRI().getFragment();
		}
	}

	
	public String visit(OWLClassAssertionAxiom arg0) {

		String classExpression = arg0.getClassExpression().accept(this);

		String ind = arg0.getIndividual().asOWLNamedIndividual().getIRI()
				.getFragment();

		return ind + " : " + classExpression;
	}

	
	public String visit(OWLNegativeObjectPropertyAssertionAxiom arg0) {

		String a = arg0.getSubject().asOWLNamedIndividual().getIRI()
				.getFragment();
		String b = arg0.getObject().asOWLNamedIndividual().getIRI()
				.getFragment();
		String r = arg0.getProperty().asOWLObjectProperty().getIRI()
				.getFragment();

		return "¬" + r + "(" + a + ", " + b + ")";
	}

	
	public String visit(OWLSubClassOfAxiom arg0) {

		String subclsExpression = arg0.getSubClass().accept(this);
		String superclsExpression = arg0.getSuperClass().accept(this);

		return   subclsExpression  + " ⊑ " +   superclsExpression ;
	}

	
	public String visit(OWLObjectAllValuesFrom arg0) {

		String r = arg0.getProperty().asOWLObjectProperty().getIRI()
				.getFragment();

		String classExpression = arg0.getFiller().accept(this);

		return "∀" + r + " . " + classExpression;
	}

	
	public String visit(OWLObjectComplementOf arg0) {

		String classExpression = arg0.getOperand().accept(this);

		return "¬" + classExpression;
	}

	
	public String visit(OWLObjectIntersectionOf arg0) {

		List<String> newOperands = new ArrayList<String>();

		for (OWLClassExpression operand : arg0.getOperands()) {

			newOperands.add(operand.accept(this));
		}

		Iterator<String> iter = newOperands.iterator();

		int noOfOperands = arg0.getOperands().size();

		if (noOfOperands != 0) {

			StringBuffer buffer = noOfOperands > 1 ? new StringBuffer("(")
					: new StringBuffer();

			for (int i = 0; i < noOfOperands - 2; i++) {

				buffer.append("(");
			}

			buffer.append(iter.next());

			while (iter.hasNext()) {
				buffer.append(" ⊓ " + iter.next() + ")");
			}

			return buffer.toString();

		}

		return "⊤";
	}

	public String visit(OWLObjectOneOf arg0) {

		// We allow only for {a}
		// not {a,...,z}
		String ind = arg0.getIndividuals().iterator().next()
				.asOWLNamedIndividual().getIRI().getFragment();

		return "{" + ind + "}";
	}

	public String visit(OWLObjectPropertyAssertionAxiom arg0) {

		String a = arg0.getSubject().asOWLNamedIndividual().getIRI()
				.getFragment();
		String b = arg0.getObject().asOWLNamedIndividual().getIRI()
				.getFragment();

		String r = arg0.getProperty().asOWLObjectProperty().getIRI()
				.getFragment();

		return r + "(" + a + ", " + b + ")";
	}

	public String visit(OWLObjectSomeValuesFrom arg0) {

		String r = arg0.getProperty().asOWLObjectProperty().getIRI()
				.getFragment();

		String classExpression = arg0.getFiller().accept(this);

		return "∃" + r + " . " + classExpression;
	}

	public String visit(OWLObjectUnionOf arg0) {

		List<String> newOperands = new ArrayList<String>();

		for (OWLClassExpression operand : arg0.getOperands()) {

			newOperands.add(operand.accept(this));
		}

		Iterator<String> iter = newOperands.iterator();

		int noOfOperands = arg0.getOperands().size();

		if (noOfOperands != 0) {

			StringBuffer buffer = noOfOperands > 1 ? new StringBuffer("(")
					: new StringBuffer();

			for (int i = 0; i < noOfOperands - 2; i++) {

				buffer.append("(");
			}

			buffer.append(iter.next());

			while (iter.hasNext()) {
				buffer.append(" ⊔ " + iter.next() + ")");
			}

			return buffer.toString();

		}

		return "⊥";
	}

	public String visit(AtomicAxiom at_axiom) {
		return at_axiom.getOWLAxiom().accept(this);
	}

	public String visit(ConjunctiveAxiom conj_axiom) {

		StringBuffer str_bf = new StringBuffer();

		str_bf.append("(");
		str_bf.append(conj_axiom.getLeftAxiom().accept(this));
		str_bf.append(" ");
		str_bf.append("∧");
		str_bf.append(" ");
		str_bf.append(conj_axiom.getRightAxiom().accept(this));
		str_bf.append(")");

		return str_bf.toString();
	}

	public String visit(DisjunctiveAxiom disj_axiom) {

		StringBuffer str_bf = new StringBuffer();

		str_bf.append("(");
		str_bf.append(disj_axiom.getLeftAxiom().accept(this));
		str_bf.append(" ");
		str_bf.append("∨");
		str_bf.append(" ");
		str_bf.append(disj_axiom.getRightAxiom().accept(this));
		str_bf.append(")");

		return str_bf.toString();

	}

	public String visit(NegativeAxiom neg_axiom) {

		StringBuffer str_bf = new StringBuffer();

		str_bf.append("¬");
//		str_bf.append("(");
		str_bf.append(neg_axiom.getLeftAxiom().accept(this));
//		str_bf.append(")");

		return str_bf.toString();

	}

	public String visit(NextAxiom nex_axiom) {

		StringBuffer str_bf = new StringBuffer();

		str_bf.append("X");
		str_bf.append("(");
		str_bf.append(nex_axiom.getLeftAxiom().accept(this));
		str_bf.append(")");

		return str_bf.toString();

	}

	public String visit(UntilAxiom until_axiom) {

		StringBuffer str_bf = new StringBuffer();

		str_bf.append("(");
		str_bf.append(until_axiom.getLeftAxiom().accept(this));
		str_bf.append(" ");
		str_bf.append("U");
		str_bf.append(" ");
		str_bf.append(until_axiom.getRightAxiom().accept(this));
		str_bf.append(")");

		return str_bf.toString();

	}

}
