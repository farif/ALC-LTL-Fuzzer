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

package tud.inf.dl.alc.ltl.render;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataComplementOf;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataIntersectionOf;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataOneOf;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDataUnionOf;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFacetRestriction;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasSelf;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectInverseOf;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.SWRLBuiltInAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLDataPropertyAtom;
import org.semanticweb.owlapi.model.SWRLDataRangeAtom;
import org.semanticweb.owlapi.model.SWRLDifferentIndividualsAtom;
import org.semanticweb.owlapi.model.SWRLIndividualArgument;
import org.semanticweb.owlapi.model.SWRLLiteralArgument;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLSameIndividualAtom;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.vocab.OWLXMLVocabulary;

import tud.inf.dl.alc.ltl.axioms.AtomicAxiom;
import tud.inf.dl.alc.ltl.axioms.ConjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.DisjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.axioms.NegativeAxiom;
import tud.inf.dl.alc.ltl.axioms.NextAxiom;
import tud.inf.dl.alc.ltl.axioms.UntilAxiom;
import tud.inf.dl.alc.ltl.io.LTLOWLXMLWriter;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntology;
import tud.inf.dl.alc.ltl.util.Entry.FormulaType;
import tud.inf.dl.alc.ltl.visitor.LTLOWLOntologyVisitor;
import tud.inf.dl.alc.ltl.visitor.LTLXMLAxiomVisitior;

/**
 * LTLOWLObjectRenderer does the following task(s):
 * a. Visit the elements of OWL Ontology
 * b. Write output in XML Format. 
 * 
 * @version
 * @author
 */

