import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class image {
    public static void main ( String[] args ) throws IOException, InterruptedException {
        if( args.length < 3 ) {
            System.out.println("************************************************************");
            System.out.println("Error. Insufficient number of arguments.");
            System.out.println("Usage : java <input.jpg> <pixelSize> <Processing Mode (M/S)>");
            System.out.println("************************************************************");
            return;
        }
//        Reading the input files from Console:
        String inputFile = args[0];
        int pixelSize = Integer.parseInt(args[1]);
        String processingMode = args[2];
        int cores = Runtime.getRuntime().availableProcessors();


//        Storing the input image in the Buffer
        BufferedImage inputImage = ImageIO.read(new File( inputFile ));
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

//        Open image in the GUI
        JFrame frame = new JFrame("Interesting Frame");
        JLabel jLabel = new JLabel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(inputImage);
        jLabel.setIcon(imageIcon);
        frame.setIconImage(inputImage);
        frame.getContentPane().add(jLabel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
        imageprocessor imageProcessor = new imageprocessor(inputImage);

//        Initialize Thread
        Thread thread = new Thread();
cores=8;
//        Create for each core a new thread in case if the Multithreading is selected
        if(processingMode.equals("M")){
            System.out.println("Processing mode equals M");
            /*In case if we have more than four cores, first we divide the image by 2 the height of the image,
            then we divide the width of the image by the number of the rest of the cores.
            Ex: if 16 cores: will be 8 threads in the top and 8 in the bottom (height/2, width/8)
            Else, if less than 4 cores, we just divide the width of the image by number of cores */
            if(cores%2==0 && cores >=4){
              for(int j=0;j<2;j++){
                int top = j*height/2;
                int bottom = (j+1)*height/2;
                for (int i = 0; i < (cores/2); i++) {
                int left = i*width/(cores/2);
                int right = (i+1)*width/(cores/2);
                thread = new MultiThreading(frame,imageProcessor,top,bottom,left,right,pixelSize);
                thread.start();
              }
            }
        }
            else {
                for (int i = 0; i < cores; i++) {
                    int left = i*width/cores;
                    int right = (i+1)*width/cores;
                    int top = 0;
                    int bottom = height;
                    thread = new MultiThreading(frame,imageProcessor,top,bottom,left,right,pixelSize);
                    thread.start();
                }
            }
//            Wait until all the threads are completed in order to save the result image as final version
            for (int i = 0; i < cores; i++) {
               thread.join();
            }
        }
//             In case if Single thread selected we just straight forward run the single thread
        else {
            System.out.println("Processing mode equals S");
            int top = 0;
            int bottom = height;
            int left = 0;
            int right = width;
            thread = new MultiThreading(frame,imageProcessor,top,bottom, left,right,pixelSize);
            thread.start();
            thread.join();
        }
//        Write processed image in the destination folder
        imageProcessor.writeImage("result.jpg");
    }
}
