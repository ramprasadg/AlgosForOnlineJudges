package com.ramprasadg.interview;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Booking2 {

    static class Pair {
        public int id;
        public int count;

        public Pair(int id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String keysLine = s.nextLine();

        String[] rawKeys = keysLine.split(" ");
        Set<String> keys = new HashSet<String>();

        for (String raw : rawKeys) {
            keys.add(raw.trim());
        }

        HashMap<Integer, Integer> score = new HashMap<Integer, Integer>();
        int maxScore = 0;

        int hotels = Integer.parseInt(s.nextLine());

        for (int i = 0; i < hotels; i++) {
            int id = 0;
            if (s.hasNextLine()) {
                id = Integer.parseInt(s.nextLine());
            } else {
                break;
            }
            
            String review = null;
            if (s.hasNextLine()) {
                review = s.nextLine();
            } else {
                break;
            }

            review = review.replace('.', ' ');
            review = review.replace(',', ' ');

            String words[] = review.split(" ");
            for (String word : words) {
                if (keys.contains(word.trim())) {
                    Integer count = score.get(id);
                    if (count == null) {
                        count = 0;
                    }
                    maxScore = Math.max(maxScore, count + 1);
                    score.put(id, count + 1);
                }
            }
        }

        List<Pair> output = new ArrayList<Pair>();

        for (Entry<Integer, Integer> e : score.entrySet()) {
            Pair p = new Pair(e.getKey(), e.getValue());
            output.add(p);
        }

        output.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.count == o2.count) {
                    return o1.id - o2.id;
                }
                return o2.count - o1.count;
            }
        });

        System.out.print(output.get(0).id);
        for (int i = 1; i < output.size(); i++) {
            System.out.print(" " + output.get(i).id);
        }
        System.out.println();
    }
}
