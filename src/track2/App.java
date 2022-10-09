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
                //与えられた日数で起こりうる収穫数の場合のかずを格納する
                final ArrayList<Long> yieldFruitsList = new ArrayList<>();
                for(int currentDay = 0; currentDay < days; currentDay++){

                    //最も多い木を見つける
                    final int maxTreeNum = Collections.max(treeList);
                    final int maxTreeIndex = treeList.indexOf(maxTreeNum);

                    //多い木を見つけてTreeListから削除する
                    foundTreeList.add(maxTreeNum);
                    treeList.remove(maxTreeIndex);

                    //見つけた木の合計 * 残り日数を収穫数とする
                    int sumFruits = 0;
                    for(int yieldNum : foundTreeList){
                        sumFruits += yieldNum;
                    }
                    yieldFruitsList.add((long) sumFruits * (days - foundTreeList.size()));
                }

                //起こりうる事象の中で最も獲得数の多い木のみの数を出力する
                System.out.println(Collections.max(yieldFruitsList));
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
