package Apperance;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SpriteSlicer {
    private static final int TILESIZE = 32;
    private HashMap<Integer, BufferedImage> images;
    private static SpriteSlicer instance;

    private SpriteSlicer() {
        images = new HashMap<>();
        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("spritesheet.png"));
            int counter = 1;
            for (int x = 0; x < image.getWidth() / TILESIZE; x++) {
                for (int y = 0; y < image.getHeight() / TILESIZE; y++) {
                    this.images.put(counter,image.getSubimage(x,y,TILESIZE,TILESIZE));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized SpriteSlicer getInstance(){
        if (instance == null ){
            instance = new SpriteSlicer();
        }
        return instance;
    }

    public BufferedImage getImage(int gID) {
        return images.get(gID);
    }
}
