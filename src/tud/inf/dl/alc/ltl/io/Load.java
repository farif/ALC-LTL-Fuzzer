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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntology;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntologyImpl;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntologyManager;

/**
 * LoadOntology does the following tasks: 
 * 1. Create a New Ontology. 
 * 2. Open an Ontology From a given URL. 
 * 3. Open an Ontology From a given FILE.
 * 

 * @version
 * @author
 */

public class Load {

	/**
	 * Create a new Ontology
	 * 
	 * @param manager
	 * @return
	 * @throws OWLOntologyCreationException
	 */
	public static OWLOntology createOntology(OWLOntologyManager manager)
			throws OWLOntologyCreationException {

		OWLOntology ontology = manager.createOntology();

		return ontology;
	}

	/**
	 * Access an online Ontology
	 * 
	 * @param manager
	 * @param url
	 * @return
	 * @throws OWLOntologyCreationException
	 */
	public static OWLOntology webOntology(OWLOntologyManager manager, String url)
			throws OWLOntologyCreationException {

		IRI iri = IRI.create(url);
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(iri);

		return ontology;
	}

	/**
	 * Access a local Ontology
	 * 
	 * @param manager
	 * @param path
	 * @return
	 * @throws OWLOntologyCreationException
	 */
	public static OWLOntology fileOntology(OWLOntologyManager manager,
			File file_path) throws OWLOntologyCreationException {

		OWLOntology ontology = manager
				.loadOntologyFromOntologyDocument(file_path);

		return ontology;
	}

	/**
	 * Access a local Ontology
	 * 
	 * @param manager
	 * @param path
	 * @return
	 * @throws OWLOntologyCreationException
	 */
	public static LTLOWLOntology fileOntology(LTLOWLOntologyManager ltl_manager,
			File file_path) throws OWLOntologyCreationException {

		OWLOntology ontology = ltl_manager
				.loadOntologyFromOntologyDocument(file_path);
		
		LTLOWLOntology ltl_ontology = new LTLOWLOntologyImpl(ltl_manager, ontology.getOntologyID());
		
		ltl_ontology.setOWLOntology(ontology);

		return ltl_ontology;
	}

	// /**
	// * Access a local ontology
	// *
	// * @param manager
	// * @param path
	// * @return
	// * @throws OWLOntologyCreationException
	// */
	// public static LTLOWLOntology fileOntology(LTLOWLOntologyManager
	// ltl_manager,
	// File file_path) throws OWLOntologyCreationException {
	//
	// OWLOntology ontology =
	// ltl_manager.loadOntologyFromOntologyDocument(file_path);
	//
	// return ontology;
	// }

	/**
	 * Load concepts from given Ontology
	 * 
	 * @param ontology
	 * @return
	 */
	public static List<OWLClass> concepts(OWLOntology ontology) {

		List<OWLClass> concepts = new ArrayList<OWLClass>(ontology
				.getClassesInSignature());
		return concepts;
	}

	/**
	 * Load concepts from given LTLOntology
	 * 
	 * @param ontology
	 * @return
	 */
	public static List<OWLClass> concepts(LTLOWLOntology ltl_ontology) {

		List<OWLClass> concepts = new ArrayList<OWLClass>(ltl_ontology.getOWLOntology()
				.getClassesInSignature());
		return concepts;
	}

	/**
	 * Load roles from given Ontology
	 * 
	 * @param ontology
	 * @return
	 */
	public static List<OWLObjectProperty> roles(OWLOntology ontology) {

		List<OWLObjectProperty> roles = new ArrayList<OWLObjectProperty>(
				ontology.getObjectPropertiesInSignature());
		return roles;
	}

	/**
	 * Load roles from given LTLOWLOntology
	 * 
	 * @param ontology
	 * @return
	 */
	public static List<OWLObjectProperty> roles(LTLOWLOntology ltl_ontology) {

		List<OWLObjectProperty> roles = new ArrayList<OWLObjectProperty>(
				ltl_ontology.getOWLOntology().getObjectPropertiesInSignature());
		return roles;
	}

	/**
	 * Load individuals from given Ontology
	 * 
	 * @param ontology
	 * @return
	 */
	public static List<OWLNamedIndividual> individuals(OWLOntology ontology) {

		List<OWLNamedIndividual> individuals = new ArrayList<OWLNamedIndividual>(
				ontology.getIndividualsInSignature());
		return individuals;
	}

	/**
	 * Load individuals from given LTLOWLOntology
	 * 
	 * @param ontology
	 * @return
	 */
	public static List<OWLNamedIndividual> individuals(LTLOWLOntology ltl_ontology) {

		List<OWLNamedIndividual> individuals = new ArrayList<OWLNamedIndividual>(
				ltl_ontology.getOWLOntology().getIndividualsInSignature());
		return individuals;
	}

}
