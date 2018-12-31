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

package tud.inf.dl.alc.ltl.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import tud.inf.dl.alc.ltl.generator.ALCLTLGenerator;
import tud.inf.dl.alc.ltl.util.CmdParser;
import tud.inf.dl.alc.ltl.util.ConfigLoader;

/**
 * Driver does the following task(s):
 * 
 * 1. Read the Command line 
 * 2. Generator Nc, Nr, Ni Sets
 * 3. Generator ALC Concept Descriptions
 * 4. Generator ALC-LTL Formulas
 * 
 * 
 * @version
 * @author
 */

public class Driver {
	//Logging Information
	private static final Logger logger = Logger.getLogger(Driver.class.getName());
	

	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException, Exception {

	    long startTime = System.currentTimeMillis();

		ALCLTLGenerator alc_ltl_fm = new ALCLTLGenerator();

		logger.log(Level.INFO, "Program is Running...");
		final String GEN_CONFIG_PATH = "gen_config.ini";
		
		CmdParser.licenceMsg();

		/** Parsing the Provided Arguments */
		CmdParser.loadArgs(args);

		ConfigLoader.loadConfig(new File(GEN_CONFIG_PATH));

		try {

			/** Generating Nc, Nr, Ni sets of given length */
			if (CmdParser.isGC()) {

				alc_ltl_fm.generate_sets();

			}

			/** Generation Assertions and GCIs for given length and size */
			else if (CmdParser.isGF()) {

				alc_ltl_fm.generate_axioms();

			}
			/** Generator ALC-LTL Formulas **/
			else if (CmdParser.isGG()) {

				alc_ltl_fm.generate_axiom_formulae();

			}
			/** Load ALC-LTL Formula(s) **/
			else if (CmdParser.isGL()) {

				alc_ltl_fm.load_formulas();

			}
			/** Load Axioms from Ontology File and generate ALC-LTL Formulae **/
			else if (CmdParser.isGA()) {

				alc_ltl_fm.generate_formulae();

			}  
			else {

				CmdParser.printUsage();

			}

			logger.log(Level.INFO, "Program is Terminated.");

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed:" +  e.getMessage());
			e.printStackTrace();
		} catch (OWLOntologyCreationException e) {
			logger.log(Level.SEVERE, "Failed:" +  e.getMessage());
			e.printStackTrace();
		} catch (OWLOntologyStorageException e) {
			logger.log(Level.SEVERE, "Failed:" +  e.getMessage());
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Total time(msec) elapsed: " + (endTime - startTime));

	}


}
