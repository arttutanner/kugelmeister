

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.util.*;



public class KugelMeisterMain {

    
    public static GpioController GPIO;
    public static Vector<BallTrackTimer> trackTimers;


    public static void main(String[] args) {
        // KoriKarhuFrame kkf = new KoriKarhuFrame();
        // kkf.reset();
        
        /*
        kkf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar());
                if (e.getKeyChar()=='s') ;
             }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        */
        
        
        // in order to use the Broadcom GPIO pin numbering scheme, we need to configure the
        // GPIO factory to use a custom configured Raspberry Pi GPIO provider
        
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
        // create gpio controller
        GPIO = GpioFactory.getInstance();
        trackTimers = new Vector<>();
        trackTimers.add(new BallTrackTimer("Rata 1","0xFFCCCC",RaspiPin.GPIO_26,RaspiPin.GPIO_20));
        trackTimers.add(new BallTrackTimer("Rata 2","0xCCFFCC",RaspiPin.GPIO_21,RaspiPin.GPIO_19));
        trackTimers.add(new BallTrackTimer("Rata 3","0xCCCCFF",RaspiPin.GPIO_06,RaspiPin.GPIO_13));
        trackTimers.add(new BallTrackTimer("Rata 4","0xFFCCFF",RaspiPin.GPIO_11,RaspiPin.GPIO_08));
        trackTimers.add(new BallTrackTimer("Rata 5","0xCCFFFF",RaspiPin.GPIO_09,RaspiPin.GPIO_25));
        trackTimers.add(new BallTrackTimer("Rata 6","0xFFFFCC",RaspiPin.GPIO_22,RaspiPin.GPIO_23));
        
        new KugelFrame();
        
        /*
        // keep program running until user aborts (CTRL-C)
        while(true) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller

        
    }

}
