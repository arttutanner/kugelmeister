  

/**
 * Write a description of class BallTrackTimer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.*;
import java.util.*;

public class BallTrackTimer
{
    private Pin startPin;
    private Pin endPin;
    private GpioPinDigitalInput startInput;
    private GpioPinDigitalInput endInput;
    
    private long timeAtStart;
    
    private long lastResult;
    private String trackName;
    private boolean isActive;
    private String colorHex;
    private Vector<Long> prevResults;
    
    public BallTrackTimer (String trackName, String colorHex, Pin startPin, Pin endPin) {
        this.startPin = startPin;
        this.endPin = endPin;
        this.trackName = trackName;
        this.isActive = false;
        this.colorHex = colorHex;
        this.prevResults = new Vector<>();
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
        return isActive;
    }
    
    
    public String getColor() {
        return colorHex;
    }
    
    protected void startClock() {
        if (isActive) {
            System.out.println("Cannot start, because we are already started");
            return;
        }
        System.out.println(trackName+ "clock started");
        timeAtStart = System.currentTimeMillis();
        isActive = true;
    }
    
    protected void stopClock() {
        if (!isActive) {
            System.out.println("Cannot stop, since we haven't started.");
            return;
        }
        lastResult = System.currentTimeMillis()-timeAtStart;
        System.out.println(trackName+ "clock stopped, time:"+formatAsTime(lastResult));
        prevResults.add(lastResult);
        isActive=false;
    }
    
    public String getCurrent() {
        if (!isActive) return null;
        return formatAsTime(System.currentTimeMillis()-timeAtStart);
    }
    
    public String getName() {
        return trackName;
    }
    
    public String getPrev(int nth) {
        if (prevResults.size()==0) return "--.--";
        int pos = prevResults.size()-(nth+1);
        if (pos<0) return "--.--";
        return(formatAsTime(prevResults.get(pos)));
    }
    
    private String formatAsTime(long millis) {
        long secs = millis / 1000;
        long dec = millis % 1000;
        return ""+secs+"."+dec;
    }
    
        
}
