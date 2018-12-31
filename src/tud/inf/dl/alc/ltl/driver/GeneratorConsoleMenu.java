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

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


import tud.inf.dl.alc.ltl.generator.ALCLTLGenerator;
import tud.inf.dl.alc.ltl.util.CmdParser;
import tud.inf.dl.alc.ltl.util.ConfigLoader;

public class GeneratorConsoleMenu {

	/**
	 * 1. Display Menu 
	 * 2. Define Input Parameters
	 * 3. Call SET Generation Algorithm
	 * 4. Call ALC-concept description Generation Algorithm
	 * 5. Call ALC-axiom Generation Algorithm
	 * 6. Call ALC-LTL Generation Algorithm
	 * 
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException, Exception {

		ALCLTLGenerator alc_ltl_fm = new ALCLTLGenerator();
//		final String GEN_CONFIG_PATH = "gen_config.ini";
		
		CmdParser.licenceMsg();

		char menu_value;

		boolean terminate = false;

		display();
		
		Scanner console_input = new Scanner(System.in);

		do {

			menu_value = readInputOption();

			switch (menu_value) {

			case '1': {
				
				System.out.println("*** GENERATING SETS ***");
				System.out.print("*Input Nc Size : ");
				int nc_size = console_input.nextInt();
				System.out.print("*Input Nr Size : ");
				int nr_size = console_input.nextInt();
				System.out.print("*Input Ni Size : ");
				int ni_size = console_input.nextInt();
				System.out.print("*Output Ontology File Path : ");
				String file_path = readStringInput();
				System.out.println();
				String option = "-gc " + nc_size + " " + nr_size + " "
						+ ni_size + " -o" + file_path;
				args = option.split(" ");
				CmdParser.loadArgs(args);

				try {
					if (CmdParser.isGC()) {
						alc_ltl_fm.generate_sets();
					}

				} catch (OWLOntologyCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OWLOntologyStorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("*** PRESS RETURN TO CONTINUE ***");
				pause();
				clearScreen();
				display();

				break;
			}
			case '2': {

				System.out.println("*** GENERATING ALC-axioms ***");
				System.out.print("*Number of ALC-axioms : ");
				int ax_size = console_input.nextInt();
				System.out.print("*Length of an ALC-Concept Description : ");
				int ax_length = console_input.nextInt();
				System.out.print("*Input Ontology File Path : ");
				String file_path_in = readStringInput();
				System.out.print("*Input Configuration File Path : ");
				String config_file_path = readStringInput();
				System.out.print("*Output ALC-axioms File Path : ");
				String file_path_out = readStringInput();
				System.out.println();
				String option = "-gf " + ax_size + " " + ax_length + " -i "
						+ file_path_in + " -c " + config_file_path + " -w " + file_path_out;
				args = option.split(" ");
				
				CmdParser.loadArgs(args);
				ConfigLoader.loadConfig(CmdParser.config_file);

				try {
					if (CmdParser.isGF()) {
						alc_ltl_fm.generate_axioms();
					}

				} catch (OWLOntologyCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OWLOntologyStorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("*** PRESS RETURN TO CONTINUE ***");
				pause();
				clearScreen();
				display();

				break;
			}
			case '3': {

				System.out.println("*** GENERATING ALC-LTL Formulae ***");
				System.out.print("*Number of ALC-LTL formulae : ");
				int fm_size = console_input.nextInt();
				System.out.print("*Length of an ALC-LTL formula : ");
				int fm_length = console_input.nextInt();
				System.out.print("*Length of an ALC-Concept Description : ");
				int ax_length = console_input.nextInt();
				System.out.print("*Input Ontology File Path : ");
				String file_path_in = readStringInput();
				System.out.print("*Input Configuration File Path : ");
				String config_file_path = readStringInput();
				System.out.print("*Output ALC-LTL formulae Files Path : ");
				String file_path_out = readStringInput();
				System.out.println();
				String option = "-gg " + fm_size + " " + fm_length + " "
						+ ax_length + " -i "+ file_path_in + " -c " + config_file_path +" -w "
						+ file_path_out;
				args = option.split(" ");
				
				CmdParser.loadArgs(args);
				ConfigLoader.loadConfig(CmdParser.config_file);
				try {
					if (CmdParser.isGG()) {
						alc_ltl_fm.generate_axiom_formulae();
					}

				} catch (OWLOntologyCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OWLOntologyStorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("*** PRESS RETURN TO CONTINUE ***");
				pause();
				clearScreen();
				display();

				break;
			}
			case '4': {

				System.out.println("*** GENERATING ALC-LTL Formulae ***");
				System.out.print("*Number of ALC-LTL formulae : ");
				int fm_size = console_input.nextInt();
				System.out.print("*Length of an ALC-LTL formula : ");
				int fm_length = console_input.nextInt();
				System.out.print("*Input Ontology File Path : ");
				String file_path_in = readStringInput();
				System.out.print("*Input Configuration File Path : ");
				String config_file_path = readStringInput();
				System.out.print("*Output ALC-LTL formulae Files Path : ");
				String file_path_out = readStringInput();
				System.out.println();
				String option = "-ga " + fm_size + " " + fm_length +" -i "+ file_path_in + " -c " + config_file_path +" -w "
						+ file_path_out;
				args = option.split(" ");
				
				CmdParser.loadArgs(args);
				ConfigLoader.loadConfig(CmdParser.config_file);
				
				try {
					if (CmdParser.isGA()) {
						alc_ltl_fm.generate_formulae();
					}

				} catch (OWLOntologyCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OWLOntologyStorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("*** PRESS RETURN TO CONTINUE ***");
				pause();
				clearScreen();
				display();

				break;
			}
			case '5': {

				System.out.println("*** LOAD ALC-LTL Formulae ***");
				readIntInput();
				System.out.print("*Input ALC-LTL formulae Files Path : ");
				String file_path_out = readStringInput();
				String option = "-gl  -i " + file_path_out;
				args = option.split(" ");
				CmdParser.loadArgs(args);
				
				if (CmdParser.isGL()) {
					alc_ltl_fm.load_formulas();
				} 
				System.out.println("*** PRESS RETURN TO CONTINUE ***");
				pause();
				clearScreen();
				display();

				break;
			}
			case '6': {

				System.out.println("*** HELP ***");
				System.out.println("Consult Arif");
//				CmdParser.printUsage();
				System.out.println("*** PRESS RETURN TO CONTINUE ***");
				pause();
				clearScreen();
				display();

				break;
			}
			case '7': {
				clearScreen();
//				logger.log(Level.INFO, "Program is Terminated.");
				System.out.println(" Good Bye - Auf Wiedersehen !!! ");
				terminate = true;
				break;
			} 			
			default: {
				break;
			}
			}

		} while (!terminate);
	}

	/**
	 * Display Options
	 */
	public static void display() {

		System.out.println("========================================================");
		System.out.println("*            [ALC LTL GENERATOR MENU]                   *");
		System.out.println("========================================================*");
		System.out.println("* Options:                                              *");
		System.out.println("*        1. Generate Nc, Nr & Ni                        *");
		System.out.println("*        2. Generate ALC-axioms                         *");
		System.out.println("*        3. Generate ALC-LTL formulae using Nc, Nr & Ni *");
		System.out.println("*        4. Generate ALC-LTL formulae using ALC-axioms  *");
		System.out.println("*        5. Load ALC-LTL formulae                       *");
		System.out.println("*        6. Help                                        *");
		System.out.println("*        7. Exit                                        *");
		System.out.println("--------------------------------------------------------*");

		System.out.print("Choose an Option: ");

		System.out.flush();
	}

	/**
	 * Clear Screen
	 */
	public static void clearScreen() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	public static char readInputOption() {
		int option_value = 4;
		try {
			option_value = System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (char) option_value;
	}

	public static int readIntInput() {
		int value = 0;
		try {
			value = System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String readStringInput() {
		int value = 0;
		String s = "";

		boolean finished = false;

		while (!finished) {
			try {
				value = System.in.read();
				if (value < 0 || (char) value == '\n') {
					finished = true;
				} else if ((char) value != '\r') {
					s += (char) value;

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return s;
	}

	public static void pause() {
		readStringInput();	
	}
	
}
