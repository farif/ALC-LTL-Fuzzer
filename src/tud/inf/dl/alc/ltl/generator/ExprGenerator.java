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

package tud.inf.dl.alc.ltl.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import tud.inf.dl.alc.ltl.util.ConfigLoader;
import tud.inf.dl.alc.ltl.util.Probability;
import tud.inf.dl.alc.ltl.visitor.PrettyVisitor;

/**
 * EprGenerator generate ALC Concept Description: 
 * 1. Concepts, Concept Description 
 * 2. Conjunction, Disjunction 
 * 3. Existential Restriction, Value Restriction
 * 
 * @version
 * @author
 */

public class ExprGenerator {

	protected OWLDataFactory factory = null;

	/** Concept Name set */
	protected List<OWLClass> concepts = null;
	/** Role Name set */
	protected List<OWLObjectProperty> roles = null;

	protected Probability<ClassExpressionType> prob_dist = null;
	/**
	 * Constructor
	 * 
	 * @param factory
	 * @param prefix_iri
	 * @param concept_set
	 * @param role_set
	 */
	public ExprGenerator(OWLDataFactory factory, List<OWLClass> concepts,
			List<OWLObjectProperty> roles) {

		this.factory = factory;

		this.concepts = concepts;
		this.roles = roles;

		this.prob_dist = new Probability<ClassExpressionType>(ConfigLoader.expr_table);

		Collections.shuffle(concepts);
		Collections.shuffle(roles);
	}

	/**
	 * Complement ( ¬ )
	 * 
	 * @param expr
	 * @return
	 */
	public OWLObjectComplementOf complement(OWLClassExpression expr) {

		return factory.getOWLObjectComplementOf(expr);

	}

	/**
	 * Union ( ⊔ )
	 * 
	 * @param expr_1
	 * @param expr_2
	 * @return
	 */
	private OWLObjectUnionOf union(OWLClassExpression expr_1,
			OWLClassExpression expr_2) {
		if (expr_1 == expr_2) {
			// System.err.println(expr_1);
			expr_2 = getConcept();
		}
		return factory.getOWLObjectUnionOf(expr_1, expr_2);

	}

	/**
	 * Intersection ( ⊓ )
	 * 
	 * @param expr_1
	 * @param expr_2
	 * @return
	 */
	private OWLObjectIntersectionOf intersection(OWLClassExpression expr_1,
			OWLClassExpression expr_2) {
		if (expr_1 == expr_2) {
			// System.err.println(expr_1);
			expr_2 = getConcept();
		}
		return factory.getOWLObjectIntersectionOf(expr_1, expr_2);

	}

	/**
	 * Universal quantification over roles (∀)
	 * 
	 * @param role
	 * @param expr
	 * @return
	 */
	private OWLClassExpression value_restriction(
			OWLObjectPropertyExpression role, OWLClassExpression expr) {
		return factory.getOWLObjectAllValuesFrom(role, expr);

	}

	/**
	 * Existential quantification over roles (∃)
	 * 
	 * @param role
	 * @param expr
	 * @return
	 */
	private OWLClassExpression ext_restriction(
			OWLObjectPropertyExpression role, OWLClassExpression expr) {

		return factory.getOWLObjectSomeValuesFrom(role, expr);

	}

	/**
	 * Shuffle and return a Random concept
	 * 
	 * @return
	 */
	public OWLClassExpression getConcept() {

		OWLClass concept = this.concepts.get(0);
		Collections.shuffle(concepts);
		return concept;

	}

	/**
	 * Shuffle and return a random role
	 * 
	 * @return
	 */
	public OWLObjectProperty getRole() {

		OWLObjectProperty role = this.roles.get(0);
		Collections.shuffle(roles);
		return role;
	}

	/**
	 * Generate Concept Description of given length
	 * 
	 * @param conceptExpression
	 * @param length
	 *            concept description length based on number of constructs
	 *            applied
	 * @return
	 */
	public OWLClassExpression getConceptDescription(
			OWLClassExpression conceptExpression, int length, boolean negation_op) {

		// Random Select Type
		ClassExpressionType op = this.prob_dist.getRandomElement();
		
		// size - 1
		if (length > 0) {

			switch (op) {

			case OBJECT_COMPLEMENT_OF: {
				
				if(negation_op) {
					return getConceptDescription(conceptExpression, length, true);
				}
				
				return complement(getConceptDescription(conceptExpression,
						length - 1,true));

			}

			case OBJECT_INTERSECTION_OF: {
				
				length = length - 1;

				int length_1 = 0;
				int length_2 = 0;

				if (length > 0) {
					length_1 = partiton(length);
					length_2 = length - length_1;
				}

				return intersection(getConceptDescription(conceptExpression,
						length_1, false), getConceptDescription(
						conceptExpression, length_2,false));

			}

			case OBJECT_UNION_OF: {
				
				length = length - 1;

				int length_1 = 0;
				int length_2 = 0;

				if (length > 0) {
					length_1 = partiton(length);
					length_2 = length - length_1;
				}

				return union(getConceptDescription(conceptExpression, length_1,false), 
						getConceptDescription(conceptExpression, length_2,false));
			}

			case OBJECT_SOME_VALUES_FROM: {

				return ext_restriction(getRole(), getConceptDescription(
						conceptExpression, length - 1,false));
			}

			case OBJECT_ALL_VALUES_FROM: {
				
				return value_restriction(getRole(), getConceptDescription(
						conceptExpression, length - 1,false));

			}

			} // End Switch

		} else {

			if (conceptExpression == null) {
				conceptExpression = getConcept();
			}
		}

		return conceptExpression;

	}

	private int partiton(int length) {
		Random r = new Random();
		int rand = r.nextInt(length);
		
		return rand;
	}

	/**
	 * ALC concept description Syntax. For any generated Concept Description "C"
	 * NEGATION = ¬ C CONJUNCTION = C ⊔ D DISJUNCTION = C ⊓ D EXISTRESTRICTION =
	 * (∀r).C VALUERESTRICTION = (∃).C
	 * 
	 * @author
	 * 
	 */

	 public static void main(String [] args) {
	    	 
	 OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLDataFactory factory = manager.getOWLDataFactory();

		PrefixManager pm = new DefaultPrefixManager("#");

		VocGenerator voc = new VocGenerator(factory, pm, 5, 5, 5);

		ExprGenerator exp_gen = new ExprGenerator(factory, voc.getConcepts(),
				voc.getRoles());

		PrettyVisitor print_visitor = new PrettyVisitor();
		List<OWLClassExpression> expr = new ArrayList<OWLClassExpression>();

		for (int i = 0; i <= 5; i++) {
			expr.add(exp_gen.getConceptDescription(null, 2, false));

		}

		for (OWLClassExpression ex : expr) {
			System.out.print(ex.accept(print_visitor));
			System.out.print(" := " + ex + "\n");
		}
	 }
}
