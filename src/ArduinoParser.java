// ConsoleTerminal.java: read keyboard and transmit to COM port - read any response
//
// note requires jSerialComm jar file from https://fazecast.github.io/jSerialComm/

// compile and run commands - note that jSerialComm file should be in same directory as java source file
// javac -cp .\jSerialComm-2.9.3.jar;.  ConsoleTerminal.java
// java -cp .\jSerialComm-2.9.3.jar;.  ConsoleTerminal


import com.fazecast.jSerialComm.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class ArduinoParser implements ActionListener {

    private int UPDATE_TIME = 5;
    private static SerialPort comPorts[];
    private static int port=-1;
    private int clocktime;
    private int cooldown;
    private boolean data;
    private String presses;

    private Game backend;

    public static SerialPort comPort;
    public static StringBuilder text=new StringBuilder();  // will hiold a line of text to be parse for an float

    public ArduinoParser(Game backend) {
        this.backend = backend;
        this.clocktime = 0;
        this.cooldown = 0;
        this.data = false;
        this.presses = "";
        init();
        makeTimer();
    }

    public void init() {
        Scanner console = new Scanner(System.in);
        System.out.println("List COM ports");
        // read list of COM ports and display them
        comPorts = SerialPort.getCommPorts();
        if(comPorts.length<1)           // if no COM ports found exit
        {System.out.println("no COM ports found"); return;}
        // print list of COM ports, add to COMportMenu and attach event handler
        for (int i = 0; i < comPorts.length; i++)   {
            System.out.println("comPort[" + i + "] = " + comPorts[i].getDescriptivePortName());
        }
        if(comPorts.length==1)            // if 1 COM port found open it
        {port=0; openCOMport();}
        else
        {
            System.out.print("\nEnter COM port (0, 1, 2 etc) to select serial port ");
            if(console.hasNextInt()) {
                port = console.nextInt();
                openCOMport();
            }
        }
        // loop read characters from keyboard transmit over serial
        //   read characters from serial and display them

    }

    public String read() {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        String result;
        try {
            // read serial port  and display data
            if(comPort.bytesAvailable() > 0)
            {
                byte[] data = new byte[10];
                comPort.readBytes(data,1);
//                System.out.println("yup: " + (char)data[0]);
                if((char)data[0] >= ' ')
                    text.append((char)data[0]);  // if printable append to text
                else {
                    // ****  if end of line parse text for a float value  ****
                    if ((char) data[0] == '\n') {
//                        System.out.println("receieved: (" + text + ")");
                        if (!text.toString().equals("")) {
//                            float x = Float.parseFloat(text.toString());
//                            if (x > 0.5) {
//                                clicked = true;
//                                comPorts[port].closePort();
//                                text.setLength(0);
//                                return true;
//                            }
                            result = "";
                            boolean datapresent = false;
                            if(text.indexOf("L") != -1) {
                                result = result + "L";
                                datapresent = true;
                            }
                            if(text.indexOf("R") != -1) {
                                result = result + "R";
                                datapresent = true;
                            }
                            if(text.indexOf("U") != -1) {
                                result = result + "U";
                                datapresent = true;
                            }
                            if(text.indexOf("D") != -1) {
                                result = result + "D";
                                datapresent = true;
                            }
                            if (datapresent) {
                                comPorts[port].closePort();
                                text.setLength(0);
                                return result;
                            }
                        }
                        text.setLength(0);
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPorts[port].closePort();
        return "";
    }

    // attempt to open COM port
    static void openCOMport() {
        comPort = SerialPort.getCommPorts()[port];
        System.out.println("attempting to open " + comPorts[port].getDescriptivePortName() + "\n");
        if (!comPort.openPort()) {
            System.out.println("failed to open COM port " + comPorts[port].getDescriptivePortName() + "\n");
            port = -1;
            return;
        }
        System.out.println("Opened COM port " + comPorts[port].getDescriptivePortName() + " OK\n");
        comPort.setBaudRate(115200);
    }

    public void makeTimer() {
        Timer parseClock = new Timer(UPDATE_TIME, this);
        parseClock.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        clocktime++;
        cooldown--;


        String result = read();

//        System.out.println(result);

        if (cooldown < 1) {
            if(result.indexOf("L") != -1) {
                if (!presses.contains("L")) {
                    presses = presses + "L";
                }
                data = true;
            }
            if(result.indexOf("R") != -1) {
                if (!presses.contains("R")) {
                    presses = presses + "R";
                }
                data = true;
            }
            if(result.indexOf("U") != -1) {
                if (!presses.contains("U")) {
                    presses = presses + "U";
                }
                data = true;
            }
            if(result.indexOf("D") != -1) {
                if (!presses.contains("D")) {
                    presses = presses + "D";
                }
                data = true;
            }
        }


//        if ((!result.equals("")) && cooldown < 1) {
//            data = true;
//            System.out.println("data");
//        }

        if (clocktime % 3 == 0) {
//            System.out.println(result);
            backend.acceptData(presses);
            if (data) {
                System.out.println("d: " + presses);
                cooldown = 20;
            }
            data = false;
            presses = "";
        }



    }
}
