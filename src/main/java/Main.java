class Heap {

    int heap[];
    int lastInd = 0;

    public Heap(int sizeOfHeap) {
        heap = new int[sizeOfHeap];
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void printHeap() {
        for (int i = 0; i < lastInd; i++) {
            System.out.format("%d ", heap[i]);
        }
        System.out.println();
    }

    private void siftUp(int ind) {
        if (ind != 0) {
            int fatherInd = ind / 2;
            while (heap[ind] < heap[fatherInd]) {
                swap(ind, fatherInd);
                ind = fatherInd;
                fatherInd = ind / 2;
            }
        }
    }

    private void siftDown(int ind) {
        int leftSon = 2 * ind + 1;
        int rightSon = leftSon + 1;
        if (rightSon < lastInd && heap[ind] > heap[rightSon] && heap[leftSon] >= heap[rightSon]) {
            swap(ind, rightSon);
            siftDown(rightSon);
        }
        else if (leftSon < lastInd && heap[ind] >= heap[leftSon]  ) {
            swap(ind, leftSon);
            siftDown(leftSon);
        }

    }

    public void addToHeap(int elem) {
        heap[lastInd] = elem;
        siftUp(lastInd);
        lastInd += 1;
    }

    private int extractMin() {
        int temp = heap[0];
        heap[0] = Integer.MAX_VALUE; // kind of infinity here i guess ....
        swap(0, lastInd - 1 );
        lastInd -= 1;
        siftDown(0);
        return temp;
    }

    public int[] heapSort(int[] array)
    {
        for (int i = 0 ; i < array.length; i++) {
            this.addToHeap(array[i]);
        }
        int lol = 5;
        for (int i = 0; i <array.length; i++){
            array[i] = extractMin();
        }

        return array;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        int heh[] = {23,17,5,0,20,7,29,2,14,31,0,29,13,13,43,-1,14,0,4,12,55,43};
        Heap h = new Heap(heh.length);
        heh = h.heapSort(heh);
        for (int i = 0; i < heh.length; i++){
            System.out.format("%d ", heh[i]);
        }
    }
}