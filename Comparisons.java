import java.io.*;
import java.util.Random;

public class Comparisons {
    public static void main(String[] args) throws IOException {
        //createFiles();                 //entoli gia na dhmiourgithoun ta 30 arxeia txt opws zitaei h ekfwnhsh
        int makespan100=0;
        int makespanSorted100=0;
        int makespan250=0;
        int makespanSorted250=0;
        int makespan500=0;
        int makespanSorted500=0;

        File directoryPath = new File(args[0]);             //dexetai ws eisodo to fakelo me ta txt arxeia, opou analoga
        File[] filesList = directoryPath.listFiles();       //me ton arithmo ton epexergaston tou kathe arxeiou
        assert filesList != null;
        if (filesList!=null) {
            for (File file : filesList) {                        //katalavainei ton arithmo N ton tasks
                Reader fileR = new FileReader(file);
                BufferedReader fileBR = new BufferedReader(fileR);
                String line = null;
                line = fileBR.readLine();
                int procNum = Integer.parseInt(line);

                if (procNum == 10) {                                    //gia kathe arxeio me omoio N apothhkeuei to makespan
                    Greedy gr = new Greedy(file, false);         //ton duo algorithmon
                    makespan100 += gr.getMakespan();
                    Greedy grSorted = new Greedy(file, true);
                    makespanSorted100 += grSorted.getMakespan();
                } else if (procNum == 15) {
                    Greedy gr = new Greedy(file, false);
                    makespan250 += gr.getMakespan();
                    Greedy grSorted = new Greedy(file, true);
                    makespanSorted250 += grSorted.getMakespan();
                } else if (procNum == 22) {
                    Greedy gr = new Greedy(file, false);
                    makespan500 += gr.getMakespan();
                    Greedy grSorted = new Greedy(file, true);
                    makespanSorted500 += grSorted.getMakespan();
                }
            }
        }

        makespan100=makespan100/10;
        makespan250=makespan250/10;
        makespan500=makespan500/10;
        makespanSorted100=makespanSorted100/10;
        makespanSorted250=makespanSorted250/10;
        makespanSorted500=makespanSorted500/10;
        System.out.println("Gia N=100 unsorted greedy to makespan: "+makespan100+"   enw gia sorted greedy: "+ makespanSorted100);
        System.out.println("Gia N=250 unsorted greedy to makespan: "+makespan250+"   enw gia sorted greedy: "+ makespanSorted250);
        System.out.println("Gia N=500 unsorted greedy to makespan: "+makespan500+"   enw gia sorted greedy: "+ makespanSorted500);
        System.out.println();
        System.out.println("fanera sorted greedy makespan < unsorted greedy makespan");
    }

    public static void createFiles() throws IOException {    //algorithmos dhmiourgias arxeion txt
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] N = {100,250,500};                        //arithmoi tasks
        for (int i = 0; i<3; i++){
            for (int item : numbers) {                  //3*10 arxeia
                String key = "file" + item;
                File file = File.createTempFile(key, ".txt", new File("/Users/dimitris/Desktop/files"));


                Writer writer = new OutputStreamWriter(new FileOutputStream(file));
                String m = Integer.toString((int) Math.sqrt(N[i]));                     //arithmos epexergaston
                writer.write(m + "\n");

                String n2 = Integer.toString(N[i]);
                writer.write(n2 + "\n");

                Random rand = new Random();
                int r_time = 0;
                String r_time2;
                int r_id = 0;
                String r_id2;
                for (int j = 0; j < N[i]; j++) {
                    r_time = rand.nextInt(1000);                        //random task xronos kai id
                    r_id = rand.nextInt(1000);
                    r_time2 = Integer.toString(r_time);
                    r_id2 = Integer.toString(r_id);
                    writer.write(r_id + " " + r_time2 + "\n");

                    file.createNewFile();
                }
                writer.close();
            }
        }
    }
}

