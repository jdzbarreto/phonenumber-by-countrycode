package com.growin.challenge.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhoneNumbersRules {

    HashMap<String, Integer> finalList = new HashMap<String, Integer>();

    public List phoneIsValid(List<String> list) {

        List<String> newList = new ArrayList<>();
        list.stream()
                .filter(item -> validNumber(item))
                .forEach(item -> {
                    newList.add(transformNumberToPortugal(item));
                });
        return newList;
    }

    public String transformNumberToPortugal(String number) {
        if (isShortNumber(number)) {
            return "+351 ".concat(number);
        }
        return number;
    }

    public boolean isShortNumber(String phoneNumber) {
        return (phoneNumber.length() >= 4 && phoneNumber.length() <= 6 && !phoneNumber.startsWith("0"));
    }

    public String countPhoneNumbersCountry(List<String> phones, Map<String, String> countries) {
        List<String> validNumbers = (List<String>) phoneIsValid(phones).stream().distinct().collect(Collectors.toList());

        for (String phoneNumber : validNumbers) {
            String ddiCode = "";
            if (phoneNumber.startsWith("+")) {
                String[] split = phoneNumber.split(" ");
                ddiCode = split[0].replace("+", "");
            }
            if (phoneNumber.startsWith("00")) {
                ddiCode = phoneNumber.substring(2, 3);
            }

            for (Map.Entry<String, String> map : countries.entrySet()) {
                if (map.getValue().equals(ddiCode)) {
                    if (finalList.containsKey(map.getKey())) {
                        finalList.put(map.getKey(), finalList.get(map.getKey()) + 1);
                    } else {
                        finalList.put(map.getKey(), 1);
                    }
                    break;
                }
            }
        }
        return sortByValue(finalList).toString();
    }

    public boolean validNumber(String phoneNumber) {
        String newNumber = phoneNumber.replaceAll("\\s+","") ;
        return ((newNumber.length() >= 4 && newNumber.length() <= 6 && !newNumber.startsWith("0") && !newNumber.startsWith("+"))
                || (newNumber.length() >= 9 && newNumber.length() <= 14));
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm){
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list,(i1, i2) -> i2.getValue().compareTo(i1.getValue()));

        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
