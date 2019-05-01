package dk.cphbusiness.algorithms.stars;

import static java.awt.BorderLayout.*;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.Timer;


public class StarChart extends javax.swing.JFrame {
    private final Timer timer;
    private final Random random = new Random(7l);
    private final Canvas skyCanvas;
    private final JButton button = new JButton("Start/stop");
    
    private final Matter[] universe = new Matter[1_023];
//    private final Stuff[] universe = new Stuff[65_535];
    
    
    public StarChart() {
        super("Star Chart");
        for (int index = 0; index < universe.length; index++)
            universe[index] = new Matter(random);
        skyCanvas = new SkyCanvas(universe);
        timer = new Timer(50, (event) -> onButtonClicked(event));
        button.addActionListener((event) -> {
            if (timer.isRunning()) { 
                timer.stop();
                button.setText("Start");
                }
            else {
                timer.start();
                button.setText("Stop");
                } 
            });
        initiate();
        }
    
    private void initiate() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container pane = getContentPane();
        skyCanvas.setPreferredSize(new Dimension(1000, 1000));
        pane.add(skyCanvas, CENTER);
        pane.add(button, PAGE_END);
        pack();
        }
    
    private void onButtonClicked(ActionEvent event) {
        // handle(universe, 0, universe.length, 2, 0);
        for (Matter source : universe) {
            if (source.exists()) {
                for (Matter target : universe) {
                    if (target.exists()) source.influence(target);
                    }
                }
            }
        for (Matter matter : universe) if (matter.exists()) matter.move();
        skyCanvas.repaint();
        }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new StarChart().setVisible(true));
        }

    }
