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

package tud.inf.dl.alc.ltl.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntology;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntologyManager;
import tud.inf.dl.alc.ltl.render.LTLOWLXMLOntologyFormat;

/**
 * StoreOntology does the following tasks: 
 * 1. Save an ontology into any of the following format. 
 *    a. OWLXML Format 
 *    b. Manchester Format 
 *    c. Default Format
 *     
 * 2. Store Axiom in Ontology 
 * 3. Store Axiom List in Ontology
 * 
 * @version
 * @author
 */

public class Store {

	/**
	 * Store ontology file in a given location
	 * 
	 * @param manager
	 * @param ontology
	 *            (Ontology to be stored)
	 * @param format
	 *            (OWLXML = 'r' or Manchester = 'm' or Default Format)
	 * @param path
	 *            (Location for file stroage)
	 * @throws OWLOntologyStorageException
	 */
	public static void ontology(OWLOntologyManager manager,
			OWLOntology ontology, char format, File file)
			throws OWLOntologyStorageException {

		IRI iri = IRI.create(file.toURI());
		OWLOntologyFormat owlformat = manager.getOntologyFormat(ontology);

		switch (format) {

			case 'r': {
				/** OWL/XML Format */
	
				OWLXMLOntologyFormat owlxmltype = new OWLXMLOntologyFormat();
	
				if (owlformat.isPrefixOWLOntologyFormat()) {
					owlxmltype.copyPrefixesFrom(owlformat
							.asPrefixOWLOntologyFormat());
				}
	
				manager.saveOntology(ontology, owlxmltype, iri);
	
				break;
			}
			case 'm': {
				/** Manchester Format */
	
				ManchesterOWLSyntaxOntologyFormat manySyntaxtype = new ManchesterOWLSyntaxOntologyFormat();
	
				if (owlformat.isPrefixOWLOntologyFormat()) {
					manySyntaxtype.copyPrefixesFrom(owlformat
							.asPrefixOWLOntologyFormat());
				}
	
				manager.saveOntology(ontology, manySyntaxtype, iri);
	
				break;
			}
	
			default:
				manager.saveOntology(ontology, iri);
			}

			System.out.println("Ontology saved: " + file.getAbsolutePath());

	}

	/**
	 * Store an axiom
	 * 
	 * @param manager
	 * @param ontology
	 * @param axiom
	 */
	public static void axiom(OWLOntologyManager manager, OWLOntology ontology,
			OWLAxiom axiom) {
		manager.addAxiom(ontology, axiom);
	}

	/**
	 * Store a number of axioms
	 * 
	 * @param manager
	 * @param ontology
	 * @param axiom_list
	 */
	public static void axioms(OWLOntologyManager manager, OWLOntology ontology,
			List<OWLAxiom> axiom_list) {

		for (OWLAxiom ax : axiom_list) {
			manager.addAxiom(ontology, ax);
		}

	}

	/**
	 * Store an axiom
	 * 
	 * @param manager
	 * @param ontology
	 * @param axiom
	 */
	public static void axiom(LTLOWLOntologyManager manager,
			LTLOWLOntology ontology, LTLOWLAxiom axiom) {
		manager.addLTLAxiom(ontology, axiom);
	}

	/**
	 * Store a number of axioms
	 * 
	 * @param manager
	 * @param ontology
	 * @param axiom_list
	 */
	public static void axioms(LTLOWLOntologyManager manager,
			LTLOWLOntology ontology, List<LTLOWLAxiom> axiom_list) {

		manager.addLTLAxioms(ontology, axiom_list);

	}

	/**
	 * Store a number of axioms
	 * 
	 * @param manager
	 * @param ontology
	 * @param axiom_list
	 */
	public static void conceptAxiom(OWLOntologyManager manager,
			OWLDataFactory factory, OWLOntology ontology, List<OWLClass> nc_set) {

		for (OWLClass cls : nc_set) {
			OWLDeclarationAxiom cls_axiom = factory
					.getOWLDeclarationAxiom((OWLEntity) cls);
			manager.addAxiom(ontology, cls_axiom);
		}

	}

	/**
	 * Store a number of axioms
	 * 
	 * @param manager
	 * @param ontology
	 * @param axiom_list
	 */
	public static void roleAxiom(OWLOntologyManager manager,
			OWLDataFactory factory, OWLOntology ontology,
			List<OWLObjectProperty> nr_set) {

		for (OWLObjectProperty role : nr_set) {
			OWLDeclarationAxiom role_axiom = factory
					.getOWLDeclarationAxiom((OWLEntity) role);
			manager.addAxiom(ontology, role_axiom);
		}

	}

	/**
	 * Store a number of axioms
	 * 
	 * @param manager
	 * @param ontology
	 * @param axiom_list
	 */
	public static void individualAxiom(OWLOntologyManager manager,
			OWLDataFactory factory, OWLOntology ontology,
			List<OWLNamedIndividual> ni_set) {

		for (OWLIndividual individual : ni_set) {
			OWLDeclarationAxiom individual_axiom = factory
					.getOWLDeclarationAxiom((OWLEntity) individual);
			manager.addAxiom(ontology, individual_axiom);
		}

	}

	public static void saveLTLAxioms(List<LTLOWLAxiom> ltl_axiom_list, String file_path) {

		try {
			BufferedWriter	bw = new BufferedWriter(new FileWriter(file_path));
			
			for (LTLOWLAxiom ltl_ax : ltl_axiom_list) {
				bw.write(ltl_ax + "\n");
			}
	
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Prints the node values in the "Inorder" order. Uses a recursive helper to
	 * do the traversal.
	 */

	public static void storeAxiom(LTLOWLAxiom node, OWLOntologyManager manager,
			OWLOntology ontology) {

		if (node == null) {
			return;
		}

		if (node.getOWLAxiom() != null)
			axiom(manager, ontology, node.getOWLAxiom());

		storeAxiom(node.getLeftAxiom(), manager, ontology);
		storeAxiom(node.getRightAxiom(), manager, ontology);
	}

	public static void ontology(LTLOWLOntologyManager ltl_manager, LTLOWLOntology ltl_ontology, File output_file) {
		
		ltl_manager.saveOntology(ltl_ontology, new LTLOWLXMLOntologyFormat(), output_file.getAbsolutePath() + ".xml");

	}
 }
