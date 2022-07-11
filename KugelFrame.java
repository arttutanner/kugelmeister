
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
        cont.setBackground(Color.WHITE);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new MigLayout("fill"));
//        titlePanel.setBackground(Color.RED);
        ImageIcon kajoLogo = new ImageIcon("kajo_logo_200px.png");
        

        titlePanel.add(new JLabel(kajoLogo),"al left");
        JLabel titleLbl = new JLabel(new ImageIcon("kugel_logo.png"));
        titleLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        titlePanel.add(titleLbl, "al center");
        
        titlePanel.add(new JLabel(new ImageIcon("tuuma_200px.png")),"al right");
        
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
