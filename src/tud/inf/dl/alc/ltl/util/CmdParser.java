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

package tud.inf.dl.alc.ltl.util;

import java.io.File;

import org.apache.commons.cli.*;

/**
 * CmdParser does the following two task: 
 * 1. Parse Input Command Line Arguments
 * 2. Print Usage Instructions
 * 
 * CmdParser use Apache CLI Parser:
 * 
 * @version
 * @author
 */
public class CmdParser {

	/** Command Line Options */
	private static Options options = null;
	/** Command Line Arguments */
	private static CommandLine cmd = null;

	/** 1st Command Line Options */
	private static final String SET_GENERATION = "gc";
	public static Integer nc_size;
	public static Integer nr_size;
	public static Integer ni_size;

	private static final String OUTPUT_FILE = "o";
	public static File output_file;

	/** 2nd Command Line Options */
	private static final String FORMULA_GENERATION = "gf";
	public static Integer formula_size;
	public static Integer formula_length;
	public static Integer axiom_asser_length;

	private static final String INPUT_FILE = "i";
	public static File input_file;

	/** 3rd Command Line Options */
	private static final String ALC_LTL_FORMULA_GENERATION = "gg";

	private static final String FM_OUTPUT_FILE = "w";
	public static File fm_output_file;


	/** 4rd Command Line Options */
	private static final String ALC_LTL_FORMULA_LOAD = "gl";


	/** 5rd Command Line Options */
	private static final String ALC_LTL_AX_FORMULA_GENERATION = "ga";

	/** Defaults */
	private static final String DEFAULT_OUTPUT_FILE = "/tmp/out.xml";

	private static final String DEFAULT_CONFIG_FILE = "gen_conf.ini";

	private static final String CONFI_FILE = "c";
	public static File config_file;

	static {

		options = new Options();

		/** First Line of arguments */
		options
				.addOption(OptionBuilder
						.withArgName("NC_SIZE, NR_SIZE, NI_SIZE")
						.hasArgs(3)
						.withValueSeparator(',')
						.withDescription(
								"Generate Random Sets: Concept Set(NC), Role Set(NR), Individual Set(NI)")
						.create(SET_GENERATION));
		options.addOption(new Option(OUTPUT_FILE, true,
				"OUTPUT OWL/XML/RDF FILE"));

		/** Second Line of Arguments */
		options.addOption(OptionBuilder.withArgName("SIZE, LENGTH").hasArgs(2)
				.withValueSeparator(',').withDescription(
						"Generate FORMULAS: FORMULAS SIZE, LENGTH OF FORMULA")
				.create(FORMULA_GENERATION));

		options
				.addOption(new Option(INPUT_FILE, true,
						"INPUT OWL/XML/RDF FILE"));

		/** Third Line of Arguments */
		options
				.addOption(OptionBuilder
						.withArgName("SIZE, LENGTH, CONCEPT_LENGTH")
						.hasArgs(3)
						.withValueSeparator(',')
						.withDescription(
								"Generate FORMULAS: FORMULAS SIZE, LENGTH OF FORMULA, CONCEPT ASSERTION LENGTH")
						.create(ALC_LTL_FORMULA_GENERATION));

//		options.addOption(new Option(FM_OUTPUT_FILE, true,
//				"OUTPUT LTL XML FILE"));

		/** Fourth Line of Arguments */
		options
				.addOption(OptionBuilder
						.withArgName("LOADFILE")
						.hasArgs(0)
						.withValueSeparator(',')
						.withDescription(
								"LOAD ALCLTL FORMULAS")
						.create(ALC_LTL_FORMULA_LOAD));

		/*** Fifth Line of Arguments */
		options
				.addOption(OptionBuilder
						.withArgName("SIZE, LENGTH")
						.hasArgs(2)
						.withValueSeparator(',')
						.withDescription(
								"Generate FORMULAS: FORMULAS SIZE, LENGTH OF FORMULA")
						.create(ALC_LTL_AX_FORMULA_GENERATION));


		options.addOption(new Option(FM_OUTPUT_FILE, true,
				"OUTPUT LTL XML FILE"));

		options.addOption(new Option(CONFI_FILE, true,
		"CONFIG FILE <OPERATOR_KEY,FREQUENCY>"));

		options.addOption("u", false, "Print usage information");

	}

	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	//
	// // = new CmdLineParser();
	// CmdParser.loadArgs(args);
	// }

