package com.example.finalvy;

class Queue
{
    private Student[] arr;  // array to store queue elements
    private int front;      // front points to the front element in the queue
    private int rear;       // rear points to the last element in the queue
    private int capacity;   // maximum capacity of the queue
    private int count;      // current size of the queue

    // Constructor to initialize a queue
    Queue(int size)
    {
        arr = new Student[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }

    // Utility function to dequeue the front element
    public Student dequeue()
    {
        // check for queue underflow
        if (isEmpty())
        {System.exit(-1);}
        Student x = arr[front];
        front = (front + 1) % capacity;
        count--;
        return x;
    }

    // Utility function to add an item to the queue
    public void enqueue(Student item)
    {
        // check for queue overflow
        if (isFull())
        {System.exit(-1);}
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        count++;
    }

    // Utility function to return the front element of the queue
    public Student peek()
    {
        if (isEmpty())
        {System.exit(-1);}
        return arr[front];
    }

    // Utility function to return the size of the queue
    public int size() {
        return count;
    }

    // Utility function to check if the queue is empty or not
    public boolean isEmpty() {
        return (size() == 0);
    }

    // Utility function to check if the queue is full or not
    public boolean isFull() {
        return (size() == capacity);
    }
}

