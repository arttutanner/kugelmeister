

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
        trackTimers.add(new BallTrackTimer("Rata 1",RaspiPin.GPIO_26,RaspiPin.GPIO_20));
  
        // keep program running until user aborts (CTRL-C)
        while(true) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller

        
    }

}
