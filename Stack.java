import java.util.NoSuchElementException;

public class Stack<T> implements IStack<T>{
    Queue<T> q1;
    Queue<T> q2;
    int total;

    public Stack(){
        q1 = new Queue<>();
        q2 = new Queue<>();
        total = 0;
    }

    public void push(T data){
        q2.push(data);
        while (!q1.isEmpty()) {
            q2.push(q1.pop());
        }
        Queue<T> tmp = q2;
        q2 = q1;
        q1 = tmp;
        total++;
    }

    public T pop() throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("queue is empty"); }
        T item = q1.pop();
        total--;
        return item;
    }

    public T peek(){
        if(isEmpty()){ throw new NoSuchElementException("queue is empty"); }
        return q1.peek();
    }

    public int size(){
        return total;
    }

    public boolean isEmpty(){
        if (total == 0) return true;
        else return false;
    }
