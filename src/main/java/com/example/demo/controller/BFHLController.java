package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BFHLController {

    @PostMapping("/bfhl")
    public Map<String, Object> processData(@RequestBody Map<String, Object> requestData) {
        List<String> data = (List<String>) requestData.get("data");

        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();

        int sum = 0;
        StringBuilder concatString = new StringBuilder();

        for (String item : data) {
            if (item.matches("\\d+")) {
                // Numbers
                int num = Integer.parseInt(item);
                sum += num;
                if (num % 2 == 0) evenNumbers.add(item);
                else oddNumbers.add(item);
            } else if (item.matches("[a-zA-Z]+")) {
                // Alphabets
                alphabets.add(item.toUpperCase());
                concatString.insert(0, item); // reverse order
            } else {
                // Special characters
                specialChars.add(item);
            }
        }

        // Alternating caps for concatenated string
        StringBuilder altCaps = new StringBuilder();
        boolean upper = true;
        for (char c : concatString.toString().toCharArray()) {
            altCaps.append(upper ? Character.toUpperCase(c) : Character.toLowerCase(c));
            upper = !upper;
        }

        // Use LinkedHashMap to preserve key order exactly as required
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("is_success", true);
        response.put("user_id", "kriti_tiwari_01012003"); // replace with your name & DOB
        response.put("email", "kriti.tiwari0810@gmail.com");
        response.put("roll_number", "22BCS00000");
        response.put("odd_numbers", oddNumbers);
        response.put("even_numbers", evenNumbers);
        response.put("alphabets", alphabets);
        response.put("special_characters", specialChars);
        response.put("sum", String.valueOf(sum));
        response.put("concat_string", altCaps.toString());

        return response;
    }
}
