import java.util.NoSuchElementException;

public class Queue<T> implements IQueue<T>{
    T[] dataArray;
    int front;
    int back;
    int total;

    public Queue(){
        dataArray = (T[]) new Object[1];
        front = 0;
        back = 0;
        total = 0;
    }

    public void push(T data){
        if (total == totalCapactiy()) {
            back = resize(front, total, 2 * totalCapactiy());
            dataArray[back++] = data;
            total++;
        }
        else {
            back = back % totalCapactiy();
            dataArray[back++] = data;
            total++;
        }
    }

    public T pop() throws NoSuchElementException {
        // exception throwing checks have already been added
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        T item = dataArray[front];
        if (total <= (totalCapactiy() * .25)) {
            back = resize(front, total, totalCapactiy() / 2);
            front = 0;
            if (total == 1) {
                dataArray[front] = null;
                back = 0;
            }
            else dataArray[front++] = null;
        }
        else {
            dataArray[front++] = null;
            front = front % totalCapactiy();
        }
        total--;
        if (total == 0) {
            back = 0;
            front = 0;
        }
        return item;
    }

    public T peek() throws NoSuchElementException {
        // exception throwing checks have already been added
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        return dataArray[front];
    }

    public int size(){
        return total;
    }

    public boolean isEmpty(){
        if (total == 0) return true;
        else return false;
    }

    /* do not modify this function, call this to check the current size of your array */
    public int totalCapactiy(){
        return dataArray.length;
    }

    private int resize(int front, int currentSize, int capacity) {
        T[] copy = (T[]) new Object[capacity];
        int count = 0;
        for (int i = 0; i < currentSize; i++) {
            copy[i] = this.dataArray[front++];
            count++;
        }
        this.dataArray = copy;
        return count;
    }
}
