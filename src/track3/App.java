package track3;
import java.util.*;

public class App {

    public static void main(String[] args) {

        //A => town A
        //B => town B
        //N => trainer toward North
        //S => trainer toward South
        //E => trainer toward East
        //W => trainer toward West
        //# => wall
        //. => road

        String[] lines = //getStdin();
        {
                "1",
                "2 10",
                "NSESEWSNNE",
                "A........B",
        };

        final int step = Integer.parseInt(lines[0]);

        final int height = Integer.parseInt(lines[1].split(" ")[0]);
        final int width = Integer.parseInt(lines[1].split(" ")[1]);

        final List<List<String>> lanes = new ArrayList<>();
        for(int h = 0; h < height; h ++){
            lanes.add(Arrays.asList(lines[2 + h].split("")));
        }

        switch (step){
            case 1:
                //Step1

                //一列なので歩数はwidth-1, トレーナーの数はSの個数分
                final List<String> trainers = lanes.get(0);
                int numToFight = 0;
                for(String trainer : trainers){
                    numToFight = Objects.equals(trainer, "S") ? numToFight + 1 : numToFight;
                }

                System.out.print(width -1);
                System.out.print(" ");
                System.out.println(numToFight);

            case 2:
            case 3:
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
