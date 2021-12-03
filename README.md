# concurrency-eimanov2018
concurrency-eimanov2018 created by GitHub Classroom

The imageprocessing app, is the GUI application, written in Java using Swing and JFrame libraries.

The application is written in the IntelliJ IDE. However, it can be run from the terminal as well.

In order to run from the terminal, you need to locate in the terminal to the folder path "/concurrency-eimanov2017/imageprocessor/src"

Here you will see three java classes:
- image.java : Where the main class is situated with JFrame GUI constructors
- imageprocessor.java - The compute() function is written, which finds the mean of the pixels, the size of which were indicated in the terminal
- MultiThreading.java - The class which extends the Thread class. 

       For smooth visualization, Thread.sleep(5); method is called in the MultiThreading.java class in the line 35. 

       It can be put to 0 in order to see the real speed.

       The run() method receives the left,right,top,bottom values of borders of the part of the image to process.
                        
       The run() method is called the same amount, how many cores the PC has
       
       For each thread different borders of the same image is sent
                       
       After all threads are completed, the result.jpg will be saved in the path, where the java files are stored.
                        
In order to run from the terminal, you need to compile the image.java class: 

                $ javac image.java

Run the compiled main java class, you need to pass three parameters in the terminal: 

Arg 1: file name; Arg 2: size of the square to find mean (pixels); Arg 3: the processing mode (S/M) S - for Single thread, M - for the Multi thread

                $ java image IMG_1.JPG 40 M

The new JFrame windows is opening with image, which is processed in the real time.

After the process is finished, the result.jpg image is saved in the current folder path.

For questions, please write eimanov2018@ada.edu.az