public class LTLOWLObjectRenderer implements OWLObjectVisitor,LTLXMLAxiomVisitior,
	 LTLOWLOntologyVisitor {

	protected LTLOWLXMLWriter writer;

	/**
	 * Constructor
	 * 
	 * @param writer
	 */
	public LTLOWLObjectRenderer(LTLOWLXMLWriter writer) {
		this.writer = writer;
	}

	/**
	 * Writing Annonation Axioms in Ontology
	 * 
	 * @param axiom
	 */
	private void writeAnnotations(OWLAxiom axiom) {
		for (OWLAnnotation anno : axiom.getAnnotations()) {
			anno.accept(this);
		}
	}

	/**
	 * LTLOWLOntology visitor
	 * 
	 * @param LTLOWLOntology
	 */
	public void visit(LTLOWLOntology ltl_ontology) {

//		for (OWLImportsDeclaration decl : ltl_ontology.getOWLOntology().getImportsDeclarations()) {
//			this.writer.writeStartElement(OWLXMLVocabulary.IMPORT);
//			this.writer.writeTextContent(decl.getURI().toString());
//			this.writer.writeEndElement();
//		}
		
		for (OWLAnnotation annotation : ltl_ontology.getOWLOntology().getAnnotations()) {
			annotation.accept(this);
		}
		
		ltl_ontology.getOWLOntology().accept(this);
		
		for (LTLOWLAxiom ax : ltl_ontology.getLTLAxioms()) {
			this.writer.writeStartDocument();
			ax.accept(this);
			this.writer.writeEndDocument();


		}
	}

//	/**
//	 * Default OWLOntology visitor
//	 * 
//	 * @param OWLOntology
//	 */
//	public void visit(OWLOntology ontology) {
//		for (OWLImportsDeclaration decl : ontology.getImportsDeclarations()) {
//			writer.writeStartElement(OWLXMLVocabulary.IMPORT);
//			writer.writeTextContent(decl.getURI().toString());
//			writer.writeEndElement();
//		}
//		for (OWLAnnotation annotation : ontology.getAnnotations()) {
//			annotation.accept(this);
//		}
//
//		List<OWLAxiom> axioms = new ArrayList<OWLAxiom>(ontology.getAxioms());
//		Collections.sort(axioms);
//		for (OWLAxiom ax : axioms) {
//			ax.accept(this);
//		}
//	}
//
//	public void visit(IRI iri) {
//		writer.writeIRIElement(iri);
//	}
//
//	public void visit(OWLAnonymousIndividual individual) {
//		writer.writeStartElement(OWLXMLVocabulary.ANONYMOUS_INDIVIDUAL);
//		writer.writeNodeIDAttribute(individual.getID());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLAsymmetricObjectPropertyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.ASYMMETRIC_OBJECT_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLClassAssertionAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.CLASS_ASSERTION);
//		writeAnnotations(axiom);
//		axiom.getClassExpression().accept(this);
//		axiom.getIndividual().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataPropertyAssertionAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_PROPERTY_ASSERTION);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getSubject().accept(this);
//		axiom.getObject().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataPropertyDomainAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_PROPERTY_DOMAIN);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getDomain().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataPropertyRangeAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_PROPERTY_RANGE);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getRange().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLSubDataPropertyOfAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.SUB_DATA_PROPERTY_OF);
//		writeAnnotations(axiom);
//		axiom.getSubProperty().accept(this);
//		axiom.getSuperProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDeclarationAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DECLARATION);
//		writeAnnotations(axiom);
//		axiom.getEntity().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDifferentIndividualsAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DIFFERENT_INDIVIDUALS);
//		writeAnnotations(axiom);
//		render(axiom.getIndividuals());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDisjointClassesAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DISJOINT_CLASSES);
//		writeAnnotations(axiom);
//		render(axiom.getClassExpressions());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDisjointDataPropertiesAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DISJOINT_DATA_PROPERTIES);
//		writeAnnotations(axiom);
//		render(axiom.getProperties());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDisjointObjectPropertiesAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DISJOINT_OBJECT_PROPERTIES);
//		writeAnnotations(axiom);
//		render(axiom.getProperties());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDisjointUnionAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DISJOINT_UNION);
//		writeAnnotations(axiom);
//		axiom.getOWLClass().accept(this);
//		render(axiom.getClassExpressions());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLAnnotationAssertionAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.ANNOTATION_ASSERTION);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getSubject().accept(this);
//		axiom.getValue().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLEquivalentClassesAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.EQUIVALENT_CLASSES);
//		writeAnnotations(axiom);
//		render(axiom.getClassExpressions());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLEquivalentDataPropertiesAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.EQUIVALENT_DATA_PROPERTIES);
//		writeAnnotations(axiom);
//		render(axiom.getProperties());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLEquivalentObjectPropertiesAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.EQUIVALENT_OBJECT_PROPERTIES);
//		writeAnnotations(axiom);
//		render(axiom.getProperties());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLFunctionalDataPropertyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.FUNCTIONAL_DATA_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLFunctionalObjectPropertyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.FUNCTIONAL_OBJECT_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLInverseFunctionalObjectPropertyAxiom axiom) {
//		writer
//				.writeStartElement(OWLXMLVocabulary.INVERSE_FUNCTIONAL_OBJECT_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLInverseObjectPropertiesAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.INVERSE_OBJECT_PROPERTIES);
//		writeAnnotations(axiom);
//		axiom.getFirstProperty().accept(this);
//		axiom.getSecondProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLIrreflexiveObjectPropertyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.IRREFLEXIVE_OBJECT_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLNegativeDataPropertyAssertionAxiom axiom) {
//		writer
//				.writeStartElement(OWLXMLVocabulary.NEGATIVE_DATA_PROPERTY_ASSERTION);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getSubject().accept(this);
//		axiom.getObject().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLNegativeObjectPropertyAssertionAxiom axiom) {
//		writer
//				.writeStartElement(OWLXMLVocabulary.NEGATIVE_OBJECT_PROPERTY_ASSERTION);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getSubject().accept(this);
//		axiom.getObject().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectPropertyAssertionAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_PROPERTY_ASSERTION);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getSubject().accept(this);
//		axiom.getObject().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLSubPropertyChainOfAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.SUB_OBJECT_PROPERTY_OF);
//		writeAnnotations(axiom);
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_PROPERTY_CHAIN);
//		for (OWLObjectPropertyExpression prop : axiom.getPropertyChain()) {
//			prop.accept(this);
//		}
//		writer.writeEndElement();
//		axiom.getSuperProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectPropertyDomainAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_PROPERTY_DOMAIN);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getDomain().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectPropertyRangeAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_PROPERTY_RANGE);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		axiom.getRange().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLSubObjectPropertyOfAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.SUB_OBJECT_PROPERTY_OF);
//		writeAnnotations(axiom);
//		axiom.getSubProperty().accept(this);
//		axiom.getSuperProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLReflexiveObjectPropertyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.REFLEXIVE_OBJECT_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLSameIndividualAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.SAME_INDIVIDUAL);
//		writeAnnotations(axiom);
//		render(axiom.getIndividuals());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLSubClassOfAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.SUB_CLASS_OF);
//		writeAnnotations(axiom);
//		axiom.getSubClass().accept(this);
//		axiom.getSuperClass().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLSymmetricObjectPropertyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.SYMMETRIC_OBJECT_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLTransitiveObjectPropertyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.TRANSITIVE_OBJECT_PROPERTY);
//		writeAnnotations(axiom);
//		axiom.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLClass desc) {
//		writer.writeStartElement(OWLXMLVocabulary.CLASS);
//		writer.writeIRIAttribute(desc.getIRI());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataAllValuesFrom desc) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_ALL_VALUES_FROM);
//		desc.getProperty().accept(this);
//		desc.getFiller().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataExactCardinality desc) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_EXACT_CARDINALITY);
//		writer.writeCardinalityAttribute(desc.getCardinality());
//		desc.getProperty().accept(this);
//		if (desc.isQualified()) {
//			desc.getFiller().accept(this);
//		}
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataMaxCardinality desc) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_MAX_CARDINALITY);
//		writer.writeCardinalityAttribute(desc.getCardinality());
//		desc.getProperty().accept(this);
//		if (desc.isQualified()) {
//			desc.getFiller().accept(this);
//		}
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataMinCardinality desc) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_MIN_CARDINALITY);
//		writer.writeCardinalityAttribute(desc.getCardinality());
//		desc.getProperty().accept(this);
//		if (desc.isQualified()) {
//			desc.getFiller().accept(this);
//		}
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataSomeValuesFrom desc) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_SOME_VALUES_FROM);
//		desc.getProperty().accept(this);
//		desc.getFiller().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataHasValue desc) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_HAS_VALUE);
//		desc.getProperty().accept(this);
//		desc.getValue().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectAllValuesFrom desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_ALL_VALUES_FROM);
//		desc.getProperty().accept(this);
//		desc.getFiller().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectComplementOf desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_COMPLEMENT_OF);
//		desc.getOperand().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectExactCardinality desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_EXACT_CARDINALITY);
//		writer.writeCardinalityAttribute(desc.getCardinality());
//		desc.getProperty().accept(this);
//		if (desc.isQualified()) {
//			desc.getFiller().accept(this);
//		}
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectIntersectionOf desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_INTERSECTION_OF);
//		render(desc.getOperands());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectMaxCardinality desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_MAX_CARDINALITY);
//		writer.writeCardinalityAttribute(desc.getCardinality());
//		desc.getProperty().accept(this);
//		if (desc.isQualified()) {
//			desc.getFiller().accept(this);
//		}
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectMinCardinality desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_MIN_CARDINALITY);
//		writer.writeCardinalityAttribute(desc.getCardinality());
//		desc.getProperty().accept(this);
//		if (desc.isQualified()) {
//			desc.getFiller().accept(this);
//		}
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectOneOf desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_ONE_OF);
//		render(desc.getIndividuals());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectHasSelf desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_HAS_SELF);
//		desc.getProperty().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectSomeValuesFrom desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_SOME_VALUES_FROM);
//		desc.getProperty().accept(this);
//		desc.getFiller().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectUnionOf desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_UNION_OF);
//		render(desc.getOperands());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectHasValue desc) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_HAS_VALUE);
//		desc.getProperty().accept(this);
//		desc.getValue().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataComplementOf node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_COMPLEMENT_OF);
//		node.getDataRange().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataOneOf node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_ONE_OF);
//		render(node.getValues());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDatatype node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATATYPE);
//		writer.writeIRIAttribute(node.getIRI());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDatatypeRestriction node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATATYPE_RESTRICTION);
//		node.getDatatype().accept(this);
//		render(node.getFacetRestrictions());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLFacetRestriction node) {
//		writer.writeStartElement(OWLXMLVocabulary.FACET_RESTRICTION);
//		writer.writeFacetAttribute(node.getFacet());
//		node.getFacetValue().accept(this);
//		writer.writeEndElement();
//	}
//
	public void visit(OWLLiteral node) {
		writer.writeStartElement(OWLXMLVocabulary.LITERAL);
		if (node.hasLang()) {
			writer.writeLangAttribute(node.getLang());
		}
//		writer.writeDatatypeAttribute(node.getDatatype());
		writer.writeTextContent(node.getLiteral());
		writer.writeEndElement();
	}
