package util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;

public class Printscreen {

    public static void tiraPrint() throws Exception {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage screenCapturedImage = robot.createScreenCapture(screenRect);

        ImageIO.write(screenCapturedImage, "png", new File("/tmp/LabMonitor/printscreen.png"));
    }
    
    public static void enviar(){
        try {
            ServerSocket ss = new ServerSocket(2016);
            Socket s = ss.accept();
            OutputStream os = s.getOutputStream();
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", os);
            
            os.close();
            s.close();
            ss.close();
        } catch (IOException | AWTException | HeadlessException e) {
            System.err.println("Erro: " + e);
        }
    }
}
