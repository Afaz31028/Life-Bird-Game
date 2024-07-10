package GameProject2;
import java.awt.Color;
import java.awt.Graphics;

public class Bird {
    private int x, y, width, height, velocity;
    private static final int GRAVITY = 1;
    private static final int JUMP_STRENGTH = 15;

    public Bird() {
        this.x = 100;
        this.y = 250;
        this.width = 50;
        this.height = 50;
        this.velocity = 0;
    }

    public void update() {
        velocity += GRAVITY;
        y += velocity;
    }

    public void jump() {
        velocity = -JUMP_STRENGTH;
    }

//    public void render(Graphics g) {
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, width, height);
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
