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


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.util.OWLAxiomTypeProcessor;
import org.semanticweb.owlapi.vocab.OWLXMLVocabulary;

import tud.inf.dl.alc.ltl.util.ConfigLoader;
import tud.inf.dl.alc.ltl.util.Probability;
import tud.inf.dl.alc.ltl.util.Entry.AxiomType;

/**
 * AxiomGenerator does the following tasks: 
 * 1. Generate Concept Assertion Axioms. 
 * 2. Generate Role Assertion Axioms. 
 * 3. Generate Negative Role Assertion Axioms. 
 * 4. Generate GCI's.
 * 
 * AxiomGenerator extends the following major class: a. ExprGenerator
 * 
 * @version
 * @author
 */

public class AxiomGenerator {

	private OWLDataFactory factory = null;

	/** Expression Generator */
	private ExprGenerator expr_gen = null;

	/** Individual Name set */
	private List<OWLNamedIndividual> individuals = null;

	private Probability<AxiomType> prob_dist = null;
	
	/**
	 * Constructor
	 * 
	 * @param factory
	 * @param prefix_iri
	 * @param size_Nc
	 *            : size of Concept Set.
	 * @param size_Nr
	 *            : size of Role Set.
	 * @param size_Ni
	 *            : size of Individuals Set.
	 */
	public AxiomGenerator(OWLDataFactory factory, List<OWLClass> concepts,
			List<OWLObjectProperty> roles, List<OWLNamedIndividual> individuals) {

		this.factory = factory;
		this.expr_gen = new ExprGenerator(factory, concepts, roles);

		this.individuals = individuals;
		this.prob_dist = new Probability<AxiomType>(ConfigLoader.axiom_table);

		Collections.shuffle(individuals);

	}

	public AxiomGenerator(OWLDataFactory factory) {

		this.factory = factory;

	}


	/**
	 * produce a concept assertion axiom
	 * 
	 * @param expr
	 *            a Complex Class Expression
	 * @return
	 */
	public OWLClassAssertionAxiom conceptAssertion(OWLClassExpression expr, String index) {

		OWLNamedIndividual individual = this.individuals.get(0);
		Collections.shuffle(individuals);

		
		OWLLiteral ax_lit = this.factory.getOWLLiteral(index);

		OWLAnnotation annotation = this.factory.getOWLAnnotation(
        		this.factory.getOWLAnnotationProperty(OWLXMLVocabulary.CLASS_ASSERTION.getIRI()), ax_lit);
        Set<OWLAnnotation> anot_set = new HashSet<OWLAnnotation>();
        anot_set.add(annotation);
        
		OWLClassAssertionAxiom cls_assertion = this.factory
		.getOWLClassAssertionAxiom(expr, individual,anot_set);
		OWLAxiom ax = cls_assertion;
		
		return cls_assertion;

	}

	public OWLClassAssertionAxiom conceptAssertion(OWLClassExpression expr, Set<OWLNamedIndividual> individual_set,String index) {
		
		
		OWLLiteral ax_lit = this.factory.getOWLLiteral(index);

		OWLAnnotation annotation = this.factory.getOWLAnnotation(
        		this.factory.getOWLAnnotationProperty(OWLXMLVocabulary.CLASS_ASSERTION.getIRI()), ax_lit);
        Set<OWLAnnotation> anot_set = new HashSet<OWLAnnotation>();
        anot_set.add(annotation);
        
        OWLClassAssertionAxiom cls_assertion = null;
        Vector<OWLNamedIndividual> ind_set = new Vector<OWLNamedIndividual>(individual_set);
		
		cls_assertion = this.factory.getOWLClassAssertionAxiom(expr, ind_set.elementAt(0),anot_set);
		
		
		return cls_assertion;

	}

	/**
	 * produce role assertion
	 * 
	 * @param role
	 * @return
	 */
	public OWLObjectPropertyAssertionAxiom roleAssertion(OWLObjectProperty role, String index) {

		OWLNamedIndividual individual_1 = this.individuals.get(0);
		OWLNamedIndividual individual_2 = this.individuals.get(1);

		Collections.shuffle(individuals);


		OWLLiteral ax_lit = this.factory.getOWLLiteral(index);

		OWLAnnotation annotation = this.factory.getOWLAnnotation(
        		this.factory.getOWLAnnotationProperty(OWLXMLVocabulary.OBJECT_PROPERTY_ASSERTION.getIRI()), ax_lit);
        Set<OWLAnnotation> anot_set = new HashSet<OWLAnnotation>();
        anot_set.add(annotation);
        
		OWLObjectPropertyAssertionAxiom role_assertion_axiom = this.factory
		.getOWLObjectPropertyAssertionAxiom(role, individual_1,
				individual_2,anot_set);

		return role_assertion_axiom;

	}

