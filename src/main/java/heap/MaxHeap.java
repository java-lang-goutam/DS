public class MaxHeap {

    private int size;
    private final int capacity;
    private final int[] heap;

    public MaxHeap(final int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    public int getSize() {
        return size;
    }

    public int getMax() {
        if (size == 0) 
            throw new RuntimeException("Cannot extract max from empty heap !!");
        return heap[0];
    }

    public void insert(final int ele) {
        if (size < capacity) {
            heap[size++] = ele;
            heapifyUp(size-1);
            return;
        }
        throw new RuntimeException("Heap Capacity is full !!");
    }

    public void remove() {
        if (size == 0)
            throw new RuntimeException("Heap is empty, cannot remove !!");
        heap[0] = heap[--size];
        heapifyDown(0);
    }

    public void print() {

        System.out.print("[");
        for (int i=0; i<size; i++) {
            System.out.print(heap[i]);
            if (i<size-1) System.out.print(" ");
        }
        System.out.print("]");
    }

    // insert case 
    private void heapifyUp(int index) {
        final int currentNode = heap[index];
        while (index > 0 && currentNode > heap[parentIndex(index)]) {
            heap[index] = heap[parentIndex(index)];
            index = parentIndex(index);
        }
        heap[index] = currentNode;
    }

    // delete case 
    private void heapifyDown(int index) {
        while (index < size) {
            int biggerIndex = index;

            if (heap[leftChildIndex(index)] > heap[biggerIndex]) {
                biggerIndex = leftChildIndex(index);
            }
            if (heap[rightChildIndex(index)] > heap[biggerIndex]) {
                biggerIndex = rightChildIndex(index);
            }

            if (biggerIndex == index) break;
            swap(biggerIndex, index);
            index = biggerIndex;
        }
    } 

    private void swap(final int index1, final int index2) {
        final int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private int parentIndex(final int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(final int index) {  
        return (2 * index) + 1;
    }

    private int rightChildIndex(final int index) {
        return (2 * index) + 2;
    }

    public static void main(String[] args) {
        
        MaxHeap heap = new MaxHeap(5);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.print();
    }
}