//
//	public void visit(OWLDataProperty property) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_PROPERTY);
//		writer.writeIRIAttribute(property.getIRI());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectProperty property) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_PROPERTY);
//		writer.writeIRIAttribute(property.getIRI());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLObjectInverseOf property) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_INVERSE_OF);
//		property.getInverse().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLNamedIndividual individual) {
//		writer.writeStartElement(OWLXMLVocabulary.NAMED_INDIVIDUAL);
//		writer.writeIRIAttribute(individual.getIRI());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLHasKeyAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.HAS_KEY);
//		writeAnnotations(axiom);
//		axiom.getClassExpression().accept(this);
//		render(axiom.getObjectPropertyExpressions());
//		render(axiom.getDataPropertyExpressions());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataIntersectionOf node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_INTERSECTION_OF);
//		render(node.getOperands());
//		writer.writeEndElement();
//	}
//
//	public void visit(OWLDataUnionOf node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_UNION_OF);
//		render(node.getOperands());
//		writer.writeEndElement();
//	}

	public void visit(OWLAnnotationProperty property) {
		writer.writeStartElement(OWLXMLVocabulary.ANNOTATION_PROPERTY);
		writer.writeIRIAttribute(property.getIRI());
		writer.writeEndElement();
	}

	public void visit(OWLAnnotation annotation) {
//		writer.writeStartElement(OWLXMLVocabulary.ANNOTATION);
		writer.writeAxiomStart();
		for (OWLAnnotation anno : annotation.getAnnotations()) {
			anno.accept(this);
		}
//		annotation.getProperty().accept(this);
		annotation.getValue().accept(this);
//		writer.writeEndElement();
		writer.writeAxiomEnd();
	}

	public void visit(OWLAnnotationPropertyDomainAxiom axiom) {
		writer.writeStartElement(OWLXMLVocabulary.ANNOTATION_PROPERTY_DOMAIN);
		axiom.getProperty().accept(this);
		axiom.getDomain().accept(this);
		writer.writeEndElement();
	}

	public void visit(OWLAnnotationPropertyRangeAxiom axiom) {
		writer.writeStartElement(OWLXMLVocabulary.ANNOTATION_PROPERTY_RANGE);
		axiom.getProperty().accept(this);
		axiom.getRange().accept(this);
		writer.writeEndElement();
	}

	public void visit(OWLSubAnnotationPropertyOfAxiom axiom) {
		writer.writeStartElement(OWLXMLVocabulary.SUB_ANNOTATION_PROPERTY_OF);
		axiom.getSubProperty().accept(this);
		axiom.getSuperProperty().accept(this);
		writer.writeEndElement();
	}

