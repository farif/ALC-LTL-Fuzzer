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

import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

import tud.inf.dl.alc.ltl.axioms.AtomicAxiom;
import tud.inf.dl.alc.ltl.axioms.ConjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.DisjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.axioms.NegativeAxiom;
import tud.inf.dl.alc.ltl.axioms.NextAxiom;
import tud.inf.dl.alc.ltl.axioms.UntilAxiom;
import tud.inf.dl.alc.ltl.util.ConfigLoader;
import tud.inf.dl.alc.ltl.util.Probability;
import tud.inf.dl.alc.ltl.util.Entry.FormulaType;
import tud.inf.dl.alc.ltl.visitor.PrettyVisitor;

/**
 * FormulaGenerator generate ALC-LTL Formulas: 
 * 1. AtomicFormulas 
 * 2. Negated, Conjunctive, Disjunctive Formulas
 * 
 * @version
 * @author
 */

public class FormulaGenerator {

	protected AxiomGenerator axiom_gen = null;

	protected Vector<OWLAxiom> axiom_list = null;
	protected static Integer fm_index = 0;

	protected Probability<FormulaType> prob_dist = null;
	private boolean genetrate_axiom_flag;
	private Random rand;
	
	/**
	 * Constructor
	 * 
	 * @param factory
	 * @param prefix_iri
	 * @param concept_set
	 * @param role_set
	 */
	public FormulaGenerator(OWLDataFactory factory, List<OWLClass> concepts,
			List<OWLObjectProperty> roles, List<OWLNamedIndividual> individuals) {

		axiom_gen = new AxiomGenerator(factory, concepts, roles, individuals);
		axiom_list = new Vector<OWLAxiom>();
		this.prob_dist = new Probability<FormulaType>(ConfigLoader.formula_table);
		this.genetrate_axiom_flag = true;
	}
	
	/**
	 * 
	 * @param ontology
	 */
	public FormulaGenerator(OWLDataFactory factory, OWLOntology ontology) {
		
		axiom_gen = new AxiomGenerator(factory);
		this.axiom_list = new Vector<OWLAxiom>(ontology.getLogicalAxioms());
		this.axiom_list = this.axiom_gen.annotateAxiom(this.axiom_list);
		
		PrettyVisitor visitor = new PrettyVisitor();
		
		int index = 0;
//		
//		for (OWLAxiom ax : this.axiom_list) {
//			System.out.println(++index + ": " + ax.accept(visitor));
//		}
//
//		
		System.out.println("------------------------------------------" + ontology.getLogicalAxiomCount());
		this.prob_dist = new Probability<FormulaType>(ConfigLoader.formula_table);
		
		this.rand = new Random();
		this.genetrate_axiom_flag = false;

	}


//	private Vector<OWLAxiom> load_axioms(OWLOntology ontology){
//		Vector<OWLAxiom> axiom_vector = new Vector<OWLAxiom>();
//		
//		ontology.get
//		return axiom_vector;
//	}
	
	public Vector<OWLAxiom> getAxioms() {
		return this.axiom_list;
	}

	public LTLOWLAxiom getAtomicProposition(int length) {

		OWLAxiom ax = null;
		
		if(this.genetrate_axiom_flag) {
			fm_index++;
			ax = this.axiom_gen.getAssertion(length, fm_index.toString());
			axiom_list.add(ax);
			
		} else {

			int min = 0;
			int max = this.axiom_list.size();
						
			int ax_index = rand.nextInt(max - min) + min;
			ax = axiom_list.get(ax_index);
			
		}
		
		LTLOWLAxiom ltl_ax = new AtomicAxiom(ax);
		
		return ltl_ax;
	}

	/**
	 * Complement (˜)
	 * 
	 * @param expr
	 * @return
	 */
	public LTLOWLAxiom negation(LTLOWLAxiom expr, boolean flag) {

		LTLOWLAxiom ltl_ax = new NegativeAxiom(expr);
		if (expr != null) {
			if (expr.getOWLAxiom() != null) {
				if (expr.getOWLAxiom().getAxiomType() == AxiomType.NEGATIVE_OBJECT_PROPERTY_ASSERTION) {
					ltl_ax = expr;
					flag = true;
				}
			}
		}

		return ltl_ax;

	}

	/**
	 * Complement (X)
	 * 
	 * @param expr
	 * @return
	 */
	public LTLOWLAxiom next(LTLOWLAxiom expr) {

		LTLOWLAxiom ltl_ax = new NextAxiom(expr);
		return ltl_ax;

	}

