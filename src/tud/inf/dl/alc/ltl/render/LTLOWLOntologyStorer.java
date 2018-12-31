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

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.AbstractOWLOntologyStorer;

import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntology;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntologyManager;

import java.io.IOException;
import java.io.Writer;
/**
 * LTLOWLOntologyStorer does the following task(s):
 * a. Store LTL OWL Ontology
 * 
 * @version
 * @author
 */

public class LTLOWLOntologyStorer extends AbstractOWLOntologyStorer {
	@Override
	public boolean canStoreOntology(OWLOntologyFormat ontologyFormat) {
		return ontologyFormat.equals(new LTLOWLXMLOntologyFormat());
	}

	/**
	 * Register the LTL Renderer to Write the output XML
	 * 
	 * @param manager
	 * @param ontology
	 * @param writer
	 * @param format
	 * @throws OWLOntologyStorageException
	 */
	protected void storeOntology(LTLOWLOntologyManager manager,
			LTLOWLOntology ontology, Writer writer, OWLOntologyFormat format)
			throws OWLOntologyStorageException {
		try {
			LTLOWLXMLRenderer renderer = new LTLOWLXMLRenderer(manager);
			renderer.render(ontology, writer, format);
			writer.flush();
		} catch (IOException e) {
			throw new OWLRuntimeException(e);
		}
	}

	@Override
	protected void storeOntology(OWLOntologyManager arg0, OWLOntology arg1,
			Writer arg2, OWLOntologyFormat arg3)
			throws OWLOntologyStorageException {

	}

}
