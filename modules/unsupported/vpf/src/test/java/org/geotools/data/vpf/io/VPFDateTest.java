/*
 * File is generated by 'Unit Tests Generator' developed under
 * 'Web Test Tools' project at http://sf.net/projects/wttools/
 * Copyright (C) 2001 "Artur Hefczyc" <kobit@users.sourceforge.net>
 * to all 'Web Test Tools' subprojects.
 *
 * No rigths to files and no responsibility for code generated
 * by this tool are belonged to author of 'unittestsgen' utility.
 *
 */
package org.geotools.data.vpf.io;

import java.util.Calendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * File <code>VPFDateTest.java</code> is automaticaly generated by
 * 'unittestsgen' application. Code generator is created for java
 * sources and for 'junit' package by "Artur Hefczyc"
 * <kobit@users.sourceforge.net><br/>
 * You should fulfil test methods with proper code for testing
 * purpose. All methods where you should put your code are below and
 * their names starts with 'test'.<br/>
 * You can run unit tests in many ways, however prefered are:
 * <ul>
 *   <li>Run tests for one class only, for example for this class you
 *       can run tests with command:
 *     <pre>
 *       java -cp "jar/thisjarfile.jar;lib/junit.jar" org.geotools.vpf.VPFDateTest
 *     </pre>
 *   </li>
 *   <li>Run tests for all classes in one command call. Code generator
 *       creates also <code>TestAll.class</code> which runs all
 *       available tests:
 *     <pre>
 *       java -cp "jar/thisjarfile.jar;lib/junit.jar" TestAll
 *     </pre>
 *   </li>
 *   <li>But the most prefered way is to run all tests from
 *     <em>Ant</em> just after compilation process finished.<br/>
 *     To do it. You need:
 *     <ol>
 *       <li>Ant package from
 *         <a href="http://jakarta.apache.org/">Ant</a>
 *       </li>
 *       <li>JUnit package from
 *         <a href="http://www.junit.org/">JUnit</a>
 *       </li>
 *       <li>Put some code in your <code>build.xml</code> file
 *         to tell Ant how to test your package. Sample code for
 *         Ant's <code>build.xml</code> you can find in created file:
 *         <code>sample-junit-build.xml</code>. And remember to have
 *         <code>junit.jar</code> in CLASSPATH <b>before</b> you run Ant.
 *         To generate reports by ant you must have <code>xalan.jar</code>
 *         in your <code>ANT_HOME/lib/</code> directory.
 *       </li>
 *     </ol>
 *   </li>
 * </ul>
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/unsupported/vpf/src/test/java/org/geotools/data/vpf/io/VPFDateTest.java $
 */
public class VPFDateTest extends TestCase
{
  /**
   * Instance of tested class.
   */
  protected VPFDate varVPFDate;

  /**
   * Public constructor for creating testing class.
   */
  public VPFDateTest(String name) {
    super(name);
  } // end of VPFDateTest(String name)
  /**
   * This main method is used for run tests for this class only
   * from command line.
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  } // end of main(Stringp[] args)

  protected String date = "20030129161055.00000";

  /**
   * This method is called every time before particular test execution.
   * It creates new instance of tested class and it can perform some more
   * actions which are necessary for performs tests.
   */
  protected void setUp()
  {
    byte[] bytes = new byte[date.length()];
    for (int i = 0; i < bytes.length; i++)
    {
      bytes[i] = (byte)date.charAt(i);
    } // end of for (int i = 0; i < bytes.length; i++)
    varVPFDate = new VPFDate(bytes);
  }
  /**
   * Returns all tests which should be performed for testing class.
   * By default it returns only name of testing class. Instance of this
   * is then created with its constructor.
   */
  public static Test suite()
  {
    return new TestSuite(VPFDateTest.class);
  } // end of suite()

  /**
   * Method for testing original source method:
   * java.util.Date getDate()
   * from tested class
   */
  public void testGetDate()
  {
	assertNotNull("Check if it is possible to parse date, corretness is "+
				  "checked in getCalendar method", varVPFDate.getDate());
  } // end of testGetDate()

  public void testGetCalendar()
  {
    Calendar cal = varVPFDate.getCalendar();
    assertEquals("Checking year", 2003, cal.get(Calendar.YEAR));
    assertEquals("Checking month (Calendar numvers months from 0)",
				 1, cal.get(Calendar.MONTH)+1);
    assertEquals("Checking day of month", 29, cal.get(Calendar.DAY_OF_MONTH));
    assertEquals("Checking hour", 16, cal.get(Calendar.HOUR_OF_DAY));
    assertEquals("Checking minute", 10, cal.get(Calendar.MINUTE));
    assertEquals("Checking second", 55, cal.get(Calendar.SECOND));
    assertEquals("Checking zone", 0, cal.get(Calendar.ZONE_OFFSET));
  }

  /**
   * Method for testing original source method:
   * java.lang.String toString()
   * from tested class
   */
  public void testToString()
  {
    assertEquals("Conversion to string", varVPFDate.toString(), date);
  } // end of testToString()

} // end of VPFDateTest
