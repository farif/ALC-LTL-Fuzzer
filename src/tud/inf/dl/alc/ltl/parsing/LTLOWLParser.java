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

package tud.inf.dl.alc.ltl.parsing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tud.inf.dl.alc.ltl.axioms.AtomicAxiom;
import tud.inf.dl.alc.ltl.axioms.ConjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.DisjunctiveAxiom;
import tud.inf.dl.alc.ltl.axioms.LTLOWLAxiom;
import tud.inf.dl.alc.ltl.axioms.NegativeAxiom;
import tud.inf.dl.alc.ltl.axioms.NextAxiom;
import tud.inf.dl.alc.ltl.axioms.UntilAxiom;
import tud.inf.dl.alc.ltl.io.Load;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntology;
import tud.inf.dl.alc.ltl.owlapi.LTLOWLOntologyManager;
import tud.inf.dl.alc.ltl.visitor.PrettyVisitor;

/**
 * LTLOWLParser does the following two task: 
 * 1. Load OWL Ontology File 
 * 2. Load Formual Ontology File 
 * 3. Parse & Load Formula Axoims into Internal structure.
 * 4. Print Axioms w.r.t to assigned reference codes
 *  
 * LTLOWLParser use DOM for XML parssing.
 * 
 * @version
 * @author
 */

public class LTLOWLParser {

	// Number of Axioms in all formulas
	public static int nr_axioms;
	// List of axioms occur in each and every formula
	public static Set<OWLAxiom> axiom_set;
	// Hashing mapping for each axiom
	public static HashMap<String, OWLAxiom> axiom_map;
	// List of Formula Axioms
	public static Vector<LTLOWLAxiom> ltl_axiom_set;

	static {
		nr_axioms = -1;
		axiom_set = new TreeSet<OWLAxiom>();
		axiom_map = new HashMap<String, OWLAxiom>();
		ltl_axiom_set = new Vector<LTLOWLAxiom>();
	}

	/**
	 * Loading Ontology File
	 * 
	 * @param ltl_manager
	 * @param ltl_ontology
	 * @param input_file
	 * @throws OWLOntologyCreationException
	 */
	public static void loadOntology(LTLOWLOntologyManager ltl_manager,
			LTLOWLOntology ltl_ontology, File input_file)
			throws OWLOntologyCreationException {

		ltl_ontology = Load.fileOntology(ltl_manager, input_file);

		nr_axioms = ltl_ontology.getOWLOntology().getAxiomCount();
		axiom_set = ltl_ontology.getOWLOntology().getAxioms();

		parse(input_file);

	}


