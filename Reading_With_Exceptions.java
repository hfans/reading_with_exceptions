package reading_with_exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Reading_With_Exceptions {
	void process(String inputFilename) {
		// Here is where your work goes ... Steps that you will need to do:
		// 1.) Create a Scanner from the inputFilename. Catch exceptions from errors.
		Scanner sc = null;
		PrintStream print = null;
		String fileout = "";
		int counter = -1;
		try {
			FileInputStream fs = new FileInputStream(inputFilename);
			sc = new Scanner(fs);
		} catch (Exception e) {
			System.err.println("File error: " + e);
		}
		// 2.) Read the first String from the file and use it to create a PrintStream
		// catching appropriate exceptions
		try {
			fileout = sc.next();
			print = new PrintStream(fileout);
		} catch (Exception e) {
			System.err.println("Print stream error: " + e);
		}
		// 3.) Using hasNextInt and nextInt, carefully read the count integer.
		// I recommend -1 for a count value if it is bad to indicate reading ALL
		try {
			if (sc.hasNextInt())
				counter = sc.nextInt();
			else
				throw new Exception("Does not have a number next ");
		} catch (Exception e) {
			System.err.println(" error: " + e);
		}

		// 4.) Use copyNumbers method described below to complete the job
		copyNumbers(sc, print, counter);

		// 5.) Close Scanner and PrintStream objects
		sc.close();
		print.close();

		// 6.) Call printToScreen to copy the output file to the screen
		printToScreen(fileout);
	}

	// The following routine is called to complete the job of copying integers to
	// the output file:
	// scan - a Scanner object to copy integers from
	// ps - A PrintStream to write the integers to
	// numIntsToRead - number of integers to read. A value of -1 ==> read all
	// integers

	void copyNumbers(Scanner scan, PrintStream ps, int numIntsToRead) {

		// hasNext() can be used to see if the scan object still has data
		if (scan.hasNext()) {
			// Note that hasNextInt() can be used to see if an integer is present
			if (scan.hasNextInt())
				// nextInt() will read an integer
				scan.nextInt();
			// next() can be used to skip over bad integers
			else
				scan.next();
		}
	}

	public static void main(String[] args) {
		Reading_With_Exceptions rwe = new Reading_With_Exceptions();
		for (int i = 0; i < args.length; i++) {
			System.out.println("\n\n=========== Processing " + args[i] + " ==========\n");
			rwe.process(args[i]);
		}
	}

	// For the last step, we Copy the contents of the file to the screen

	private void printToScreen(String filename) {
		Scanner scan = null;
		try {
			FileInputStream fis = new FileInputStream(filename);
			scan = new Scanner(fis);
			while (scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("printToScreen: can't open: " + filename);
		} finally {
			if (scan != null)
				scan.close();
		}
	}// end of printToScreen
} // end of class
