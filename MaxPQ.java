import java.util.Comparator;

public class MaxPQ implements PQInterface {



        private Processor[] heap; // the heap to store data in
        private int size; // current size of the queue
        private IntegerComparator integerComparator ; // the comparator to use between the objects

        private static final int DEFAULT_CAPACITY = 4; // default capacity
        private static final int AUTOGROW_SIZE = 4; // default auto grow


        public MaxPQ(IntegerComparator integerComparator) {
            this.heap = new Processor[DEFAULT_CAPACITY + 1];
            this.size = 0;
            this.integerComparator = integerComparator;
        }


        public void insert(Processor item) {
            // Check available space
            if (size == heap.length - 1)
                grow();

            // Place item at the next available position
            heap[++size] = item;
            // Let the newly added item swim
            if (size>1) {
                swim(size);
            }
        }

        public int size(){
            return this.size;
        }




        public boolean isEmpty(){
            if(this.size==0){
                return true;
            }else{
                return false;
            }
        }


        public Processor getmax() {
            // Ensure not empty
            if (size == 0)
                return null;

            // Keep a reference to the root item
            Processor root = heap[1];

            // Replace root item with the one at rightmost leaf
            heap[1] = heap[size];
            size--;

            // Dispose the rightmost leaf
            // Sink the new root element
            sink(1);

            // Return the int removed
            return root;
        }

        public Processor max(){
            if (size == 0)
                return null;

            Processor root = heap[1];
            return root;
        }




        private void swim(int i) {
            // if i is root (i==1) return
            if (i == 1)
                return;

            // find parent
            int parent = i / 2;

            // compare parent with child i
            while (integerComparator.compare(heap[i], heap[parent]) < 0) {
                swap(i, parent);
                i = parent;
                parent = i / 2;
            }

            // recursive function
            // if (heap[i] < heap[parent]) {
            //     swap(i, parent);
            //     swim(parent);
            // }
        }


        private void sink(int i) {
            // determine left, right child
            int left = 2 * i;
            int right = left + 1;

            // if 2*i > size, node i is a leaf return
            if (left > size)
                return;

            // while haven't reached the leafs
            while (left <= size) {
                // Determine the smallest child of node i
                int min = left;
                if (right <= size) {
                    if (integerComparator.compare(heap[left], heap[right]) > 0)
                        min = right;
                }

                // If the heap condition holds, stop. Else swap and go on.
                // child larger than parent
                if (integerComparator.compare(heap[i], heap[min]) < 0)
                    return;
                else {
                    swap(i, min);
                    i = min;
                    left = i * 2;
                    right = left + 1;
                }
            }
        }


        private void swap(int i, int j) {
            Processor tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }


        private void grow() {
            Processor[] newHeap = new Processor[heap.length + AUTOGROW_SIZE];

            // copy array
            //(notice: in the priority queue, elements are located in the array slots with positions in [1, size])
            for (int i = 0; i <= size; i++) {
                newHeap[i] = heap[i];
            }

            heap = newHeap;
        }

}
