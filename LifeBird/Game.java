
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Score score;
    private Timer timer;
    private int pipeSpawnCounter;
    private boolean gameOver;
    int width=800;
    int height=600;
    Image backGroundImg;
    Image birdImg;

    public Game() {
        bird = new Bird();
        pipes = new ArrayList<>();
        score = new Score();
        timer = new Timer(20, this);
        pipeSpawnCounter = 0;
        gameOver = false;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.backGroundImg = new ImageIcon(this.getClass().getResource("./frame.jpg")).getImage();
        this.birdImg = new ImageIcon(this.getClass().getResource("./bird.png")).getImage();

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new InputHandler(bird));

        // Initial pipes
        spawnPipe();
    }

    public void start() {
        timer.start();
    }
    public void draw(Graphics g) {
        g.drawImage(backGroundImg, 0, 0, width, height,null);
        g.drawImage(birdImg, bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);
    }

    private void spawnPipe() {
        int pipeHeight = (int) (Math.random() * 300) + 100;
        int gap = 200; // Increase the gap between the pipes
        pipes.add(new Pipe(800, 600 - pipeHeight, 50, pipeHeight));
        pipes.add(new Pipe(800, 0, 50, 600 - pipeHeight - gap));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            timer.stop();
            return;
        }

        bird.update();
        for (Pipe pipe : pipes) {
            pipe.update();
        }

        pipeSpawnCounter++;
        if (pipeSpawnCounter > 100) {
            spawnPipe();
            pipeSpawnCounter = 0;
        }

        // Remove off-screen pipes
        Iterator<Pipe> iterator = pipes.iterator();
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            if (pipe.getX() + pipe.getWidth() < 0) {
                iterator.remove();
            }
        }

        // Check for collisions
        if (checkCollisions() || checkFall()) {
            gameOver = true;
        }

        score.update();
        repaint();
    }

    private boolean checkCollisions() {
        for (Pipe pipe : pipes) {
            if (bird.getX() < pipe.getX() + pipe.getWidth() &&
                    bird.getX() + bird.getWidth() > pipe.getX() &&
                    (bird.getY() < pipe.getY() + pipe.getHeight() &&
                            bird.getY() + bird.getHeight() > pipe.getY())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkFall() {
        return bird.getY() + bird.getHeight() > getHeight() || bird.getY() < 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

        // Draw background
//        g.setColor(Color.CYAN);
//        g.fillRect(0, 0, getWidth(), getHeight());

        //bird.render(g);
        for (Pipe pipe : pipes) {
            pipe.render(g);
        }
        score.render(g);

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", getWidth() / 2 - 150, getHeight() / 2);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Your Score: " + score.getScore(), getWidth() / 2 - 130, getHeight() / 2+25 );
        }
    }
}
