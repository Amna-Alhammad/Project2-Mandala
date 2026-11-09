import edu.cymbal.graphics.*;
import java.awt.Color;

public class Mandala {

    private static CanvasAPI canvas;
    private static PainterAPI p;
    private static float designHue;
/**
   Creates the canvas and painter, chooses a random color hue,
   and draws the complete mandala design.
 */
    public static void main(String[] args) {
        canvas = Graphics.createCanvas(800, 800, "My Mandala");
        p = canvas.getPainter();
        designHue = (float) Math.random();
        drawDesign();
    }

    public static void drawDesign() {
        drawBackground();
        drawCentralMandala();
        drawSquareRing();
        drawSurroundingShapes();
        drawSmallDecorations();
    }
/**
   Draws three flower layers at the canvas center using
   different sizes and shades of the chosen hue.
 */
    public static void drawCentralMandala() {
        drawFlower(0, 0, 100,
                Color.getHSBColor(designHue, 0.45f, 0.95f));

        drawFlower(0, 0, 80,
                Color.getHSBColor(designHue, 0.65f, 0.75f));

        drawFlower(0, 0, 50,
                Color.getHSBColor(designHue, 0.85f, 0.55f));
    }
    
    public static void drawFlower(int x, int y, int radius, Color color) {
        p.liftPen();
        p.moveTo(x, y);
        p.lowerPen();
        p.setPenColor(color);
        p.setPenWidth(2);

        for (int petal = 0; petal < 12; petal++) {
            drawPetalCircle(radius);
            p.turnRight(30);
        }

        p.liftPen();
    }
    public static void drawPetalCircle(int radius) {
        p.circle(radius);
    }
    
    
    public static void drawSquareRing() {
        for (int angle = 0; angle < 90; angle += 4) {
            float brightness = 0.55f + (angle / 90.0f) * 0.30f;

            p.setPenColor(
                    Color.getHSBColor(designHue, 0.65f, brightness));

            drawTurningSquare(0, 0, 440, angle);
        }
    }

    public static void drawTurningSquare(int x, int y, int side, int angle) {
        double cornerRadius = side / Math.sqrt(2);

        p.liftPen();

        for (int corner = 0; corner <= 4; corner++) {
            double radians = Math.toRadians(angle + 45 + corner * 90);

            int cornerX = x + (int) Math.round(cornerRadius * Math.cos(radians));
            int cornerY = y + (int) Math.round(cornerRadius * Math.sin(radians));

            p.moveTo(cornerX, cornerY);
            p.lowerPen();
        }

        p.liftPen();
    }

    public static void drawCornerDecoration(int x, int y) {
        drawFlower(x, y, 85,
                Color.getHSBColor(designHue, 0.45f, 0.95f));

        drawFlower(x, y, 60,
                Color.getHSBColor(designHue, 0.75f, 0.65f));

        for (int angle = 0; angle < 90; angle += 4) {
            float brightness = 0.55f + (angle / 90.0f) * 0.30f;

            p.setPenColor(
                    Color.getHSBColor(designHue, 0.65f, brightness));

            drawTurningSquare(x, y, 360, angle);
        }
    }

    public static void drawSurroundingShapes() {
        drawCornerDecoration(-400, 400);
        drawCornerDecoration(400, 400);
        drawCornerDecoration(-400, -400);
        drawCornerDecoration(400, -400);
    }

    

    public static void drawSmallDecorations() {
        Color flowerColor = Color.getHSBColor(designHue, 0.65f, 0.75f);
        drawFlower(0, 355, 20, flowerColor);
        drawFlower(0, -355, 20, flowerColor);
        drawFlower(-355, 0, 20, flowerColor);
        drawFlower(355, 0, 20, flowerColor);

        p.setPenColor(
                Color.getHSBColor(designHue, 0.75f, 0.55f));

        // Beside the top flower.
        drawTurningSquare(-50, 355, 15, 45);
        drawTurningSquare(50, 355, 15, 45);

        // Beside the bottom flower.
        drawTurningSquare(-50, -355, 15, 45);
        drawTurningSquare(50, -355, 15, 45);

        // Above and below the left flower.
        drawTurningSquare(-355, 50, 15, 45);
        drawTurningSquare(-355, -50, 15, 45);

        // Above and below the right flower.
        drawTurningSquare(355, 50, 15, 45);
        drawTurningSquare(355, -50, 15, 45);
    }

    public static void drawBackground() {
        Color backgroundColor = Color.getHSBColor(designHue, 0.06f, 0.98f);

        p.liftPen();
        p.moveTo(-400, -400);
        p.setPenColor(backgroundColor);
        p.lowerPen();

        p.startFill(backgroundColor);

        p.moveTo(400, -400);
        p.moveTo(400, 400);
        p.moveTo(-400, 400);
        p.moveTo(-400, -400);

        p.finishFill();
        p.liftPen();
    }
}