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

package tud.inf.dl.alc.ltl.owlapi;

import java.util.List;
import java.util.Vector;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyID;

import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.visitor.LTLOWLOntologyVisitor;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyImpl;
/**
 * LTLOWLOntologyImpl does the following tasks: 
 * LTL extention of OWL Ontology
 * 
 * @version
 * @author
 */

public class LTLOWLOntologyImpl extends OWLOntologyImpl implements
		LTLOWLOntology {

	private List<LTLOWLAxiom> ltl_axiom_list = null;

	private OWLOntology ontology;

	public LTLOWLOntologyImpl(LTLOWLOntologyManager manager,
			OWLOntologyID ontologyID) {

		super(manager, ontologyID);
		ltl_axiom_list = new Vector<LTLOWLAxiom>();

	}

	public List<LTLOWLAxiom> getLTLAxioms() {
		return this.ltl_axiom_list;
	}

	public int getLTLAxiomCount() {
		return this.ltl_axiom_list.size();
	}


	public void addLTLAxiom(LTLOWLAxiom ltl_axiom) {
		this.ltl_axiom_list.add(ltl_axiom);
	}

	public OWLOntology getOWLOntology() {

		return this.ontology;
	}

	public void setOWLOntology(OWLOntology ontology) {

		this.ontology = ontology;

	}

	public void accept(LTLOWLOntologyVisitor visitor) {
		visitor.visit(this);
		
	}
}