package mvc.view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;

import mvc.controller.Controller;

public class View extends Canvas {

  private static final long serialVersionUID = 1L;
  private static final double ASPECT_RATIO = 1; // width to height ratio
  private static final int HEIGHT = 600;
  private static final int WIDTH = 800;

  private BufferedImage img;
  private Rectangle displayRect = new Rectangle(0, 0, 0, 0);

  public View(Controller controller) {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    try {
      img = ImageIO.read(new File("mvc/resources/chess-board.png"));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    addComponentListener(controller);
    addMouseListener(controller);
  }

  public void noticeResize(Dimension updatedSize) {
    if (updatedSize.height * ASPECT_RATIO > updatedSize.width) { // use full width
      displayRect.width = updatedSize.width;
      displayRect.height = (int) (displayRect.width / ASPECT_RATIO);
      displayRect.x = 0;
      displayRect.y = (int) ((updatedSize.height - displayRect.width / ASPECT_RATIO) / 2);
    } else { // use full height
      displayRect.height = updatedSize.height;
      displayRect.width = (int) (displayRect.height * ASPECT_RATIO);
      displayRect.x = (int) ((updatedSize.width - displayRect.height * ASPECT_RATIO) / 2);
      displayRect.y = 0;
    }
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(img, displayRect.x, displayRect.y, displayRect.width, displayRect.height, this);
  }

}
