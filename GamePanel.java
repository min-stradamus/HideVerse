import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel {
    private static final int TILE = 45;
    private static final int GRID_W = 14; // columns
    private static final int GRID_H = 15; // rows

    // Corner images
    private BufferedImage topLeftCornerImg, topRightCornerImg;
    private BufferedImage bottomLeftCornerImg, bottomRightCornerImg;

    // Side images
    private BufferedImage topSideImg, bottomSideImg;
    private BufferedImage leftSideImg, rightSideImg;

    public GamePanel() {
        setPreferredSize(new Dimension(GRID_W * TILE, GRID_H * TILE));
        Color TORQUOISE = new Color(60, 184, 152);
        setBackground(TORQUOISE);

        try {
            // Load corners
            topLeftCornerImg     = ImageIO.read(new File("assets/corner_wall.png"));
            topRightCornerImg    = ImageIO.read(new File("assets/corner_wall_2.png"));
            bottomLeftCornerImg  = ImageIO.read(new File("assets/corner_wall_3.png"));
            bottomRightCornerImg = ImageIO.read(new File("assets/corner_wall_4.png"));

            // Load sides
            topSideImg    = ImageIO.read(new File("assets/side_wall.png"));
            bottomSideImg = ImageIO.read(new File("assets/side_wall_d.png"));
            leftSideImg   = ImageIO.read(new File("assets/side_wall_l.png"));
            rightSideImg  = ImageIO.read(new File("assets/side_wall_r.png"));
        } catch (Exception e) {
            System.out.println("Error loading wall images: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Top row
        for (int x = 0; x < GRID_W; x++) {
            int y = 0;
            if (x == 0 && topLeftCornerImg != null) {
                g.drawImage(topLeftCornerImg, x * TILE, y * TILE, TILE, TILE, null);
            } else if (x == GRID_W - 1 && topRightCornerImg != null) {
                g.drawImage(topRightCornerImg, x * TILE, y * TILE, TILE, TILE, null);
            } else if (topSideImg != null) {
                g.drawImage(topSideImg, x * TILE, y * TILE, TILE, TILE, null);
            }
        }

        // Bottom row
        for (int x = 0; x < GRID_W; x++) {
            int y = GRID_H - 1;
            if (x == 0 && bottomLeftCornerImg != null) {
                g.drawImage(bottomLeftCornerImg, x * TILE, y * TILE, TILE, TILE, null);
            } else if (x == GRID_W - 1 && bottomRightCornerImg != null) {
                g.drawImage(bottomRightCornerImg, x * TILE, y * TILE, TILE, TILE, null);
            } else if (bottomSideImg != null) {
                g.drawImage(bottomSideImg, x * TILE, y * TILE, TILE, TILE, null);
            }
        }

        // Left side (skip corners)
        for (int y = 1; y < GRID_H - 1; y++) {
            if (leftSideImg != null) {
                g.drawImage(leftSideImg, 0, y * TILE, TILE, TILE, null);
            }
        }

        // Right side (skip corners)
        for (int y = 1; y < GRID_H - 1; y++) {
            if (rightSideImg != null) {
                g.drawImage(rightSideImg, (GRID_W - 1) * TILE, y * TILE, TILE, TILE, null);
            }
        }
    }
}