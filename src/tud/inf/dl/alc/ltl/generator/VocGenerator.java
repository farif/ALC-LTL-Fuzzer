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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.PrefixManager;

import tud.inf.dl.alc.ltl.util.RandomGenerator;

/**
 * VocGenerator does the following two task(s): 
 * 1. Generate Concept Set of provided length. 
 * 2. Generate Role Set of provided length. 
 * 3. Generate Individual set of provided length.
 * 
 * VocGenerator use following major class: 
 * a. SymbolGenerator
 * 
 * @version
 * @author
 */

public class VocGenerator {

	private OWLDataFactory factory = null;
	private PrefixManager pm = null;

	/** Set of Concept Names */
	private Set<String> Nc;
	/** Set of Concept Names */
	private List<OWLClass> NC;

	/** Concept Name set Size */
	private int size_Nc;

	/** Set of Role Names */
	private Set<String> Nr;
	/** Set of Role Names */
	private List<OWLObjectProperty> NR;

	/** Role Names set Size */
	private int size_Nr;

	/** Set of Individual Names */
	private Set<String> Ni;
	/** Set of Individual Names */
	private List<OWLNamedIndividual> NI;

	/** Individual Names set Size */
	private int size_Ni;

	/** Pattern use to generate concept names" */
	private String c_pattern = "c";
	/** Pattern use to generate role names" */
	private String r_pattern = "r";
	/** Pattern use to generate individual names" */
	private String i_pattern = "a";

	/**
	 * Constructor
	 * 
	 * @param size_Nc
	 *            : size of Concept Set.
	 * @param size_Nr
	 *            : size of Role Set.
	 * @param size_Ni
	 *            : size of Individuals Set.
	 */
	public VocGenerator(OWLDataFactory factory, PrefixManager pm, int size_Nc,
			int size_Nr, int size_Ni) {

		this.factory = factory;
		/**
		 * Sample Prefix =
		 * "http://www.semanticweb.org/owlapi/ontologies/ontology#"
		 */
		this.pm = pm;

		this.size_Nc = size_Nc;
		this.size_Nr = size_Nr;
		this.size_Ni = size_Ni;

		generate();
	}

	public VocGenerator(OWLDataFactory factory, List<OWLClass> nc,
			List<OWLObjectProperty> nr, List<OWLNamedIndividual> ni) {

		this.factory = factory;

		this.NC = nc;
		this.NR = nr;
		this.NI = ni;

		this.size_Nc = nc.size();
		this.size_Nr = nr.size();
		this.size_Ni = ni.size();

	}

	/**
	 * Generate and filling Concept, Role & individual Names SymbolGenerator
	 * generate names according to provided pattern and given size
	 */
	private void generate() {

		// Generate random Concept
		Nc = new HashSet<String>(size_Nc);
		RandomGenerator.fixfillSet(c_pattern, Nc, this.size_Nc);

		// Generate random Roles
		Nr = new HashSet<String>(size_Nr);
		RandomGenerator.fixfillSet(r_pattern, Nr, this.size_Nr);

		// Generate random Individuals
		Ni = new HashSet<String>(size_Ni);
		RandomGenerator.fixfillSet(i_pattern, Ni, this.size_Ni);

		/** Generate random Concept Entities */
		NC = new ArrayList<OWLClass>(size_Nc);
		for (String concept_name : Nc) {
			OWLClass cls = factory.getOWLClass(concept_name, this.pm);
			this.NC.add(cls);
		}

		/** Generate random Roles Entities */
		NR = new ArrayList<OWLObjectProperty>(size_Nc);
		for (String role_name : Nr) {
			OWLObjectProperty role = factory.getOWLObjectProperty(role_name,
					this.pm);
			this.NR.add(role);
		}

		/** Generate random Individual Entities */
		NI = new ArrayList<OWLNamedIndividual>(size_Nc);
		for (String individual_name : Ni) {
			OWLNamedIndividual individual = factory.getOWLNamedIndividual(
					individual_name, this.pm);
			this.NI.add(individual);
		}

	}

	public List<OWLClass> getConcepts() {
		return NC;
	}

	public List<OWLObjectProperty> getRoles() {
		return NR;
	}

	public List<OWLNamedIndividual> getIndividuals() {
		return NI;
	}

	public void setConcepts(List<OWLClass> concepts) {
		NC = concepts;
		this.size_Nc = NC.size();
	}

	public void getRoles(List<OWLObjectProperty> roles) {
		NR = roles;
		this.size_Nr = NR.size();
	}

	public void getIndividuals(List<OWLNamedIndividual> individuals) {
		NI = individuals;
		this.size_Ni = NI.size();
	}


}
