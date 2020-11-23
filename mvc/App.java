package mvc;

import javax.swing.JFrame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import mvc.controller.Controller;
import mvc.view.View;

public class App extends JFrame {

  private static final long serialVersionUID = 1L;

  private GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
  private GraphicsDevice device = environment.getDefaultScreenDevice();
  private boolean isFullScreenAble = device.isFullScreenSupported();
  private boolean isFullScreenNow = false;

  private Controller controller = new Controller(this);
  public View view = new View(controller);

  public App() {
    add(view);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(true);
    setTitle("Lesson");
    setVisible(true);
  }

  public void toggleFS() {
    if (isFullScreenNow) {
      isFullScreenNow = false;
      device.setFullScreenWindow(null);
    } else if (isFullScreenAble) {
      isFullScreenNow = true;
      device.setFullScreenWindow(this);
    } else {
      System.out.println("Fullscreen mode is not supported.");
    }
  }

  public static void make() {
    new App();
  }

}
