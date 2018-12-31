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


import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
/**
 * LTLOWLXMLOntologyFormat does the following task(s):
 * a. Extension of OWLOntology Format
 * 
 * @version
 * @author
 */

public class LTLOWLXMLOntologyFormat extends OWLXMLOntologyFormat {

    @Override
    public String toString() {
        return "LTL/OWL XML Format";
    }
    
}
