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
import java.util.Set;
import java.util.Vector;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import tud.inf.dl.alc.ltl.visitor.PrettyVisitor;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import tud.inf.dl.alc.ltl.io.Load;
import tud.inf.dl.alc.ltl.io.Store;
import tud.inf.dl.alc.ltl.util.CmdParser;

/**
 * ALCGenerator does the following two tasks:
 * 
 * 1. Generate & Store Vocabulary 
 * 2. Generate & Store ALC Axioms
 * 
 * ALCGenerator uses following major classes: 
 * a. AxiomGenerator 
 * b. VocGenerator
 * 
 * @version
 * @author
 */

public class ALCGenerator {

	private OWLOntologyManager manager = null;
	protected OWLOntology ontology = null;

	protected OWLDataFactory factory = null;
	private PrefixManager pm = null;

	private List<OWLAxiom> axiom_list = null;

	protected PrettyVisitor visitor = null;

	protected static Integer ax_index = 0;

	public ALCGenerator() {

		this.manager = OWLManager.createOWLOntologyManager();
		this.visitor = new PrettyVisitor();
		this.axiom_list = new Vector<OWLAxiom>();
	}

	/**
	 * Generate Following Sets: a. Nc - Concept Set b. Nr - Role Set c. Ni -
	 * Individual Set
	 * 
	 * @throws OWLOntologyCreationException
	 * @throws OWLOntologyStorageException
	 */
	public void generate_sets() throws OWLOntologyCreationException,
			OWLOntologyStorageException {

		ontology = Load.createOntology(manager);
		factory = manager.getOWLDataFactory();

		pm = new DefaultPrefixManager("http://www.semanticweb.org/"
				+ IRI.generateDocumentIRI() + "#");

		VocGenerator voc = new VocGenerator(factory, pm, CmdParser.nc_size,
				CmdParser.nr_size, CmdParser.ni_size);
		/** Storing generated in ontology */
		Store.conceptAxiom(manager, factory, ontology, voc.getConcepts());
		Store.roleAxiom(manager, factory, ontology, voc.getRoles());
		Store.individualAxiom(manager, factory, ontology, voc.getIndividuals());

		Store.ontology(manager, ontology, 'r', CmdParser.output_file);

	}

	/**
	 * Generate Assersion Axioms: a. Concept Assertion Axiom b. Role Assertion
	 * Axiom c. Negated Role Assertion Axiom b. GCI Assertion Axiom
	 * 
	 * @throws OWLOntologyCreationException
	 * @throws OWLOntologyStorageException
	 */
	public void generate_axioms() throws OWLOntologyCreationException,
			OWLOntologyStorageException {

		ontology = Load.fileOntology(manager, CmdParser.input_file);

		factory = manager.getOWLDataFactory();

		VocGenerator voc = new VocGenerator(factory, Load.concepts(ontology),
				Load.roles(ontology), Load.individuals(ontology));

		AxiomGenerator axiom_gen = new AxiomGenerator(factory, voc
				.getConcepts(), voc.getRoles(), voc.getIndividuals());

		for (int i = 0; i < CmdParser.formula_size; i++) {
			ax_index++;
			this.axiom_list.add(axiom_gen
					.getAssertion(CmdParser.formula_length,ax_index.toString()));
		}

		/** Pretty prettying on system.out in human readability format */
		System.out.println("Axioms:");

		int index = 0;

		for (OWLAxiom ax : this.axiom_list) {
			System.out.println(++index + ": " + ax.accept(this.visitor));
		}

		if (CmdParser.isStore()) {
			/** Storing generated axioms in ontology */
			Store.axioms(manager, ontology, this.axiom_list);

			Store.ontology(manager, ontology, 'r', CmdParser.fm_output_file);
		}
	}

	public void load_axioms(){
		
		
		try {
			this.ontology = Load.fileOntology(manager, CmdParser.fm_output_file);
			
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public Vector<OWLAxiom> getALCAxioms() {
		
	    Vector<OWLAxiom> axiom_list = new Vector<OWLAxiom>(this.ontology.getAxioms());
		return axiom_list;
		
	}

}
