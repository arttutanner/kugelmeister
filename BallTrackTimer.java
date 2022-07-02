  

/**
 * Write a description of class BallTrackTimer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.*;


public class BallTrackTimer
{
    private Pin startPin;
    private Pin endPin;
    private GpioPinDigitalInput startInput;
    private GpioPinDigitalInput endInput;
    
    private long timeAtStart;
    
    private long lastResult;
    private String trackName;
    private boolean activeRace;
    
    
    public BallTrackTimer (String trackName, Pin startPin, Pin endPin) {
        this.startPin = startPin;
        this.endPin = endPin;
        this.trackName = trackName;
        this.activeRace = false;
        init();
        
    }
    
    private void init() {
        System.out.println("Init track "+trackName);
        startInput = KugelMeisterMain.GPIO.provisionDigitalInputPin(startPin, PinPullResistance.PULL_UP);
        endInput = KugelMeisterMain.GPIO.provisionDigitalInputPin(endPin, PinPullResistance.PULL_UP);
        startInput.setShutdownOptions(true);
        endInput.setShutdownOptions(true);  
        
            // create and register gpio pin listener
        startInput.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> Track "+trackName+" START PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                startClock();
            }

        });
        
        
        endInput.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> Track "+trackName+" START PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                stopClock();
            }

        });


        System.out.println("Init done "+trackName);
        
    }
    
    public boolean isRaceActive() {
        return activeRace;
    }
    
    protected void startClock() {
        System.out.println(trackName+ "clock started");
        timeAtStart = System.currentTimeMillis();
        activeRace = true;
    }
    
    protected void stopClock() {

        lastResult = System.currentTimeMillis()-timeAtStart;
        System.out.println(trackName+ "clock stopped, time:"+lastResult);
        activeRace=false;
    }
    
    
}
