
import javax.swing.*;
import java.util.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

/**
 * Write a description of class TrackPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrackPanel extends JPanel
{

    private static int NUM_OF_PREVS = 5;
    private Vector<JLabel> prevLbls;
    private JLabel curLbl;
    private BallTrackTimer timer;
    
    public TrackPanel(BallTrackTimer timer) {
        this.timer=timer;
        this.setLayout(new MigLayout("al center"));
        this.setBackground(Color.decode(timer.getColor()));
        JLabel nameLbl = new JLabel(timer.getName());
        nameLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 70));
        this.add(nameLbl,"wrap");
        
        curLbl = new JLabel("--.--");
        curLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
        this.add(curLbl,"wrap");
        
        prevLbls = new Vector<>();
        for (int i=0; i<NUM_OF_PREVS; i++) {
            JLabel pl = new JLabel("##");
            pl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
            prevLbls.add(pl);
            this.add(pl,"wrap");
        }
        
    }
    
    
    public void update() {
        if (timer.isRaceActive()) 
            curLbl.setText(timer.getCurrent());
        else curLbl.setText(timer.getPrev(0));
        
        for (int i=0; i<NUM_OF_PREVS; i++) {
            prevLbls.get(i).setText(timer.getPrev(i+(timer.isRaceActive() ? 0 :1)));
        }
    }
    
}
