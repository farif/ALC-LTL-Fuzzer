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

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.coode.owlapi.owlxml.renderer.OWLXMLRenderer;
import org.semanticweb.owlapi.io.OWLRendererException;
import org.semanticweb.owlapi.io.OWLRendererIOException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.vocab.Namespaces;

import tud.inf.dl.alc.ltl.io.LTLOWLXMLWriter;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntology;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntologyManager;

/**
 * LTLOWLXMLRenderer does the following task(s): 
 * a. Serialize the LTLOWL Axioms in XML Format 
 *   
 * @version
 * @author
 */

public class LTLOWLXMLRenderer extends OWLXMLRenderer {

	public LTLOWLXMLRenderer(LTLOWLOntologyManager manager) {
		super(manager);
	}

	public LTLOWLXMLRenderer(OWLOntologyManager manager) {
		super(manager);
	}

	/**
	 * Render the LTL OWL Ontology elements 
	 * 
	 * @param ltl_ontology
	 * @param writer
	 * @param format
	 * @throws OWLRendererException
	 */
	public void render(LTLOWLOntology ltl_ontology, Writer writer,
			OWLOntologyFormat format) throws OWLRendererException {

		try {
		
			LTLOWLXMLWriter w = new LTLOWLXMLWriter(writer, ltl_ontology
					.getOWLOntology());
			w.startDocument(ltl_ontology.getOWLOntology());

			if (format instanceof LTLOWLXMLOntologyFormat) {
				LTLOWLXMLOntologyFormat fromPrefixFormat = (LTLOWLXMLOntologyFormat) format;
				final Map<String, String> map = fromPrefixFormat
						.getPrefixName2PrefixMap();
				for (String prefixName : map.keySet()) {
					String prefix = map.get(prefixName);
					if (prefix != null && prefix.length() > 0) {
						w.writePrefix(prefixName, prefix);
					}
				}
				if (!map.containsKey("rdf:")) {
					w.writePrefix("rdf:", Namespaces.RDF.toString());
				}
				if (!map.containsKey("rdfs:")) {
					w.writePrefix("rdfs:", Namespaces.RDFS.toString());
				}
				if (!map.containsKey("xsd:")) {
					w.writePrefix("xsd:", Namespaces.XSD.toString());
				}
				if (!map.containsKey("owl:")) {
					w.writePrefix("owl:", Namespaces.OWL.toString());
				}
			} else {
				w.writePrefix("rdf:", Namespaces.RDF.toString());
				w.writePrefix("rdfs:", Namespaces.RDFS.toString());
				w.writePrefix("xsd:", Namespaces.XSD.toString());
				w.writePrefix("owl:", Namespaces.OWL.toString());
			}

			LTLOWLObjectRenderer ren = new LTLOWLObjectRenderer(w);
			ltl_ontology.accept(ren);
			w.endDocument();
			// writer.flush();
		} catch (IOException e) {
			throw new OWLRendererIOException(e);
		}
	}

}
