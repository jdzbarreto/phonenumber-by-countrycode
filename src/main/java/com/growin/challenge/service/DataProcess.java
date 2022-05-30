package com.growin.challenge.service;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataProcess {

    public BufferedReader getCountryFile(String fileName) throws FileNotFoundException {

        BufferedReader in;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            in = new BufferedReader(new InputStreamReader(fis));
        } catch (IOException e) {
            throw new IllegalArgumentException("Country file not found! " + fileName);
        }
        return in;
    }

    public Map<String, String> convertCountryListToHashMap(BufferedReader is) throws IOException {

        Map<String, String> countries = new HashMap<>();
            String line;
            while ((line = is.readLine()) != null) {
                String[] parts = line.split("-");
                countries.put(parts[0],parts[1]);
            }
        return countries;
    }
    public ArrayList<String> convertInputPhoneNumberToList(BufferedReader in) throws IOException {

        ArrayList<String> phoneNumberList = new ArrayList<String>();
            String line;
            while ((line = in.readLine()) != null) {
                if (line.trim().length() != 0) {
                    phoneNumberList.add(line);
                }
            }
        return phoneNumberList;
    }

    public void fileOutput(String phoneList) {

        Path path = Paths.get("./countPhoneListOutput.txt");
        byte[] arr = phoneList.getBytes();

        try {
            Files.write(path, arr);
        }

        catch (IOException ex) {
            System.out.print("Invalid Path to save output file");
        }
    }
}