	/**
	 * Validate and set command line arguments. Exit after printing usage if
	 * anything is astray
	 * 
	 * @param args
	 *            String[] args as featured in public static void main()
	 */
	public static void loadArgs(String[] args) {

		CommandLineParser parser = new GnuParser();

		try {

			cmd = parser.parse(options, args);

		} catch (ParseException e) {
			System.err.println(e.getMessage());
			printUsage();
			// e.printStackTrace();
			System.exit(1);
		}

		/** Print Program Usage */
		if (cmd.hasOption("u")) {
			printUsage();
		}

		/** First Group of Commands Parsing */
		else if (cmd.hasOption("gc")) {
			try {
				String[] gc_arg = cmd.getOptionValues("gc");
				nc_size = new Integer(gc_arg[0]);
				nr_size = new Integer(gc_arg[1]);
				ni_size = new Integer(gc_arg[2]);
				System.out.println("Executing ALC Set Generator...");
				System.out.println("Concept set (Nc) size: " + nc_size);
				System.out.println("Role set (Nr) size: " + nr_size);
				System.out.println("Individual set (Ni) size: " + ni_size);

				if (cmd.hasOption("o")) {
					output_file = new File(cmd.getOptionValue("o"));
					System.out.println("Output File: "
							+ output_file.getAbsolutePath());
				} else {
					/** DEFAULT SETTING */
					output_file = new File(DEFAULT_OUTPUT_FILE);
					System.out.println("Default Output File: "
							+ output_file.getAbsolutePath());
				}
			} catch (Exception exp) {
				printUsage();
				System.exit(-1);
			}
		}

		/** Second Group of Commands Parsing */
		else if (cmd.hasOption("gf")) {
			try {
				String[] gf_arg = cmd.getOptionValues("gf");
				formula_size = new Integer(gf_arg[0]);
				formula_length = new Integer(gf_arg[1]);

				System.out.println("Executing ALC Axiom Generator...");
				if (cmd.hasOption("i")) {
					input_file = new File(cmd.getOptionValue("i"));
					if (input_file.exists()) {
						System.out.println("Input Ontology File: "
								+ input_file.getAbsolutePath());
					} else {
						System.err
								.println("Input Ontology file does not exist");
						printUsage();
						System.exit(-2);
					}
				} else {
					System.err.println("Input Ontology file does not exist");
					printUsage();
					System.exit(-2);
				}

				if (cmd.hasOption("w")) {
					fm_output_file = new File(cmd.getOptionValue("w"));
					System.out.println("Output LTL OWL Ontology File: "
							+ fm_output_file.getAbsolutePath());

				} else {
					/** DEFAULT SETTING */
					fm_output_file = new File(DEFAULT_OUTPUT_FILE);
					System.out.println("Default Ouputput Ontology File: "
							+ fm_output_file.getAbsolutePath());
				}

				if (cmd.hasOption("c")) {
					config_file = new File(cmd.getOptionValue("c"));
					System.out.println("Configuration File: "
							+ config_file.getAbsolutePath());

				} else {
					/** DEFAULT SETTING */
					config_file = new File(DEFAULT_CONFIG_FILE);
					System.out.println("Default Configuration File: "
							+ config_file.getAbsolutePath());
				}


				System.out.println("Number of Axioms: " + formula_size);
				System.out.println("Length of Concept Axiom:" + formula_length);

			} catch (Exception exp) {
				printUsage();
				System.exit(-1);
			}

		}
		/** Third Group of Commands Parsing */
		else if (cmd.hasOption("gg")) {
			try {
				String[] gf_arg = cmd.getOptionValues("gg");
		
				if(gf_arg.length < 3) {
				
					System.err.println("missing argument(s) for command -gg");
					printUsage();
					System.exit(-2);
					
				}
				
				formula_size = new Integer(gf_arg[0]);
				formula_length = new Integer(gf_arg[1]);
				axiom_asser_length = new Integer(gf_arg[2]);

				System.out.println("Executing ALC LTL Axiom Generator...");

				if (cmd.hasOption("i")) {
					input_file = new File(cmd.getOptionValue("i"));
					if (input_file.exists()) {
						System.out.println("Input Ontology File: "
								+ input_file.getAbsolutePath());
					} else {
						System.err
								.println("Input Ontology file does not exist");
						printUsage();
						System.exit(-2);
					}
				} else {
					System.err
					.println("Input Ontology file does not exist");
					printUsage();
					System.exit(-2);
				}

				if (cmd.hasOption("w")) {
					fm_output_file = new File(cmd.getOptionValue("w"));
					System.out.println("Output LTL OWL Ontology File: "
							+ fm_output_file.getAbsolutePath());

				} else {
					/** DEFAULT SETTING */
					fm_output_file = new File(DEFAULT_OUTPUT_FILE);
					System.out.println("Default Input Ontology File: "
							+ fm_output_file.getAbsolutePath());
				}

				if (cmd.hasOption("c")) {
					config_file = new File(cmd.getOptionValue("c"));
					System.out.println("Configuration File: "
							+ config_file.getAbsolutePath());

				} else {
					/** DEFAULT SETTING */
					config_file = new File(DEFAULT_CONFIG_FILE);
					System.out.println("Default Configuration File: "
							+ config_file);
				}

				System.out.println("Number of Formulas: " + formula_size);
				System.out.println("Length of Formula:" + formula_length);
				System.out.println("Length of Axiom:" + axiom_asser_length);

			} catch (Exception exp) {
				exp.printStackTrace();
				printUsage();
				System.exit(-1);
			}

		}
		/** 4th Option **/
		else if (cmd.hasOption("gl")) {
//			try {
//				String[] gl_arg = cmd.getOptionValues("gl");
//		
//				if(gl_arg.length < 1) {
//				
//					System.err.println("missing argument(s) for command -gl");
//					printUsage();
//					System.exit(-2);
//					
//				}
				
				System.out.println("Loading ALC LTL Axiom(s)...");

				if (cmd.hasOption("i")) {
					fm_output_file = new File(cmd.getOptionValue("i"));
					if (fm_output_file.exists()) {
						System.out.println("Input Ontology File: "
								+ fm_output_file.getAbsolutePath());
					} else {
						System.err
								.println("Input Formula Ontology file does not exist");
						printUsage();
						System.exit(-2);
					}
				} else {
					System.err
					.println("Input Formula Ontology file does not exist");
					printUsage();
					System.exit(-2);
				}


//			} catch (Exception exp) {
//				exp.printStackTrace();
//				printUsage();
//				System.exit(-1);
//			}
		}
		/** 5th Set of Command */
		else if (cmd.hasOption("ga")) {
			try {
				String[] gf_arg = cmd.getOptionValues("ga");
		
				if(gf_arg.length < 2) {
				
					System.err.println("missing argument(s) for command -ga");
					printUsage();
					System.exit(-2);
					
				}
				
				formula_size = new Integer(gf_arg[0]);
				formula_length = new Integer(gf_arg[1]);
//				axiom_asser_length = new Integer(gf_arg[2]);

				if (cmd.hasOption("i")) {
					input_file = new File(cmd.getOptionValue("i"));
					if (input_file.exists()) {
						System.out.println("Input Ontology File: "
								+ input_file.getAbsolutePath());
					} else {
						System.err
								.println("Input Ontology file does not exist");
						printUsage();
						System.exit(-2);
					}
				} else {
					System.err
					.println("Input Ontology file does not exist");
					printUsage();
					System.exit(-2);
				}

				if (cmd.hasOption("w")) {
					fm_output_file = new File(cmd.getOptionValue("w"));
					System.out.println("Output LTL OWL Ontology File: "
							+ fm_output_file.getAbsolutePath());

				} else {
					/** DEFAULT SETTING */
					fm_output_file = new File(DEFAULT_OUTPUT_FILE);
					System.out.println("Default Input Ontology File: "
							+ fm_output_file.getAbsolutePath());
				}

				if (cmd.hasOption("c")) {
					config_file = new File(cmd.getOptionValue("c"));
					System.out.println("Configuration File: "
							+ config_file.getAbsolutePath());

				} else {
					/** DEFAULT SETTING */
					config_file = new File(DEFAULT_CONFIG_FILE);
					System.out.println("Default Configuration File: "
							+ config_file);
				}

				System.out.println("Number of Formulas: " + formula_size);
				System.out.println("Length of Formula:" + formula_length);

			} catch (Exception exp) {
				exp.printStackTrace();
				printUsage();
				System.exit(-1);
			}

		}

	}

	public static boolean isGC() {
		return cmd.hasOption("gc");
	}

	public static boolean isGG() {
		return cmd.hasOption("gg");
	}

	public static boolean isGF() {
		return cmd.hasOption("gf");
	}

	public static boolean isGL() {
		return cmd.hasOption("gl");
	}

	public static boolean isGA() {
		return cmd.hasOption("ga");
	}

	public static boolean isStore() {
		return cmd.hasOption("w");
	}

	public static void printUsage() {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();		
		formatter.printHelp("ALC Generator", options);
	}
	
	public static void licenceMsg() {
		System.out.println("*******************************************************************************");
		System.out.println("* ALC-LTL Formula Generator Copyright (C) 2011                                *");
		System.out.println("* This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.  *");
		System.out.println("* This is free software, and you are welcome to redistribute it under certain *");
		System.out.println("* conditions; type `show c' for details.                                      *");
		System.out.println("******************************************************************************");
	}

}