	/**
	 * conjunction (∧)
	 * 
	 * @param expr
	 * @return
	 */
	public LTLOWLAxiom conjunction(LTLOWLAxiom expr_1, LTLOWLAxiom expr_2) {

		LTLOWLAxiom ltl_ax = new ConjunctiveAxiom(expr_1, expr_2);
		return ltl_ax;

	}

	/**
	 * disjunction (∨)
	 * 
	 * @param expr
	 * @return
	 */
	public LTLOWLAxiom disjunction(LTLOWLAxiom expr_1, LTLOWLAxiom expr_2) {

		LTLOWLAxiom ltl_ax = new DisjunctiveAxiom(expr_1, expr_2);
		return ltl_ax;

	}

	/**
	 * Until (U)
	 * 
	 * @param expr
	 * @return
	 */
	public LTLOWLAxiom until(LTLOWLAxiom expr_1, LTLOWLAxiom expr_2) {

		LTLOWLAxiom ltl_ax = new UntilAxiom(expr_1, expr_2);
		return ltl_ax;

	}

	/**
	 * ALC concept description Syntax. For any generated Concept Description "C"
	 * 
	 * ATOMIC PROPSITION(AP): i. ConceptAssionAxiom ii.RoleAssertionAxiom
	 * iii.NegatedRoleAssertionAxiom
	 * 
	 * NEGATION = ˜ AP CONJUNCTION = AP ∧ AP DISJUNCTION = AP ∨ AP NEXT = X AP
	 * UNTIL = AP U AP
	 * 
	 * @author
	 * 
	 */
	public LTLOWLAxiom getFormula(LTLOWLAxiom ltl_exp, int concept_length,
			int fm_length, boolean negation_op) {

		// Random Select Type
		FormulaType op = this.prob_dist.getRandomElement();

		if (fm_length > 0) {

			switch (op) {

				case NEGATION: {
	
					if (negation_op) {
						return getFormula(ltl_exp, concept_length, fm_length, true);
					}
	
					boolean flag = false;
	
					LTLOWLAxiom ax_1 = negation(getFormula(ltl_exp, concept_length,
							fm_length - 1, true), flag);
					if (flag == true) {
						fm_length++;
					}
					
					return ax_1;
	
				}
	
				case NEXT: {
	
					return next(getFormula(ltl_exp, concept_length, fm_length - 1,
							false));
	
				}
	
				case CONJECTION: {
	
					fm_length = fm_length - 1;
	
					int length_1 = 0;
					int length_2 = 0;
	
					if (fm_length > 0) {
						length_1 = partiton(fm_length);
						length_2 = fm_length - length_1;
					}
	
					return conjunction(getFormula(ltl_exp, concept_length,
							length_1, false), getFormula(ltl_exp, concept_length,
							length_2, false));
	
				}
	
				case DISJUNCTION: {
	
					fm_length = fm_length - 1;
	
					int length_1 = 0;
					int length_2 = 0;
	
					if (fm_length > 0) {
						length_1 = partiton(fm_length);
						length_2 = fm_length - length_1;
					}
	
					return disjunction(getFormula(ltl_exp, concept_length,
							length_1, false), getFormula(ltl_exp, concept_length,
							length_2, false));
	
				}
	
				case UNTIL: {
	
					fm_length = fm_length - 1;
	
					int length_1 = 0;
					int length_2 = 0;
	
					if (fm_length > 0) {
						length_1 = partiton(fm_length);
						length_2 = fm_length - length_1;
					}
	
					return until(getFormula(ltl_exp, concept_length, length_1,
							false), getFormula(ltl_exp, concept_length, length_2,
							false));
	
				}

			} // End Switch

		} else {
			if (ltl_exp == null) {
				ltl_exp = getAtomicProposition(concept_length);
			}
		}
		return ltl_exp;

	}

	private int partiton(int length) {
		Random r = new Random();
		int rand = r.nextInt(length);

		return rand;
	}

	// public static void main(String [] args) {
	//    	 
	// OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	// OWLDataFactory factory = manager.getOWLDataFactory();
	//
	// PrefixManager pm = new DefaultPrefixManager("#");
	//		
	// VocGenerator voc = new VocGenerator(factory, pm, 5, 5, 5);
	//		
	// ExprGenerator exp_gen = new ExprGenerator(factory, pm, voc.getConcepts(),
	// voc.getRoles());
	//
	// PrettyVisitor print_visitor = new PrettyVisitor();
	// List<OWLClassExpression> expr = new ArrayList<OWLClassExpression>();
	//    	
	// for (int i = 0; i <= 5; i++) {
	// expr.add(exp_gen.getConceptDescription(null, 2));
	//    	
	// }
	//    	
	// for(OWLClassExpression ex: expr) {
	// System.out.print(ex.accept(print_visitor));
	// System.out.print(" := "+ ex + "\n");
	// }
	// }
}
