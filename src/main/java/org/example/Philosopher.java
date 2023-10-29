package org.example;

public class Philosopher implements Runnable {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private int mealsEaten;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.mealsEaten = 0;
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ  " + id + " голоден");
        synchronized (leftFork) {
            System.out.println("Философ " + id + " взял левую вилку");
            synchronized (rightFork) {
                System.out.println("Философ " + id + " взял правую вилку");
                System.out.println("Философ " + id + " ест");
                mealsEaten++;
                Thread.sleep((long) (Math.random() * 1000));
            }
            System.out.println("Философ " + id + " отложил правую вилку");
        }
        System.out.println("Философ " + id + " отложил левую вилку");
    }
}
