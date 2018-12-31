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

import org.semanticweb.owlapi.model.OWLOntology;

import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.visitor.LTLOWLOntologyVisitor;

/**
 * LTLOWLOntology does the following task(s): 
 * LTL extention of OWL Ontology
 * 
 * @version
 * @author
 */

public interface LTLOWLOntology {

	public List<LTLOWLAxiom> getLTLAxioms();

	public int getLTLAxiomCount();

	public void addLTLAxiom(LTLOWLAxiom axiom);

	public void setOWLOntology(OWLOntology ontology);

	public OWLOntology getOWLOntology();

	public void accept(LTLOWLOntologyVisitor visitor);

}
