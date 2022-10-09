package track2;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        String[] lines = //getStdin();
        {
            "2",
            "4",
            "10 10 10 10",
        };

        final int step = Integer.parseInt(lines[0]);
        final int days = Integer.parseInt(lines[1]);

        //tree List
        final String[] treeStrList = lines[2].split(" ");
        final ArrayList<Long> treeList = new ArrayList<>();
        for (String s : treeStrList) {
            treeList.add(Long.parseLong(s));
        }

        //List of found trees
        final ArrayList<Long> foundTreeList = new ArrayList<>();

        //与えられた日数で起こりうる収穫数の場合のかずを格納する
        final ArrayList<Long> yieldFruitsList = new ArrayList<>();
        for(int currentDay = 0; currentDay < days; currentDay++){

            //最も多い木を見つける
            final long maxTreeNum = Collections.max(treeList);

            //多い木を見つけてTreeListから削除する
            foundTreeList.add(maxTreeNum);
            treeList.remove(maxTreeNum);

            //見つけた木の合計 * 残り日数を収穫数とする
            long sumFruits = 0;
            for(long yieldNum : foundTreeList){
                sumFruits += yieldNum;
            }
            yieldFruitsList.add(sumFruits * (days - foundTreeList.size()));
        }

        //起こりうる事象の中で最も獲得数の多い木のみの数を出力する
        System.out.println(Collections.max(yieldFruitsList));
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