	public OWLObjectPropertyAssertionAxiom roleAssertion(Set<OWLObjectProperty> role_set, Set<OWLNamedIndividual> ind_set, String index) {

		OWLLiteral ax_lit = this.factory.getOWLLiteral(index);

		OWLAnnotation annotation = this.factory.getOWLAnnotation(
        		this.factory.getOWLAnnotationProperty(OWLXMLVocabulary.OBJECT_PROPERTY_ASSERTION.getIRI()), ax_lit);
        Set<OWLAnnotation> anot_set = new HashSet<OWLAnnotation>();
        anot_set.add(annotation);
        Vector<OWLNamedIndividual> ind_list = new Vector<OWLNamedIndividual>(ind_set);
        
        OWLObjectPropertyAssertionAxiom role_assertion_axiom = null;

        for(OWLObjectProperty role: role_set) {
			role_assertion_axiom = this.factory.
			getOWLObjectPropertyAssertionAxiom(role, ind_list.elementAt(0),
					ind_list.elementAt(1),anot_set);
        }
		return role_assertion_axiom;

	}

	/**
	 * produce a negated role assertion
	 * 
	 * @param role
	 * @return
	 */
	public OWLNegativeObjectPropertyAssertionAxiom negRoleAssertion(
			OWLObjectProperty role, String index) {

		OWLNamedIndividual individual_1 = this.individuals.get(0);
		OWLNamedIndividual individual_2 = this.individuals.get(1);

		Collections.shuffle(individuals);


		OWLLiteral ax_lit = this.factory.getOWLLiteral(index);

		OWLAnnotation annotation = this.factory.getOWLAnnotation(
        		this.factory.getOWLAnnotationProperty(OWLXMLVocabulary.NEGATIVE_OBJECT_PROPERTY_ASSERTION.getIRI()), ax_lit);
        Set<OWLAnnotation> anot_set = new HashSet<OWLAnnotation>();
        anot_set.add(annotation);
        
		OWLNegativeObjectPropertyAssertionAxiom neg_role_assertion_axiom = this.factory
		.getOWLNegativeObjectPropertyAssertionAxiom(role, individual_1,
				individual_2,anot_set);

		return neg_role_assertion_axiom;
	}

	public OWLNegativeObjectPropertyAssertionAxiom negroleAssertion(Set<OWLObjectProperty> role_set, Set<OWLNamedIndividual> ind_set, String index) {

		OWLLiteral ax_lit = this.factory.getOWLLiteral(index);

		OWLAnnotation annotation = this.factory.getOWLAnnotation(
        		this.factory.getOWLAnnotationProperty(OWLXMLVocabulary.OBJECT_PROPERTY_ASSERTION.getIRI()), ax_lit);
        Set<OWLAnnotation> anot_set = new HashSet<OWLAnnotation>();
        anot_set.add(annotation);
        Vector<OWLNamedIndividual> ind_list = new Vector<OWLNamedIndividual>(ind_set);
        
        OWLNegativeObjectPropertyAssertionAxiom role_assertion_axiom = null;

        for(OWLObjectProperty role: role_set) {
			role_assertion_axiom = this.factory.
			getOWLNegativeObjectPropertyAssertionAxiom(role, ind_list.elementAt(0),
					ind_list.elementAt(1),anot_set);
        }
        
		return role_assertion_axiom;

	}

	/**
	 * Generate and store a GCI assertion ( ⊑ )
	 * 
	 * @param expr_1
	 * @param expr_2
	 */
	private OWLSubClassOfAxiom getGCIAssertion(OWLClassExpression expr_1,
			OWLClassExpression expr_2, String index) {

		
		OWLLiteral ax_lit = this.factory.getOWLLiteral(index);
        OWLAnnotation sub_anno = this.factory.getOWLAnnotation(
        		this.factory.getOWLAnnotationProperty(OWLXMLVocabulary.SUB_CLASS_OF.getIRI()), ax_lit);
        
        Set<OWLAnnotation> anot_set = new HashSet<OWLAnnotation>();
        anot_set.add(sub_anno);
        
        OWLSubClassOfAxiom gci_axiom = this.factory.getOWLSubClassOfAxiom(expr_1, expr_2, anot_set);
        
		return gci_axiom;
	}


