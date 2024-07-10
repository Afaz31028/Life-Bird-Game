package GameProject2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
    private int score;

    public Score() {
        this.score = 0;
    }

    public void update() {
        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);
    }

    public int getScore() {
        return score;
    }
}