	/**
	 * Parsing XML intput Document
	 * 
	 * @param input_file
	 */
	public static void parse(File input_file) {

		/** Initialization **/
		for (OWLAxiom ax : axiom_set) {

			for (OWLAnnotation ax_annotation : ax.getAnnotations()) {

				OWLLiteral val = (OWLLiteral) ax_annotation.getValue();

				axiom_map.put(val.getLiteral(), ax);
			}

		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			System.out.println("Formula file" + input_file.getAbsolutePath()
					+ ".xml");
			// parse using builder to get DOM representation of the XML file
			Document doc = db.parse(input_file.getAbsolutePath() + ".xml");

			doc.getDocumentElement().normalize();

			// System.out.println("Root element " +
			// doc.getDocumentElement().getNodeName());
			NodeList nodelist = doc.getElementsByTagName("Formula");

			System.out.println("Number of Formulas: " + nodelist.getLength());

			// LTLOWLAxiom ax = parseFormula(nodelist);
			parsefm(nodelist);
			// if(ax != null) {
			// PrettyVisitor visitor = new PrettyVisitor();
			// System.err.println(": " + ax.accept(visitor));
			// }

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * Parsing ALC LTL Formula
	 * 
	 * @param vec
	 * @param ltl_axiom
	 * @return
	 */
	public static LTLOWLAxiom parseLTLAxiom(Iterator<String> vec,
			LTLOWLAxiom ltl_axiom) {

		String element = vec.next();

		if (element.equals("NEG")) {

			return new NegativeAxiom(parseLTLAxiom(vec, ltl_axiom));
		} else if (element.equals("NEXT")) {

			return new NextAxiom(parseLTLAxiom(vec, ltl_axiom));
		} else if (element.equals("AND")) {
			return new ConjunctiveAxiom(parseLTLAxiom(vec, ltl_axiom),
					parseLTLAxiom(vec, ltl_axiom));
		} else if (element.equals("OR")) {
			return new DisjunctiveAxiom(parseLTLAxiom(vec, ltl_axiom),
					parseLTLAxiom(vec, ltl_axiom));
		} else if (element.equals("UNTIL")) {
			// if(vec.next().equals("LEFT"))
			return new UntilAxiom(parseLTLAxiom(vec, ltl_axiom), parseLTLAxiom(
					vec, ltl_axiom));
		} else {
			// ATOM
			String ax_id = element; // = childNode.getTextContent();
			OWLAxiom axiom = axiom_map.get(ax_id);
			return new AtomicAxiom(axiom);
		}
	}

	/**
	 * Loading ALC LTL Formula
	 * 
	 * @param nodeList
	 * @param vec
	 */
	public static void parseFormula(NodeList nodeList, Vector<String> vec) {

		for (int i = 0; i < nodeList.getLength(); i++) {

			Node childNode = nodeList.item(i);

			if (childNode.getNodeName() == "Literal") {
				// Atomic Formula

				String ax_id = childNode.getTextContent();
				vec.add(ax_id);

			} else if (childNode.getNodeName() == "NegationOf") {
				vec.add("NEG");
				// ltl_axiom = new NegativeAxiom(ltl_axiom);
			} else if (childNode.getNodeName() == "NextOf") {
				vec.add("NEXT");
			} else if (childNode.getNodeName() == "ConjunctionOf") {
				vec.add("AND");
			} else if (childNode.getNodeName() == "DisjunctionOf") {
				vec.add("OR");
			} else if (childNode.getNodeName() == "UntilOf") {
				vec.add("UNTIL");
			}

			NodeList children = childNode.getChildNodes();

			if (children != null) {
				parseFormula(children, vec);
			}
		}

	}

	/**
	 * Loading ALC LTL Formulas
	 * 
	 * @param nodelist
	 */
	public static void parsefm(NodeList nodelist) {

		int fm_index = 1;

		for (int i = 0; i < nodelist.getLength(); i++) {
			Node childNode = nodelist.item(i);

			if (childNode.getNodeName() == "Formula") {

				Vector<String> vec = new Vector<String>();
				parseFormula(childNode.getChildNodes(), vec);

				if (vec != null) {
					LTLOWLAxiom ax = null;
					ax = parseLTLAxiom(vec.iterator(), ax);
					if (ax != null) {
						PrettyVisitor visitor = new PrettyVisitor();
						System.out.println("Load Formula(" + fm_index + ") := "
								+ ax.accept(visitor));
					}
					ltl_axiom_set.add(ax);
					fm_index++;
				}

			} else {
				parsefm(childNode.getChildNodes());
			}
		}
	}

	/**
	 * Printing axioms with reference code occur in each ALC LTL Formula
	 */
	public static void printAxiomSet() {

		Set<Entry<String, OWLAxiom>> pr_set = axiom_map.entrySet();
		Iterator<Entry<String, OWLAxiom>> i = pr_set.iterator();
		PrettyVisitor visitor = new PrettyVisitor();
		System.out.println("Axioms Reference Key:");
		while (i.hasNext()) {
			Map.Entry<String, OWLAxiom> me = (Map.Entry<String, OWLAxiom>) i
					.next();
			System.out.println(me.getKey() + " : "
					+ me.getValue().accept(visitor));
		}

	}

}
