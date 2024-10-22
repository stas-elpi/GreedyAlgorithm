class Processor implements Comparable<Processor> {
    int id;
    TaskQueue processedTasks;

    Processor(int id){
        this.id = id;
        processedTasks = new TaskQueue();
    }


    int getActiveTime(){
        return processedTasks.sumQueue();
    }


    public int compareTo(Processor p) {
            if(this.getActiveTime()>p.getActiveTime()){             //ama o processor A exei perissotero xrono apo to
                return 1;                                           //B tote epistrefei to 1, alliws to -1
            }
            else if(this.getActiveTime()<p.getActiveTime()){
                return -1;
            }
            else{
                if(this.id>p.id){
                    return 1;
                }
                else{
                    return -1;
                }
            }

    }

}
