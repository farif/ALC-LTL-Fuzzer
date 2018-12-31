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

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.io.Load;
import tud.inf.dl.alc.ltl.io.Store;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLManager;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntology;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntologyManager;
import tud.inf.dl.alc.ltl.parsing.LTLOWLParser;
import tud.inf.dl.alc.ltl.render.LTLOWLOntologyStorer;
import tud.inf.dl.alc.ltl.util.CmdParser;

/**
 * ALCGenerator does the following two tasks:
 * 1. Generate & Store ALC-LTL Formulas.
 * 
 * ALCGenerator uses following major classes: 
 * a. AxiomGenerator 
 * b. VocGenerator
 * c. ALCgenerator
 * 
 * @version
 * @author
 */

public class ALCLTLGenerator extends ALCGenerator {

	private LTLOWLOntologyManager ltl_manager = null;
	private LTLOWLOntology ltl_ontology = null;

	private List<LTLOWLAxiom> alc_ltl_fm = null;

	
	public ALCLTLGenerator(){
		super();
		this.ltl_manager = LTLOWLManager.createLTLOWLOntologyManager();
		this.alc_ltl_fm = new Vector<LTLOWLAxiom>();
	}
	
	/**
	 * Generate ALC-LTL Formulas
	 * 
	 * @throws OWLOntologyCreationException
	 * @throws OWLOntologyStorageException
	 * @throws IOException
	 */
	public void generate_axiom_formulae() throws OWLOntologyCreationException,
			OWLOntologyStorageException, IOException {

		this.ltl_ontology  = Load.fileOntology(this.ltl_manager, CmdParser.input_file);

		factory = this.ltl_manager.getOWLDataFactory();

		VocGenerator voc = new VocGenerator(factory, Load.concepts(ltl_ontology),
				Load.roles(ltl_ontology), Load.individuals(ltl_ontology));

		FormulaGenerator fm_gen = new FormulaGenerator(factory, voc
				.getConcepts(), voc.getRoles(), voc.getIndividuals());

		for (int i = 1; i <= CmdParser.formula_size; i++) {
			alc_ltl_fm.add(fm_gen.getFormula(null,
					CmdParser.axiom_asser_length, CmdParser.formula_length,false));
		}

		int index = 1;
		/** Pretty prettying on system.out in human readability format */
		System.out.println("ALC-LTL Formulas:");

		for (LTLOWLAxiom ltl_ax : alc_ltl_fm) {
			System.out.println(index + ": " + ltl_ax.accept(this.visitor));
			ltl_ontology.addLTLAxiom(ltl_ax);
			index++;
		}

//		System.out.println("ALC-LTL formulas saved in String (*.raw): "
//				+ CmdParser.fm_output_file + ".raw");
//		
//		Store.saveLTLAxioms(alc_ltl_fm, CmdParser.fm_output_file + ".raw");

		if(CmdParser.isStore()) {
			/* Register LTL ontology storer with the manager */
			this.ltl_manager.addOntologyStorer(new LTLOWLOntologyStorer());
	
			System.out.println("ALC-LTL Ontology saved in: " + CmdParser.fm_output_file + ".xml");
	
			Store.ontology(ltl_manager, ltl_ontology, CmdParser.fm_output_file);		
		}
		
		if (CmdParser.isStore()) {
			/** Storing generated axioms in ontology */
			Store.axioms(this.ltl_manager, ltl_ontology.getOWLOntology(), fm_gen.getAxioms());
			Store.ontology(this.ltl_manager, ltl_ontology.getOWLOntology(), 'r', CmdParser.fm_output_file);
		}
		
//		load_formulas();
		// CmdParser.fm_output_file
		// System.out.println(++index + ": " + ax.accept(visitor));

	}


	/**
	 * Generate ALC-LTL Formulas
	 * 
	 * @throws OWLOntologyCreationException
	 * @throws OWLOntologyStorageException
	 * @throws IOException
	 */
	public void generate_formulae() throws OWLOntologyCreationException,
			OWLOntologyStorageException, IOException {

		this.ltl_ontology  = Load.fileOntology(this.ltl_manager, CmdParser.input_file);

		factory = this.ltl_manager.getOWLDataFactory();
//
//		VocGenerator voc = new VocGenerator(factory, Load.concepts(ltl_ontology),
//				Load.roles(ltl_ontology), Load.individuals(ltl_ontology));

		FormulaGenerator fm_gen = new FormulaGenerator(this.factory,this.ltl_ontology.getOWLOntology());

		for (int i = 1; i <= CmdParser.formula_size; i++) {
			alc_ltl_fm.add(fm_gen.getFormula(null,
					0, CmdParser.formula_length,false));
		}

		int index = 1;
		/** Pretty prettying on system.out in human readability format */
		System.out.println("ALC-LTL Formulas:");

		for (LTLOWLAxiom ltl_ax : alc_ltl_fm) {
			System.out.println(index + ": " + ltl_ax.accept(this.visitor));
			ltl_ontology.addLTLAxiom(ltl_ax);
			index++;
		}

//		System.out.println("ALC-LTL formulas saved in String (*.raw): "
//				+ CmdParser.fm_output_file + ".raw");
//		
//		Store.saveLTLAxioms(alc_ltl_fm, CmdParser.fm_output_file + ".raw");

		if(CmdParser.isStore()) {
			/* Register LTL ontology storer with the manager */
			this.ltl_manager.addOntologyStorer(new LTLOWLOntologyStorer());
	
			System.out.println("ALC-LTL Ontology saved in: " + CmdParser.fm_output_file + ".xml");
	
			Store.ontology(ltl_manager, ltl_ontology, CmdParser.fm_output_file);		
		}
		
		if (CmdParser.isStore()) {
			/** Storing generated axioms in ontology */
			Store.axioms(this.ltl_manager, ltl_ontology.getOWLOntology(), fm_gen.getAxioms());
			Store.ontology(this.ltl_manager, ltl_ontology.getOWLOntology(), 'r', CmdParser.fm_output_file);
		}
		
//		load_formulas();
		// CmdParser.fm_output_file
		// System.out.println(++index + ": " + ax.accept(visitor));

	}

	public void load_formulas(){
		
		
		try {
			LTLOWLParser.loadOntology(ltl_manager, ltl_ontology, CmdParser.fm_output_file);
			LTLOWLParser.printAxiomSet();
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public List<LTLOWLAxiom> getALCLTLAxioms() {
		return this.alc_ltl_fm;
	}

}
