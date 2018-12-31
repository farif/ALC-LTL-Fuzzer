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

import java.io.IOException;
import java.io.Writer;

import org.coode.owlapi.owlxml.renderer.OWLXMLWriter;
import org.semanticweb.owlapi.model.OWLOntology;

import tud.inf.dl.alc.ltl.util.Entry.FormulaType;

/**
 * LTLOWLXMLWriter does the following task(s): 
 * 1. Write the LTLOWLOntology in output file.
 * 
 * @version
 * @author
 */

public class LTLOWLXMLWriter extends OWLXMLWriter {

	private Writer writer;

	public LTLOWLXMLWriter(Writer writer, OWLOntology ontology) {
		super(writer, ontology);
		this.writer = writer;
		// TODO Auto-generated constructor stub
	}

	
	public void writeStartDocument() {
		try {
			this.writer.write("<Formula>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeEndDocument() {
		try {
			this.writer.write("</Formula>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeAxiomStart() {
		try {
			this.writer.write("<OWLAxiom>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeAxiomEnd() {
		try {
			this.writer.write("</OWLAxiom>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeStartAxiomID() {
		try {
			this.writer.write("<ID>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeEndAxiomID() {
		try {
			this.writer.write("</ID>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeStartElement(FormulaType fm) {

		switch (fm) {

		case CONJECTION: {
			try {
				this.writer.write("<ConjunctionOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case DISJUNCTION: {

			try {
				this.writer.write("<DisjunctionOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case UNTIL: {
			try {

				this.writer.write("<UntilOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case NEGATION: {

			try {
				this.writer.write("<NegationOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case NEXT: {
			try {
				this.writer.write("<NextOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		}
	}

	public void writeLeftElement() {
		try {
			this.writer.write("<LeftOf>\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeEndLeftElement() {
		try {
			this.writer.write("</LeftOf>\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeRightElement() {
		try {
			this.writer.write("<RightOf>\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeEndRightElement() {
		try {
			this.writer.write("</RightOf>\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void writeEndElement(FormulaType fm) {

		switch (fm) {

		case CONJECTION: {
			try {
				this.writer.write("</ConjunctionOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case DISJUNCTION: {

			try {
				this.writer.write("</DisjunctionOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case UNTIL: {
			try {

				this.writer.write("</UntilOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case NEGATION: {

			try {
				this.writer.write("</NegationOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		case NEXT: {
			try {
				this.writer.write("</NextOf>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		}
	}

}
