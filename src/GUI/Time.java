package GUI;

public class Time extends Thread{
    @Override
    public void run(){
        try{
            wait();
        }catch (Exception e){
            System.out.print(e);
        }
    }
}
