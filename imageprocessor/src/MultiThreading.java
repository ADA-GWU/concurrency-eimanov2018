import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class MultiThreading extends Thread {
    imageprocessor imageProcessor;
    int pixelSize;
    int coreBottom=0;;
    int coreRight=0;
    int coreTop=0;
    int coreLeft=0;
    JFrame frame;

//    Constructor of the Multithreading
    public MultiThreading(JFrame frame,imageprocessor imageProcessor, int coreTop, int coreBottom,int coreLeft, int coreRight,int pixelSize) {
        this.imageProcessor=imageProcessor;
        this.pixelSize=pixelSize;
        this.frame=frame;
        this.coreTop=coreTop;
        this.coreBottom=coreBottom;
        this.coreLeft=coreLeft;
        this.coreRight=coreRight;
    }
//    Overriden run method of the Thread class
    public void run()
    {
//        For each thread is created new Left, Right, Top, Bottom borders, in order to each thread can independently process
        for(int top=coreTop; top<coreBottom; top=top+pixelSize){
                for(int left=coreLeft; left<coreRight; left=left+pixelSize){
                    int right=left+pixelSize;
                    int bottom=top+pixelSize;
                    imageProcessor.compute(left, top, right, bottom);
//                   Adding some waiting time, in order to see the process smoother in case if there is many cores
                    try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    frame.repaint();
                }
            }
        System.out.println("Thread " + Thread.currentThread().getId() + " is running");
    }
}