package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyThread extends Thread {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    @Override
    public void run() {
        String path = generateRoute("RLRFR", 100);
        Pattern pattern = Pattern.compile("RR+[^R]");
        Matcher matcher = pattern.matcher(path);
        synchronized (sizeToFreq) {
            while (matcher.find()) {
                if (sizeToFreq.containsKey((matcher.end() - 1) - matcher.start())) {
                    sizeToFreq.put((matcher.end() - 1) - matcher.start(),
                            sizeToFreq.get((matcher.end() - 1) - matcher.start()) + 1);
                } else {
                    sizeToFreq.put((matcher.end() - 1) - matcher.start(), 1);
                }
            }
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static void print() {
        Map.Entry<Integer, Integer> maxEntry =
                Collections.max(sizeToFreq.entrySet(), Map.Entry.comparingByValue());

        System.out.println("Самое частое повторение - " + maxEntry.getKey() + " Встретилось раз - "
                + sizeToFreq.get(maxEntry.getKey()));
        System.out.println("Все результаты: " + sizeToFreq.entrySet());

    }
}
