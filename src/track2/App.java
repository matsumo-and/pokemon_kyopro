package track2;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        String[] lines = //getStdin();
        {
            "1",
            "4",
            "1 1 1 1",
        };

        final int step = Integer.parseInt(lines[0]);
        final int days = Integer.parseInt(lines[1]);

        //tree List
        final String[] treeStrList = lines[2].split(" ");
        final ArrayList<Integer> treeList = new ArrayList<>();
        for (String s : treeStrList) {
            treeList.add(Integer.parseInt(s));
        }

        //List of found trees
        final ArrayList<Integer> foundTreeList = new ArrayList<>();

        switch (step){
            case 1:
                int fruitNum = 0;
                for(int currentDay = 0; currentDay < days; currentDay++){
                    if(foundTreeList.size() == 0){
                        final int maxTreeNum = Collections.max(treeList);
                        final int maxTreeIndex = treeList.indexOf(maxTreeNum);

                        foundTreeList.add(maxTreeNum);
                        treeList.remove(maxTreeIndex);
                    }else{

                    }
                }
                break;

            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    private static String[] getStdin() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines.toArray(new String[lines.size()]);
    }
}
