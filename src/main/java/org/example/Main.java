package org.example;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            MyThread myThread = new MyThread();
            myThread.start();
        }

        MyThread.print();

    }

}