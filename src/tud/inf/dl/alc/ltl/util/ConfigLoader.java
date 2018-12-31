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

import java.io.*;
import java.util.*;

import org.semanticweb.owlapi.model.ClassExpressionType;

import tud.inf.dl.alc.ltl.util.Entry.AxiomType;
import tud.inf.dl.alc.ltl.util.Entry.FormulaType;


public class ConfigLoader {

//-----------------------------------------------------------------------------------//

  private static final String CONJUNCTION = "CONJUNCTION";
  private static final String DISJUNCTION = "DISJUNCTION";
  private static final String EXIST_REST  = "EXIST_REST";
  private static final String VALUE_REST  = "VALUE_REST";
  private static final String COMPLEMENT  = "COMPLEMENT";


  private static final String CLASS_ASSERTION = "CLASS_ASSERTION";
  private static final String OBJECT_PROPERTY_ASSERTION = "OBJECT_PROPERTY_ASSERTION";
  private static final String NEGATIVE_OBJECT_PROPERTY_ASSERTION  = "NEGATIVE_OBJECT_PROPERTY_ASSERTION";
  private static final String SUBCLASS_OF  = "SUBCLASS_OF";

  private static final String AND = "AND";
  private static final String OR = "OR";
  private static final String NEG  = "NEG";
  private static final String NEXT  = "NEXT";
  private static final String UNTIL  = "UNTIL";

  public static Vector<Entry<ClassExpressionType>> expr_table = new Vector<Entry<ClassExpressionType>>();
  public static Vector<Entry<AxiomType>> axiom_table = new Vector<Entry<AxiomType>>();
  public static Vector<Entry<FormulaType>> formula_table = new Vector<Entry<FormulaType>>();

  
//  //Private variable to contains This settings.
//  private static String confFilePath;


  /*****************************************************************************
   *
   * @param logFilePath String
   * @param confFilePath String
   * @throws Exception
   */
  public static void loadConfig(File input_file)
      throws FileNotFoundException, IOException, NumberFormatException,
      Exception {
	if (loadConf(input_file)) {
        System.out.println("Loading Solver Configuration File: " + input_file.getAbsolutePath());
	} else {
      throw new Exception ("Problem while loading File");
	}

  } // end GGPlayerConf(), Constructor

  /*****************************************************************************
   * The specified configuration file is loaded to use GGPlayer service
   * @param confPath String
   * @throws FileNotFoundException
   * @throws IOException
   * @return boolean
   */
  public static boolean loadConf(File confFile)
      throws FileNotFoundException, IOException, NumberFormatException {

    boolean loaded = true;
//    File confFile = new File(confPath);

    if (confFile.isFile() && confFile.exists()) {

      InputStream is = new FileInputStream(confFile);
      Properties prop = new Properties();

      prop.load(is);

      parseConf(prop);
      
      prop.clear();
      is.close();

      return loaded;

    } else {
      //Configuration File Don't Exist. Return Error.
      return false;
    }
  } // end loadConf(String, String)

  /*****************************************************************************
   * Set the Required Filed Values Specified in Configuration File.
   * @return boolean
   */
  private static void parseConf(Properties prop) {

    Float freq_value;

    freq_value = Float.parseFloat(prop.getProperty(COMPLEMENT));
    expr_table.add(new Entry<ClassExpressionType>(ClassExpressionType.OBJECT_COMPLEMENT_OF, freq_value));
    
    freq_value = Float.parseFloat(prop.getProperty(DISJUNCTION));    
    expr_table.add(new Entry<ClassExpressionType>(ClassExpressionType.OBJECT_UNION_OF, freq_value));

    freq_value = Float.parseFloat(prop.getProperty(CONJUNCTION));    
    expr_table.add(new Entry<ClassExpressionType>(ClassExpressionType.OBJECT_INTERSECTION_OF, freq_value));

    freq_value = Float.parseFloat(prop.getProperty(EXIST_REST));    
    expr_table.add(new Entry<ClassExpressionType>(ClassExpressionType.OBJECT_SOME_VALUES_FROM, freq_value));
    
    freq_value = Float.parseFloat(prop.getProperty(VALUE_REST));    
    expr_table.add(new Entry<ClassExpressionType>(ClassExpressionType.OBJECT_ALL_VALUES_FROM, freq_value));
    

    
    freq_value = Float.parseFloat(prop.getProperty(CLASS_ASSERTION));
    axiom_table.add(new Entry<AxiomType>(AxiomType.CLASS_ASSERTION, freq_value));

    freq_value = Float.parseFloat(prop.getProperty(OBJECT_PROPERTY_ASSERTION));
    axiom_table.add(new Entry<AxiomType>(AxiomType.OBJECT_PROPERTY_ASSERTION, freq_value));

    freq_value = Float.parseFloat(prop.getProperty(NEGATIVE_OBJECT_PROPERTY_ASSERTION));
    axiom_table.add(new Entry<AxiomType>(AxiomType.NEGATIVE_OBJECT_PROPERTY_ASSERTION, freq_value));

    freq_value = Float.parseFloat(prop.getProperty(SUBCLASS_OF));
    axiom_table.add(new Entry<AxiomType>(AxiomType.SUBCLASS_OF, freq_value));


    freq_value = Float.parseFloat(prop.getProperty(AND));
    formula_table.add(new Entry<FormulaType>(FormulaType.CONJECTION, freq_value));

    freq_value = Float.parseFloat(prop.getProperty(OR));
    formula_table.add(new Entry<FormulaType>(FormulaType.DISJUNCTION, freq_value));
    
    freq_value = Float.parseFloat(prop.getProperty(UNTIL));
    formula_table.add(new Entry<FormulaType>(FormulaType.UNTIL, freq_value));
    
    freq_value = Float.parseFloat(prop.getProperty(NEXT));
    formula_table.add(new Entry<FormulaType>(FormulaType.NEXT, freq_value));

    freq_value = Float.parseFloat(prop.getProperty(NEG));
    formula_table.add(new Entry<FormulaType>(FormulaType.NEGATION, freq_value));

  } // end parseConf(Properties)

} // end of class GGPlayerConf.java
