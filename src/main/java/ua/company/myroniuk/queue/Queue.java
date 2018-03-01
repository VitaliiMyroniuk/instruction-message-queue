package ua.company.myroniuk.queue;

public interface Queue<E> {

    void enqueue(E e);

    E dequeue();

    E peek();

    int count();

    boolean isEmpty();

}
