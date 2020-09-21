import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Drawing class extends JPanel.
 * This class add a background color/design on the top and bottom of the app window.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class Drawing extends JPanel {

//    protected Figures figure1;
//    protected Figures figure2;
//    protected Figures figure3;
    protected int width;
    protected int height;

    /**
     * Constructor.
     *
     * @param width - panel width as app window width
     * @param height - panel height as app window height
     */
    public Drawing(int width, int height) {
        this.width = width;
        this.height = height;
//        figure3 =new Figures(new int[]{17,17,37}, new int[]{17,37,17}, (new Color(15,100,255)));
//        figure2 =new Figures(new int[]{15,15,35}, new int[]{15,35,15}, (new Color(100,100,255)));
//        figure1 =new Figures(new int[]{13,13,33}, new int[]{13,33,13}, (new Color(150,150,255)));
    }

    /**
     *
     * @param g - graphical object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);    // call of parent class
//        figure1.drawFigure(g);
//        figure2.drawFigure(g);
//        figure3.fillFigure(g);

        // Upper separation line / rectangles
        g.setColor(Color.lightGray);
        g.fillRect(0,0, this.width, 130);     // Filled rectangle

        // Lower separation line / rectangles
        g.setColor(Color.lightGray);
        g.fillRect(0,this.height - 160, this.width, 130);      // Filled rectangle

    }
}

/**
 * Figures class for testing.
 */
class Figures {
    protected Polygon myFigure;
    protected Color myColor;

    public Figures(int[] padX, int[] padY, Color myColor){
        myFigure = new Polygon();
        myFigure.xpoints = padX;
        myFigure.ypoints = padY;
        myFigure.npoints = padX.length;
        this.myColor = myColor;
    }
    void fillFigure(Graphics g){
        g.setColor(myColor);
        g.fillPolygon(myFigure);
    }
    void drawFigure(Graphics g){
        g.setColor(myColor);
        g.drawPolygon(myFigure);
    }

}