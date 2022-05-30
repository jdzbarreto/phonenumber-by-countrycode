package com.growin.challenge;
import com.growin.challenge.service.DataProcess;
import com.growin.challenge.service.PhoneNumbersRules;
import java.io.*;
public class Challenge {

	public static void main(String args[]) throws FileNotFoundException {

		DataProcess data = new DataProcess();
		PhoneNumbersRules rules = new PhoneNumbersRules();

		String fileName = "./coutryCodes.txt";
		BufferedReader in = data.getCountryFile(fileName);

		if (args.length == 0) {
			System.out.println("Invalid Usage");
			System.out.println("Usage: java -jar <filename> input.txt ");
			return;
		}
		File inputFile = new File(args[0]);
		try {
			BufferedReader input = new BufferedReader(new FileReader(args[0]));
			if (inputFile.exists() && inputFile.isFile()) {
				String output = rules.countPhoneNumbersCountry(data.convertInputPhoneNumberToList(input), data.convertCountryListToHashMap(in));
				data.fileOutput(output);
				System.out.println(output);
			}
		} catch (IOException e) {
			if (inputFile.exists() && inputFile.isDirectory()) {
				System.out.println("Name: " + inputFile + " is a directory");
			}
		}
	}
}