//	public void visit(OWLDatatypeDefinitionAxiom axiom) {
//		writer.writeStartElement(OWLXMLVocabulary.DATATYPE_DEFINITION);
//		axiom.getDatatype().accept(this);
//		axiom.getDataRange().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLRule rule) {
//		writer.writeStartElement(OWLXMLVocabulary.DL_SAFE_RULE);
//		writeAnnotations(rule);
//		writer.writeStartElement(OWLXMLVocabulary.BODY);
//		for (SWRLAtom atom : rule.getBody()) {
//			atom.accept(this);
//		}
//		writer.writeEndElement();
//		writer.writeStartElement(OWLXMLVocabulary.HEAD);
//		for (SWRLAtom atom : rule.getHead()) {
//			atom.accept(this);
//		}
//		writer.writeEndElement();
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLClassAtom node) {
//		writer.writeStartElement(OWLXMLVocabulary.CLASS_ATOM);
//		node.getPredicate().accept(this);
//		node.getArgument().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLDataRangeAtom node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_RANGE_ATOM);
//		node.getPredicate().accept(this);
//		node.getArgument().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLObjectPropertyAtom node) {
//		writer.writeStartElement(OWLXMLVocabulary.OBJECT_PROPERTY_ATOM);
//		node.getPredicate().accept(this);
//		node.getFirstArgument().accept(this);
//		node.getSecondArgument().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLDataPropertyAtom node) {
//		writer.writeStartElement(OWLXMLVocabulary.DATA_PROPERTY_ATOM);
//		node.getPredicate().accept(this);
//		node.getFirstArgument().accept(this);
//		node.getSecondArgument().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLBuiltInAtom node) {
//		writer.writeStartElement(OWLXMLVocabulary.BUILT_IN_ATOM);
//		writer.writeIRIAttribute(node.getPredicate());
//		for (SWRLDArgument arg : node.getArguments()) {
//			arg.accept(this);
//		}
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLVariable node) {
//		writer.writeStartElement(OWLXMLVocabulary.VARIABLE);
//		writer.writeIRIAttribute(node.getIRI());
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLIndividualArgument node) {
//		node.getIndividual().accept(this);
//	}
//
//	public void visit(SWRLLiteralArgument node) {
//		node.getLiteral().accept(this);
//	}
//
//	public void visit(SWRLDifferentIndividualsAtom node) {
//		writer.writeStartElement(OWLXMLVocabulary.DIFFERENT_INDIVIDUALS_ATOM);
//		node.getFirstArgument().accept(this);
//		node.getSecondArgument().accept(this);
//		writer.writeEndElement();
//	}
//
//	public void visit(SWRLSameIndividualAtom node) {
//		writer.writeStartElement(OWLXMLVocabulary.SAME_INDIVIDUAL_ATOM);
//		node.getFirstArgument().accept(this);
//		node.getSecondArgument().accept(this);
//		writer.writeEndElement();
//	}
//
//	private void render(Set<? extends OWLObject> objects) {
//		for (OWLObject obj : objects) {
//			obj.accept(this);
//		}
//	}

	// public LTLOWLObjectRenderer(LTLOWLOntology ltl_ontology, OWLXMLWriter
	// writer) {
	// super(ltl_ontology.getOWLOntology(), writer);
	// this.writer = writer;
	// }

	public void visit(AtomicAxiom at_axiom) {
		writeAnnotations(at_axiom.getOWLAxiom());
		at_axiom.getOWLAxiom().accept(this);
	}

	public void visit(ConjunctiveAxiom conj_axiom) {

		this.writer.writeStartElement(FormulaType.CONJECTION);
				
		conj_axiom.getLeftAxiom().accept(this);
		conj_axiom.getRightAxiom().accept(this);
		this.writer.writeEndElement(FormulaType.CONJECTION);
		
	}

	
	public void visit(DisjunctiveAxiom disj_axiom) {

		this.writer.writeStartElement(FormulaType.DISJUNCTION);
		disj_axiom.getLeftAxiom().accept(this);
		disj_axiom.getRightAxiom().accept(this);
		this.writer.writeEndElement(FormulaType.DISJUNCTION);
		
	}

	public void visit(NegativeAxiom neg_axiom) {

		this.writer.writeStartElement(FormulaType.NEGATION);
		neg_axiom.getLeftAxiom().accept(this);
		this.writer.writeEndElement(FormulaType.NEGATION);
		
	}

	public void visit(NextAxiom nex_axiom) {

		this.writer.writeStartElement(FormulaType.NEXT);
		nex_axiom.getLeftAxiom().accept(this);
		this.writer.writeEndElement(FormulaType.NEXT);
		
	}

	public void visit(UntilAxiom until_axiom) {

		this.writer.writeStartElement(FormulaType.UNTIL);
		this.writer.writeLeftElement();
		until_axiom.getLeftAxiom().accept(this);
		this.writer.writeEndLeftElement();
		
		this.writer.writeRightElement();	
		until_axiom.getRightAxiom().accept(this);
		this.writer.writeEndRightElement();

		this.writer.writeEndElement(FormulaType.UNTIL);

		
	}

	@Override
	public void visit(OWLOntology arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDeclarationAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLSubClassOfAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLNegativeObjectPropertyAssertionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLAsymmetricObjectPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLReflexiveObjectPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDisjointClassesAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataPropertyDomainAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectPropertyDomainAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLEquivalentObjectPropertiesAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLNegativeDataPropertyAssertionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDifferentIndividualsAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDisjointDataPropertiesAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDisjointObjectPropertiesAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectPropertyRangeAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectPropertyAssertionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLFunctionalObjectPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLSubObjectPropertyOfAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDisjointUnionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLSymmetricObjectPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataPropertyRangeAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLFunctionalDataPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLEquivalentDataPropertiesAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLClassAssertionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLEquivalentClassesAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataPropertyAssertionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLTransitiveObjectPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLIrreflexiveObjectPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLSubDataPropertyOfAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLInverseFunctionalObjectPropertyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLSameIndividualAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLSubPropertyChainOfAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLInverseObjectPropertiesAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLHasKeyAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDatatypeDefinitionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLRule arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLAnnotationAssertionAxiom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLClass arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectIntersectionOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectUnionOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectComplementOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectSomeValuesFrom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectAllValuesFrom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectHasValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectMinCardinality arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectExactCardinality arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectMaxCardinality arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectHasSelf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectOneOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataSomeValuesFrom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataAllValuesFrom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataHasValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataMinCardinality arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataExactCardinality arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataMaxCardinality arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(OWLFacetRestriction arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDatatype arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataOneOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataComplementOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataIntersectionOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataUnionOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDatatypeRestriction arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectProperty arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLObjectInverseOf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLDataProperty arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLNamedIndividual arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OWLAnonymousIndividual arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IRI arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLClassAtom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLDataRangeAtom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLObjectPropertyAtom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLDataPropertyAtom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLBuiltInAtom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLVariable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLIndividualArgument arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLLiteralArgument arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLSameIndividualAtom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SWRLDifferentIndividualsAtom arg0) {
		// TODO Auto-generated method stub
		
	}


}