	public Vector<OWLAxiom> annotateAxiom(Vector<OWLAxiom> ax_list) {
		
		Vector<OWLAxiom> ann_axiom_list = new  Vector<OWLAxiom>();
		Integer ax_ann_index = 1;
		
		
		for(OWLAxiom ax: ax_list){
		
			
			if(ax.equals(org.semanticweb.owlapi.model.AxiomType.SUBCLASS_OF)) {
				OWLSubClassOfAxiom new_ax = (OWLSubClassOfAxiom) ax;
				ann_axiom_list.add(getGCIAssertion(new_ax.getSubClass(),new_ax.getSuperClass(),ax_ann_index.toString()));
				
				ax_ann_index++;
				
			} else if(ax.getAxiomType().equals(org.semanticweb.owlapi.model.AxiomType.CLASS_ASSERTION)) {
				OWLClassAssertionAxiom new_ax = (OWLClassAssertionAxiom) ax;
				ann_axiom_list.add(conceptAssertion(new_ax.getClassExpression(),new_ax.getIndividualsInSignature(),ax_ann_index.toString()));
				ax_ann_index++;
				
			} else if(ax.getAxiomType().equals(org.semanticweb.owlapi.model.AxiomType.OBJECT_PROPERTY_ASSERTION)) {
				
				OWLObjectPropertyAssertionAxiom new_ax = (OWLObjectPropertyAssertionAxiom)ax;
				ann_axiom_list.add(roleAssertion(new_ax.getObjectPropertiesInSignature(), new_ax.getIndividualsInSignature(), ax_ann_index.toString()));
				ax_ann_index++;		
			} else if(ax.getAxiomType().equals(org.semanticweb.owlapi.model.AxiomType.NEGATIVE_OBJECT_PROPERTY_ASSERTION)) {
				OWLNegativeObjectPropertyAssertionAxiom new_ax = (OWLNegativeObjectPropertyAssertionAxiom)ax;
				ann_axiom_list.add(roleAssertion(new_ax.getObjectPropertiesInSignature(), new_ax.getIndividualsInSignature(), ax_ann_index.toString()));
				ax_ann_index++;			
			} else {
//				System.err.println("Axiom type is not identified");
			}
		}
		
		return ann_axiom_list;
	}
	/**
	 * Recursive Type of procedure Randomly generate an assertion
	 * axiom of provided length.
	 * 
	 * @param length
	 *            Length of assertion axioms
	 */
	public OWLAxiom getAssertion(int length, String index) {

		OWLAxiom axiom = null;

		AxiomType axiom_type = this.prob_dist.getRandomElement();

		switch (axiom_type) {

		case CLASS_ASSERTION: {
			
			axiom = conceptAssertion(this.expr_gen.getConceptDescription(null,
					length, false),index);
			break;
		}

		case OBJECT_PROPERTY_ASSERTION: {

			axiom = roleAssertion(this.expr_gen.getRole(),index);
			break;
		}

		case NEGATIVE_OBJECT_PROPERTY_ASSERTION: {

			axiom = negRoleAssertion(this.expr_gen.getRole(),index);
			break;
		}

		case SUBCLASS_OF: {

//			length = length - 1;
			int length_1 = 0;
			int length_2 = 0;

			if (length > 0) {
				length_1 = partiton(length);
				length_2 = length - length_1;
			}
			
			axiom = getGCIAssertion(this.expr_gen.getConceptDescription(null,
					length_1, false), this.expr_gen
					.getConceptDescription(null, length_2, false),index);
			break;
		}

		}// end switch

		return axiom;
	}

	private int partiton(int length) {
		Random r = new Random();
		int rand = r.nextInt(length);
		
		return rand;
	}

	
	/**
	 * Recursive Type of procedure Randomly generate given number of assertion
	 * axioms of provided length.
	 * 
	 * @param size
	 *            Number of assertion axiom generated
	 * @param length
	 *            Length of assertion axioms
	 */
//	public OWLAxiom getAssertion(double length) {
//
//		this.axiom_list = new ArrayList<OWLAxiom>();
//
//		getAssertions(1, length);
//		return this.axiom_list.get(0);
//
//	}

//	public List<OWLAxiom> getAssertionAxioms() {
//
//		return this.axiom_list;
//
//	}

	// public static void main(String []args) {
	//
	// OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	// OWLDataFactory factory = manager.getOWLDataFactory();
	//
	// PrefixManager pm = new DefaultPrefixManager("#");
	//		
	// VocGenerator voc = new VocGenerator(factory, pm, 5, 5, 5);
	// AxiomGenerator ax_gen = new AxiomGenerator(factory,pm, voc.getConcepts(),
	// voc.getRoles(), voc.getIndividuals());
	//		
	// ax_gen.genAssertions(10, 4);
	//
	// PrettyVisitor print_visitor = new PrettyVisitor();
	//		
	// for(OWLAxiom ax: ax_gen.getAssertionAxioms()) {
	// System.out.println(ax.accept(print_visitor));
	// }
	//		
	//		
	// // for(OWLAxiom ax: ax_gen.getAssertionAxioms()) {
	// // System.out.println(ax);
	// //
	// // }
	// }

}
