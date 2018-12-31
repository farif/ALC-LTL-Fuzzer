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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.UnknownOWLOntologyException;

import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.render.LTLOWLXMLOntologyFormat;
import tud.inf.dl.alc.ltl.render.LTLOWLXMLRenderer;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyManagerImpl;

/**
 * LTLOWLOntologyManagerImpl does the following task(s): 
 * a. Extend the OWL Ontology to include LTL
 * 
 * @version
 * @author
 */

public class LTLOWLOntologyManagerImpl extends OWLOntologyManagerImpl implements
		LTLOWLOntologyManager {

	public LTLOWLOntologyManagerImpl(OWLDataFactory dataFactory) {
		super(dataFactory);
	}

	public void addLTLAxioms(LTLOWLOntology ltl_ontology,
			List<LTLOWLAxiom> ltl_axiom_list) {

		for (LTLOWLAxiom ax : ltl_axiom_list) {
			addLTLAxiom(ltl_ontology, ax);
		}
	}

	public void addLTLAxiom(LTLOWLOntology ltl_ontology, LTLOWLAxiom ltl_axiom) {
		ltl_ontology.addLTLAxiom(ltl_axiom);
	}

	public void saveOntology(LTLOWLOntology ltl_ontology,
			OWLOntologyFormat format, String file_path) {
		try {

			Writer writer = new BufferedWriter(new FileWriter(file_path));

			LTLOWLXMLRenderer renderer = new LTLOWLXMLRenderer(this);
			renderer.render(ltl_ontology, writer, new LTLOWLXMLOntologyFormat());

			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnknownOWLOntologyException e) {
			e.printStackTrace();
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
	}

}
