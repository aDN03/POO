package src.View;

import java.util.Collections;
import java.util.List;

public class View {

    public static void header(List<String> strings) {
        int width = 150;
        int groupSize = 3;
    

        for (int i = 0; i < strings.size(); i += groupSize) {
            int endIndex = Math.min(i + groupSize, strings.size());
            List<String> group = strings.subList(i, endIndex);

            int groupWidth = group.stream().mapToInt(String::length).sum() + (group.size() - 1) * 2;
            int padding = Math.max(0,(width - groupWidth) / 2);

            String first = "|" + String.join("", Collections.nCopies(padding, " ")) + String.join("  ", group);
            System.out.println(first + String.join("", Collections.nCopies(Math.max(0, width-first.length()-1), " ")) + "|");
        }  
    
        System.out.println(String.join("", Collections.nCopies(width, "_")));
        System.out.print(">> ");
    }

    public static void pageName(String input) {
        int width = 150;

        int stringWidth = input.length() + 2;
        int padding = (width - stringWidth) / 2;

        System.out.println();
        System.out.println("+" + String.join("", Collections.nCopies(width - 2 , "-")) + "+");
        String first = "|" + String.join("", Collections.nCopies(padding , " ")) + "\"" + input + "\"";
        System.out.println(first + String.join("", Collections.nCopies(Math.max(0, width-first.length()-1) , " ")) + "|");
        first = "|" + String.join("", Collections.nCopies(padding , " "));
        System.out.println(first +  String.join("", Collections.nCopies((Math.max(0, width-first.length()-1)) , " ")) + "|");
        System.out.println("+" + String.join("", Collections.nCopies(width - 2 , "-")) + "+");
    }

}
