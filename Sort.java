public class Sort {
    public Sort(Task[] t, int p, int r) {
        if (r <= p) return;
        int i = partition(t, p, r);
        new Sort(t, p, i-1);
        new Sort(t, i+1, r);

    }
    static int partition(Task t[], int p, int r) {
        int i = p-1;
        int pivot = t[r].time;
        for (int j=p; j<r; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (t[j].time >= pivot){
                i++;
                // swap arr[i] and arr[j]
                Task temp = t[i];
                t[i] = t[j];
                t[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Task temp = t[i+1];
        t[i+1] = t[r];
        t[r] = temp;

        return i+1;
    }





}
