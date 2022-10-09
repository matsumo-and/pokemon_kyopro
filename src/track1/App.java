package track1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class App {

    public static void main(String[] args) {

        //get input
        String[] lines = getStdin();

        int stepNum = Integer.parseInt(lines[0]);
        switch(stepNum){
            case 1:
                stepOne(lines);
                break;
            case 2:
                stepTwo(lines);
                break;
            case 3:
                stepThree(lines);
                break;

            default:
                break;
        }
    }

    ///get user's input
    private static String[] getStdin() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines.toArray(new String[lines.size()]);
    }

    /// Step 1
    private static void stepOne(String[] inputLines){

        //room Number
        int maxRoomNum = Integer.parseInt(inputLines[1]);
        //System.out.println(maxRoomNum);

        //create 散らかり具合リスト
        String[] messStrList = inputLines[2].split(" ");
        ArrayList<Integer> messList = new ArrayList<>();
        for(String elm : messStrList){
            messList.add(Integer.parseInt(elm));
        }
        //System.out.println(messList);

        //部屋ごとの給電時間、家電効率の二次元リストを三次元リストに格納
        ArrayList<ArrayList<ArrayList<Integer>>> machinePropsList = new ArrayList<>();
        int nextRow = 0;
        for(int room = 0; room < maxRoomNum; room++){

            //create 給電時間、家電効率の二次元リスト
            int machineNum = Integer.parseInt(inputLines[3 + nextRow]);
            ArrayList<ArrayList<Integer>> machinePropsPerRoom = new ArrayList<>();

            //最初の二次元リストは４列目に出現、以降は家電数+1だけずれる
            for(int row = 4 + nextRow; row < 4 + nextRow + machineNum; row++){
                String[] machineStrProps = inputLines[row].split(" ");
                ArrayList<Integer> machineProps = new ArrayList<>();
                machineProps.add(Integer.parseInt(machineStrProps[0]));
                machineProps.add(Integer.parseInt(machineStrProps[1]));

                machinePropsPerRoom.add(machineProps);
            }
            machinePropsList.add(machinePropsPerRoom);

            //次の給電時間、家電効率の二次元リストの列位置を求める
            nextRow += machineNum + 1;
        }
        //System.out.println(machinePropsList);

        //intだとオーバーフローする
        long timeElapsed = 0;
        for(int index = 0; index < maxRoomNum; index++){

            //各家電ごとにかかった時間を保持
            ArrayList<Integer> timeElapsedList = new ArrayList<>();
            for(ArrayList<Integer> machinePropsPerRoom2 : machinePropsList.get(index)){
                //部屋の散らかり具合
                int messNum = messList.get(index);

                //家電の給電時間
                int timeToPrepare = machinePropsPerRoom2.get(0);
                //家電の効率
                int efficiency = machinePropsPerRoom2.get(1);

                int spentMinute = 0;
                do{
                    messNum -= efficiency;

                    efficiency += 1;
                    spentMinute += 1;
                }while(messNum > 0);

                timeElapsedList.add(spentMinute + timeToPrepare);
                //System.out.println("minute :" + spentMinute + timeToPrepare);
            }
            //各部屋でかかる時間の最小値を求めてかかった時間に足し合わせる
            timeElapsed += Collections.min(timeElapsedList);
            //System.out.println("minute :" + Collections.min(timeElapsedList));
        }

        System.out.println(timeElapsed);
    }



    /// Step 2
    private static void stepTwo(String[] inputLines){

        //room Number
        int maxRoomNum = Integer.parseInt(inputLines[1]);
        //System.out.println(maxRoomNum);

        //create 散らかり具合リスト
        String[] messStrList = inputLines[2].split(" ");
        ArrayList<Integer> messList = new ArrayList<>();
        for(String elm : messStrList){
            messList.add(Integer.parseInt(elm));
        }
        //System.out.println(messList);

        //部屋ごとの給電時間、家電効率の二次元リストを三次元リストに格納
        ArrayList<ArrayList<ArrayList<Integer>>> machinePropsList = new ArrayList<>();
        int nextRow = 0;
        for(int room = 0; room < maxRoomNum; room++){

            //create 給電時間、家電効率の二次元リスト
            int machineNum = Integer.parseInt(inputLines[3 + nextRow]);
            ArrayList<ArrayList<Integer>> machinePropsPerRoom = new ArrayList<>();

            //最初の二次元リストは４列目に出現、以降は家電数+1だけずれる
            for(int row = 4 + nextRow; row < 4 + nextRow + machineNum; row++){
                String[] machineStrProps = inputLines[row].split(" ");
                ArrayList<Integer> machineProps = new ArrayList<>();
                machineProps.add(Integer.parseInt(machineStrProps[0]));
                machineProps.add(Integer.parseInt(machineStrProps[1]));

                machinePropsPerRoom.add(machineProps);
            }
            machinePropsList.add(machinePropsPerRoom);

            //次の給電時間、家電効率の二次元リストの列位置を求める
            nextRow += machineNum + 1;
        }
        //System.out.println(machinePropsList);

        //シチュエーション数は最初の家電数の列番号３　＋　家電数の合計　番目に現れる
        int situation = Integer.parseInt(inputLines[3 + nextRow]);

        //最終的な部屋の散らかり具合リストを作成
        ArrayList<Integer> minMessList = new ArrayList<>();
        for (int situationCnt = 0; situationCnt < situation; situationCnt++){
            minMessList.add(Integer.parseInt(inputLines[situationCnt + 4 + nextRow]));
        }

        for(int minMess : minMessList){

            //intだとオーバーフローする
            long timeElapsed = 0;
            for(int index = 0; index < maxRoomNum; index++){

                //各家電ごとにかかった時間を保持
                ArrayList<Integer> timeElapsedList = new ArrayList<>();
                for(ArrayList<Integer> machinePropsPerRoom2 : machinePropsList.get(index)){
                    //部屋の散らかり具合
                    int messNum = messList.get(index);

                    //家電の給電時間
                    int timeToPrepare = machinePropsPerRoom2.get(0);
                    //家電の効率
                    int efficiency = machinePropsPerRoom2.get(1);

                    int spentMinute = 0;
                    if(messNum > minMess){

                        do{
                            messNum -= efficiency;

                            efficiency += 1;
                            spentMinute += 1;
                        }while(messNum > minMess);

                        timeElapsedList.add(spentMinute + timeToPrepare);

                    }else{

                        //汚さが規定値よりも下であれば掃除をしない
                        timeElapsedList.add(0);

                    }
                    //System.out.println("minute :" + spentMinute + timeToPrepare);
                }
                //各部屋でかかる時間の最小値を求めてかかった時間に足し合わせる
                timeElapsed += Collections.min(timeElapsedList);
                //System.out.println("minute :" + Collections.min(timeElapsedList));
            }
            System.out.println(timeElapsed);
        }
    }



    /// Step 3
    private static void stepThree(String[] inputLines){

        //room Number
        int maxRoomNum = Integer.parseInt(inputLines[1]);
        //System.out.println(maxRoomNum);

        //create 散らかり具合リスト
        String[] messStrList = inputLines[2].split(" ");
        ArrayList<Integer> messList = new ArrayList<>();
        for(String elm : messStrList){
            messList.add(Integer.parseInt(elm));
        }
        //System.out.println(messList);

        //部屋ごとの給電時間、家電効率の二次元リストを三次元リストに格納
        ArrayList<ArrayList<ArrayList<Integer>>> machinePropsList = new ArrayList<>();
        int nextRow = 0;
        for(int room = 0; room < maxRoomNum; room++){

            //create 給電時間、家電効率の二次元リスト
            int machineNum = Integer.parseInt(inputLines[3 + nextRow]);
            ArrayList<ArrayList<Integer>> machinePropsPerRoom = new ArrayList<>();

            //最初の二次元リストは４列目に出現、以降は家電数+1だけずれる
            for(int row = 4 + nextRow; row < 4 + nextRow + machineNum; row++){
                String[] machineStrProps = inputLines[row].split(" ");
                ArrayList<Integer> machineProps = new ArrayList<>();
                machineProps.add(Integer.parseInt(machineStrProps[0]));
                machineProps.add(Integer.parseInt(machineStrProps[1]));

                machinePropsPerRoom.add(machineProps);
            }
            machinePropsList.add(machinePropsPerRoom);

            //次の給電時間、家電効率の二次元リストの列位置を求める
            nextRow += machineNum + 1;
        }
        //System.out.println(machinePropsList);

        //シチュエーション数は最初の家電数の列番号３　＋　家電数の合計　番目に現れる
        int situation = Integer.parseInt(inputLines[3 + nextRow]);

        //かけるべき時間の合計値リストを作成
        ArrayList<Integer> minTimeList = new ArrayList<>();
        for (int situationCnt = 0; situationCnt < situation; situationCnt++){
            minTimeList.add(Integer.parseInt(inputLines[situationCnt + 4 + nextRow]));
        }

        //System.out.println(minTimeList);

        //各シチュエーションごと汚さを計算する
        for(int minTime : minTimeList){

            //部屋ごとにかかった時間を保持する
            ArrayList<Integer> timeElapsedList = new ArrayList<>();
            for(int room = 0; room < maxRoomNum; room++){
                timeElapsedList.add(0);
            }

            //かけるべき時間に到達するまで掃除をする
            int timeSum = 0;
            ArrayList<Integer> addEfficientList = new ArrayList<>();
            for(int j = 0; j < maxRoomNum; j++){
                addEfficientList.add(0);
            }

            ArrayList<Integer> currentMessList = new ArrayList<>();
            for(int messCnt = 0; messCnt < messList.size(); messCnt++){
                currentMessList.add(0);
                currentMessList.set(messCnt, messList.get(messCnt));
            }

            do{

                //最も散らかっている部屋の散らかり具合とそのIndexを取得
                int maxMess = Collections.max(currentMessList);
                int maxMessIndex = currentMessList.indexOf(maxMess);

                //最も散らかってる部屋の家電二次元リストから最も効率のいい家電で1分だけ掃除する
                int maxEfficiencyPerMinute = 0;
                int maxEfficiencyPrepare = 0;

                //System.out.println(machinePropsList.get(maxMessIndex));
                for(ArrayList<Integer> list : machinePropsList.get(maxMessIndex)){

                    Boolean isEffective = (minTime - list.get(0)) * list.get(1) + (minTime - list.get(0)) > (minTime - maxEfficiencyPrepare) * maxEfficiencyPerMinute + (minTime - maxEfficiencyPrepare);

                    if(list.get(0) < minTime && isEffective){
                        maxEfficiencyPrepare = list.get(0);
                        maxEfficiencyPerMinute = list.get(1);
                    }
                }

                //かかった時間をセット
                if(timeElapsedList.get(maxMessIndex) == 0){
                    timeElapsedList.set(maxMessIndex, maxEfficiencyPrepare + 1);
                }else{
                    timeElapsedList.set(maxMessIndex, timeElapsedList.get(maxMessIndex) + 1);
                }

                currentMessList.set(maxMessIndex, currentMessList.get(maxMessIndex) - maxEfficiencyPerMinute - addEfficientList.get(maxMessIndex));
                addEfficientList.set(maxMessIndex, addEfficientList.get(maxMessIndex) + 1);

                timeSum = 0;
                for(int time : timeElapsedList){
                    timeSum += time;
                }

            }while(timeSum < minTime);

            System.out.println(Collections.max(currentMessList));
        }
    }
}
