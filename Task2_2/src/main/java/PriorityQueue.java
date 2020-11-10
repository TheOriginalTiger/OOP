import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PriorityQueue <T> {
    private int keys[];
    private T values[];
    private int lastInd = 0;

    @SuppressWarnings("unchecked")
    public PriorityQueue(int sizeOfQueue) {
        keys = new int[sizeOfQueue];
        values = (T[]) new Object[sizeOfQueue];
    }

    private void swap(int i, int j) {
        int tmp = keys[i];
        T tmpValue = values[i];
        values[i] = values[j];
        keys[i] = keys[j];
        values[j] = tmpValue;
        keys[j] = tmp;
    }

    /**
     * method for printing all data in keys for Debug purposes
     */
    public void printKeys() {
        for (int i = 0; i < lastInd; i++) {
            System.out.format("%d ", keys[i]);
        }
        System.out.println();
    }

    private void siftUp(int ind) {
        if (ind != 0) {
            int fatherInd = ind / 2;
            while (keys[ind] > keys[fatherInd]) {
                swap(ind, fatherInd);
                ind = fatherInd;
                fatherInd = ind / 2;
            }
        }
    }

    private void siftDown(int ind) {
        int leftSon = 2 * ind + 1;
        int rightSon = leftSon + 1;
        if (rightSon < lastInd && keys[ind] < keys[rightSon] && keys[leftSon] <= keys[rightSon]) {
            swap(ind, rightSon);
            siftDown(rightSon);
        }
        else if (leftSon < lastInd && keys[ind] <= keys[leftSon]  ) {
            swap(ind, leftSon);
            siftDown(leftSon);
        }

    }

    /**
     * method of the keys to add another elem to keys.
     * @param elem
     */
    public void insert(Pair <Integer,T> elem) {
        keys[lastInd] = elem.getFirst();
        values[lastInd] = elem.getSecond();
        siftUp(lastInd);
        lastInd += 1;
    }
    public int getCapacity()
    {
        return lastInd;
    }

    /**
     * method of the keys for extracting the minimal (the first) elem from keys. Deletes the elem from keys (!)
     * @return the minimal element on the keys
     */
    public Pair <Integer,T> extractMax() {
        Pair <Integer, T> res = new Pair(keys[0], values[0]);
        keys[0] = Integer.MIN_VALUE; // kind of infinity here i guess ....
        swap(0, lastInd - 1 );
        lastInd -= 1;
        siftDown(0);
        return res;
    }
    Stream<T> stream(){
        return StreamSupport.stream(new PriorityQueueSpliterator(), false);
    }

    public class PriorityQueueSpliterator implements Spliterator <T> {
        private int currPos;
        private int lastPos;
        public PriorityQueueSpliterator()
        {
            currPos = 0;
            lastPos = lastInd;
        }
        public PriorityQueueSpliterator(int beg, int end)
        {
            currPos = beg;
            lastPos = end;
        }
        @Override
        public boolean tryAdvance(Consumer<? super T >func)
        {
            if (currPos <= lastPos)
            {

                currPos++;
                func.accept(values[currPos]); //really hope that you won't need to work with keys :P
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            int sep = (currPos - lastPos)/2;
            if (sep < 1 )
                return null;
            int beg = currPos;
            int end = currPos + sep;
            currPos = currPos + sep + 1;
            return new PriorityQueueSpliterator(beg, end);
        }

        @Override
        public long estimateSize() {
            return lastPos - currPos;
        }

        @Override
        public int characteristics() {
            return IMMUTABLE | SIZED | SUBSIZED;
        }

    }

}
