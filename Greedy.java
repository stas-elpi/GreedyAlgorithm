import java.io.*;
import java.io.IOException;

public class Greedy {


    public static void main(String[] args) throws IOException {

        File reader = new File(args[0]);
        Reader fileR = new FileReader(reader);
        BufferedReader file = new BufferedReader(fileR);
        String file2 = "";
        String line = null;

        line = file.readLine();
        int procNum = Integer.parseInt(line);    //arithmos epexergaston

        line = file.readLine();
        int taskNum = Integer.parseInt(line);    //arithmos tasks


        String s1[] = new String[taskNum];
        String s2[] = new String[taskNum];
        for (int i = 0; i < taskNum; i++) {
            line = file.readLine();
            s1[i] = line;
            if (line == null) {
                System.out.println("error: ligotera tasks");  //an kapoia grammh einai null exoume ligotera tasks
                return;
            }
            String[] splitS = s1[i].split("\\s+");    //xorizontai ta string sto keno, dhladh to id me to xrono
            s1[i] = splitS[0];
            s2[i] = splitS[1];
            if (i == taskNum - 1) {
                line = file.readLine();
                if (line != null) {
                    System.out.println("error: parapnw tasks");  //an h epomenh grammh dn einai null exoume parapanw tasks
                    return;
                }
            }
        }

        MaxPQ maxpq = new MaxPQ(new IntegerComparator());
        Processor processor;

        for (int i = 0; i < procNum; i++) {
            processor = new Processor(i + 1);
            maxpq.insert(processor);
        }
        Task[] taskTable = new Task[taskNum];
        for (int t = 0; t < taskNum; t++) {         //vazoume ta tasks se ena pinaka
            int s1b = Integer.parseInt(s1[t]);
            int s2b = Integer.parseInt(s2[t]);
            Task task = new Task(s1b, s2b);
            taskTable[t] = task;
        }



        for (int t = 0; t < taskNum; t++) {    //vazoume ta tasks apo to pinaka sta processor kai meta sth MaxPQ
            Task task2 = taskTable[t];
            Processor z = maxpq.getmax();
            z.processedTasks.put(task2);
            maxpq.insert(z);
        }
        int makespan = 0;
        int max = 0;
        if (procNum > 0) {
            for (int m = 0; m < procNum; m++) {
                if (procNum < 50) {
                    Processor z = maxpq.getmax();
                    System.out.print("id " + z.id + ", load=" + z.getActiveTime() + ": ");
                    z.processedTasks.printQueue(System.out);
                    System.out.println(" ");
                    if (max < z.getActiveTime()) {          //mazimum makespan
                        max = z.getActiveTime();
                    }
                } else {
                    Processor z = maxpq.getmax();
                    if (max < z.getActiveTime()) {
                        max = z.getActiveTime();
                    }
                }
            }
        }
        makespan = max;
        System.out.println(makespan);




    }
    // Idia greedy me mikres    allages

    private int p;
    public Greedy(File f,boolean sorted) throws IOException{   //constructor ths Greedy wste na xrhsimopoieitai me
        File reader = new File(String.valueOf(f));             //parametrous kai na epistrefei makespan
        Reader fileR = new FileReader(reader);
        BufferedReader file = new BufferedReader(fileR);
        String file2 = "";
        String line = null;

        line = file.readLine();
        int procNum = Integer.parseInt(line);

        line = file.readLine();
        int taskNum = Integer.parseInt(line);


        String s1[] = new String[taskNum];
        String s2[] = new String[taskNum];
        for (int i = 0; i < taskNum; i++) {
            line = file.readLine();
            s1[i] = line;
            if (line == null) {
                System.out.println("error: ligotera tasks");
                return;
            }
            String[] splitS = s1[i].split("\\s+");
            s1[i] = splitS[0];
            s2[i] = splitS[1];
            if (i == taskNum - 1) {
                line = file.readLine();
                if (line != null) {
                    System.out.println("error: parapnw tasks");
                    return;
                }
            }
        }

        MaxPQ maxpq = new MaxPQ(new IntegerComparator());
        Processor processor;  //end

        for (int i = 0; i < procNum; i++) {
            processor = new Processor(i + 1);
            maxpq.insert(processor);
        }
        Task[] taskTable = new Task[taskNum];
        for (int t = 0; t < taskNum; t++) {
            int s1b = Integer.parseInt(s1[t]);
            int s2b = Integer.parseInt(s2[t]);
            Task task = new Task(s1b, s2b);
            taskTable[t] = task;
        }
        if(sorted) {
            Sort sort = new Sort(taskTable,0,taskNum-1);
        }

        for (int t = 0; t < taskNum; t++) {
            Task task2 = taskTable[t];
            Processor z = maxpq.getmax();
            z.processedTasks.put(task2);
            maxpq.insert(z);
        }
        int makespan = 0;
        int max = 0;
        if (procNum > 0) {
            for (int m = 0; m < procNum; m++) {
                if (procNum < 50) {
                    Processor z = maxpq.getmax();
                    if (max < z.getActiveTime()) {
                        max = z.getActiveTime();
                    }
                } else {
                    Processor z = maxpq.getmax();
                    if (max < z.getActiveTime()) {
                        max = z.getActiveTime();
                    }
                }
            }
        }
        makespan = max;
        this.p=makespan;   //kataxwrei to makespan sthn private p

    }
    public int getMakespan(){
        return p;
    }  //epistrefei to mkespan


}

