package ru.vsu.cs.course2;

import ru.vsu.cs.course2.drawers.line.*;
import ru.vsu.cs.course2.drawers.pixel.GraphicsPixelDrawer;
import ru.vsu.cs.course2.drawers.pixel.PixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    private int x = 0, y = 0;

    public DrawPanel() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gr = bf.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        PixelDrawer pd = new GraphicsPixelDrawer(gr);
        LineDrawer ld;

        int change = 2;
        switch (change) {
            case 1:
                ld = new BresenhamLineDrawer(pd);
                break;
            case 2:
                ld = new DDALineDrawer(pd);
                break;
            case 3:
                ld = new WuLineDrawer(pd);
                break;
            default:
                ld = new GraphicsLineDrawer(g);
        }

        drawAll(ld);
        g.drawImage(bf, 0, 0, null);
        gr.dispose();
    }

    public void drawAll(LineDrawer ld) {
        snowFlake(ld, 200, 200, 80, 68);
        line(ld);
    }

    public void snowFlake(LineDrawer ld, int x, int y, int r, int n) {
        double d = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double dx = r * Math.cos(d * i);
            double dy = r * Math.sin(d * i);
            ld.drawLine(x, y, x + (int) dx, y + (int) dy);
        }
    }

    public void line(LineDrawer ld) {
        ld.drawLine(this.getWidth() / 2, this.getHeight() / 2, x, y);
    }
}
