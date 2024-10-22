import java.util.Comparator;

class IntegerComparator{
    public int compare(Processor t1, Processor t2) {
        int apotelesma;
        if(t2 !=null) {
            apotelesma = t1.compareTo(t2);
            return apotelesma;
        }
        else return 1;
    }
}