package dk.cphbusiness.algorithms.stars;

import dk.cphbusiness.algorithms.utilities.ComparableByAxis;
import java.util.Random;
import static java.lang.Math.*;

public class Matter implements ComparableByAxis<Matter>{
    public static double G = .001;
    public double x;
    public double y;
    public double vx;
    public double vy;
    public double m;
    public double r;
    
    public Matter(double x, double y) {
      this.x = x;
      this.y = y;
      vx = 0.0;
      vy = 0.0;
      m = 1.0;
      r = 0.0;
      }
    
    public Matter(Random random) {
        x = 800.0*random.nextDouble() + 100.0;
        y = 800.0*random.nextDouble() + 100.0;
        vx = 0.0; //random.nextDouble() - 0.5;
        vy = 0.0; //random.nextDouble() - 0.5;
        m = 1.0;
        r = pow(m, .33333);
        }

    @Override
    public String toString() {
        return String.format("(%4.1f,%4.1f)", x, y);
        }
    
    @Override
    public int compareToByAxis(int axis, Matter other) {
        if (!exists()) return 1;
        if (!other.exists()) return -1;
        if (axis == 0) {
            if (x > other.x) return 1;
            if (x < other.x) return -1;
            if (y > other.y) return 1;
            if (y < other.y) return -1;
            return 0;
            }
        else {
            if (y > other.y) return 1;
            if (y < other.y) return -1;
            if (x > other.x) return 1;
            if (x < other.x) return -1;
            return 0;
            }
        }
    
    public boolean exists() { return m > 0.0001; }
    
    public synchronized void influence(Matter other) {
        if (!exists()) return;
        if (!other.exists()) return;
        if (other == this) return;
        double dx = x - other.x;
        double dy = y - other.y;
        double d2 = dx*dx + dy*dy;
        if (d2 < 1.0) {
            vx = m*vx + other.m*other.vx;
            vy = m*vy + other.m*other.vy;
            m += other.m;
            other.m = 0.0;
            other.r = 0.0;
            vx /= m;
            vy /= m;
            r = pow(m, .3333);
            }
        else {
            double d = sqrt(d2);
            double a = G*m/d2;
            other.vx += dx*a/d;
            other.vy += dy*a/d;
            }
        }
    
    public void move() {
        if (!exists()) return; 
        x += vx;
        y += vy;
        }

    }
