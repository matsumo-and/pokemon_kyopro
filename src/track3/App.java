package track3;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        String[] lines = getStdin();
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
