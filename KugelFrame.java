
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;

/**
 * Write a description of class KugelFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KugelFrame extends JFrame
{

    private Vector<TrackPanel> trackPanels;
    
    public KugelFrame() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());

        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new MigLayout("al center"));
//        titlePanel.setBackground(Color.RED);
        JLabel titleLbl = new JLabel("KugelMeister 2000 pro v.1.01");
        titleLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 110));
        titlePanel.add(titleLbl);
        
        cont.add(titlePanel,BorderLayout.NORTH);
        trackPanels = new Vector<>();
        JPanel dataPanel = new JPanel(new MigLayout("fill","[fill][fill][fill]"));
        for (int i=0; i<KugelMeisterMain.trackTimers.size(); i++) {
            TrackPanel tp = new TrackPanel(KugelMeisterMain.trackTimers.get(i));
            dataPanel.add(tp,"grow"+(i==2 ? ",wrap" : ""));
            trackPanels.add(tp);
        }
        
        cont.add(dataPanel,BorderLayout.CENTER);
        
        
        this.setVisible(true);
        
        javax.swing.Timer updateTimer = new javax.swing.Timer(10,new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                update();
            }
        }
        );
        updateTimer.start();
    }
    
    public void update() {
        for (TrackPanel tp : trackPanels)
            tp.update();
        

        repaint();
    }
    
}
