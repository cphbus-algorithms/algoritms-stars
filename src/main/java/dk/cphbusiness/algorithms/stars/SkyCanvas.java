package dk.cphbusiness.algorithms.stars;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SkyCanvas extends Canvas {
    private final Matter[] universe;
    private static final Color STEADY = new Color(0.0f, 0.0f, 1.0f, 1.0f);
    private static final Color MOOVING = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    private static final Color VANISHED = new Color(0.0f, 1.0f, 1.0f, 1.0f);
    public SkyCanvas(Matter[] universe) {
        this.universe = universe;
        super.setBackground(Color.white);
        super.setSize(1000, 1000);
        }
    
    private int transformX(double x) { return (int)(x); }

    private int transformY(double y) { return (int)(y); }
    
    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        for (Matter matter : universe) {
            // g.fillRect(transformX(matter.x), transformY(matter.y), 1, 1);
            if (!matter.exists()) g.setPaint(VANISHED);
            else if (matter.vx == 0f && matter.vy == 0f) g.setPaint(STEADY);
            else g.setPaint(MOOVING);
            int r = (int)matter.r;
            
            g.fillRect(transformX(matter.x) - r, transformY(matter.y) - r, 2*r + 1, 2*r + 1);
            }
        }
    
    }
