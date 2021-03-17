package controller;

import view.Main;

import java.util.concurrent.Semaphore;

public class Sistema extends Thread {
    private boolean logado;
    private int qntingressos,id;
    private Semaphore semaforo;

    public Sistema(Semaphore semaforo, int id){
        qntingressos = (int) (Math.random() * (4 - 1 + 1) + 1);
        this.semaforo = semaforo;
        this.id = id;
    }

    @Override
    public void run() {
        loga();
        procCompra();
            try {
                semaforo.acquire();
                validaCompra(Main.TotalIngressos);
                semaforo.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public void validaCompra(int TotalIngressos){
        if(logado){
            if(qntingressos <= TotalIngressos){
                Main.TotalIngressos -= qntingressos;
                System.out.println("Usuário nº" + id + ": Compra efetuada com sucesso!");
            }else{
                System.out.println("Usuário nº " + id + ": Ingressos esgotados!");
                logado = false;
            }
        }
    }

    public void procCompra(){
        if(logado){
            int tproc;
            tproc = (int) (Math.random() * (3000 - 1000 + 1) + 1000);
            if(tproc < 2500){
                try {
                    sleep(tproc);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Usuário nº"+ id +": Tempo de sessão esgotado!");
                logado = false;
            }
        }


    }

    public void loga(){
        int tempologin = (int) (Math.random() * (2000 - 50 + 1) + 50);
        try {
            sleep(tempologin);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(tempologin > 1000){
            System.out.println("Usuário nº "+ id + ": Timeout");
            logado = false;
        }
        else{
            System.out.println("Usuário nº " + id + ": Logado com sucesso!");
            logado = true;
        }
    }
}
