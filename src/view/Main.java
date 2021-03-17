package view;

import controller.Sistema;

import java.util.concurrent.Semaphore;

public class Main {
    public static int TotalIngressos = 100;
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1);
        for (int i = 0; i < 300; i++){
            Thread sistema = new Sistema(semaforo,i);
            sistema.start();
        }

    }
}
