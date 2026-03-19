
import processing.core.*;

public class App extends PApplet {

    PVector avoid1 = new PVector(random(65, 100), random(65, 100));
    PVector avoid2 = new PVector(random(190, 300), random(110, 160));
    PVector avoid3 = new PVector(random(90, 200), random(170, 220));
    PVector avoid4 = new PVector(random(290, 400), random(230, 300));
    // PVector hero = new PVector(20, 20);
    // PVector goal = new PVector(370, 270);

    float heroX=20;
    float heroY=20;
    float goalX=370;
    float goalY=270;
    float size1 = random(40, 70);
    float size2 = random(40, 70);
    float size3 = random(40, 70);
    float size4 = random(40, 70);
    float sizeHero = 30;
    float d1 = dist(heroX, heroY, avoid1.x, avoid1.y);
    float d2 = dist(heroX, heroY, avoid2.x, avoid2.y);
    float d3 = dist(heroX, heroY, avoid3.x, avoid3.y);
    float d4 = dist(heroX, heroY, avoid4.x, avoid4.y);
    float dbase = dist(heroX, heroY, goalX, goalY);
    float speed = 2;
    float distanceFromEnemy = 0;

    PImage startScreen;
    PImage endScreen;

    int highscore = 0;
    int score = 0;
    int i = 0;
    int attempt = 1;
    int timeLeft = 660;

    boolean gameStarted = false;
    boolean gameOver = false;
    boolean successScreen = false;
    boolean moveUp = false;
    boolean moveDown = false;
    boolean moveRight = false;
    boolean moveLeft = false;
    boolean hittable = true;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(150);
        startScreen = loadImage("src/Data/RetroImage.jpg");
        startScreen.resize(width, height);

        endScreen = loadImage("src/Data/Death.jpg");
        endScreen.resize(width, height);
        // Setting up the startscreen
    }

    public void settings() {
        size(400, 300);
    }

    public void startScreen() {
        background(startScreen);
        textSize(30);
        text("Press SPACE to start", 80, 100);
        textSize(20);
        text("Press RIGHT arrow to move RIGHT.", 70, 130);
        text("Press LEFT arrow to move LEFT.", 70, 160);
        text("Press UP arrow to move UP.", 70, 190);
        text("Press DOWN arrow to move DOWN.", 70, 220);
    }

    public void draw() {
        if (gameStarted == false) {
            startScreen();
        } else if (successScreen == false && gameStarted == true) {
            background(105, 206, 235);
            heroActions();
            hero();
            goal();
            enemyActions();
            death();

            if (checkDeath(avoid1) || checkDeath(avoid2) || checkDeath(avoid3)
                    || checkDeath(avoid4)) {
                gameOver = true;
            }
            endScreen();
            textSize(20);
            text(attempt, 380, 20);
            // this is the main playing screen and what happens if you hit the enemies
            if (gameStarted == true) {
                timeLeft--;
            } else if (gameStarted == false || gameOver == true) {
                timeLeft = 660;
            }
            if (timeLeft <= 0) {
                gameOver = true;
                timeLeft = 660;
            }
            text(timeLeft / 60, 350, 20);

        } else {
            success();
            successScreen();
        }

    }

    public boolean checkDeath(PVector enemy) {

        if (hittable == false) {
            return false;
        }
        distanceFromEnemy = dist(heroX, heroY, enemy.x, enemy.y);
        if (distanceFromEnemy <= size1 / 2 + sizeHero / 2 || distanceFromEnemy <= size2 / 2 + sizeHero / 2
                || distanceFromEnemy <= size3 / 2 + sizeHero / 2 || distanceFromEnemy <= size4 / 2 + sizeHero / 2) {
            return true;
        } else {
            return false;
        }
        // this is what kills you
    }

    public void death() {
        if (d1 <= 20 + size1 / 2 || d2 <= 20 + size2 / 2 || d3 <= 20 + size3 / 2 ||
                d4 <= 20 + size4 / 2) {
            speed = 2;
            attempt++;
            // these are things that I wanted done after death
        }
    }

    public void endScreen() {

        if (gameOver == true) {
            background(endScreen);
            textSize(20);
            fill(255);
            text("GAME OVER. PRESS SPACE TO TRY AGAIN", 40, 100);
            textSize(30);
            text("SCORE: " + score, 100, 175);
            text("HIGHSCORE: " + highscore, 100, 205);
            speed = 1;
            if (score > highscore) {
                highscore = score;
            }
            timeLeft = 660;
        } else {
            gameStarted = true;
            // this is the end screen
        }
    }

    public void goal() {
        fill(255);
        strokeWeight(2);
        stroke(0, 200, 0);
        ellipse(goalX, goalY, 30, 30);
        // this is establishing what the goal is
    }

    public void hero() {
        fill(250);
        strokeWeight(0);
        circle(heroX, heroY, sizeHero);
    }

    public void enemyActions() {
        fill(0);
        strokeWeight(2);
        stroke(12, 24, 48);

        drawOneEnemy(avoid1, size1);
        drawOneEnemy(avoid2, size2);
        drawOneEnemy(avoid3, size3);
        drawOneEnemy(avoid4, size4);

    }

    public void drawOneEnemy(PVector enemy, float size) {
        circle(enemy.x, enemy.y, size);
        enemy.x = enemy.x + speed;
        if (enemy.x > 400 + size / 2) {
            enemy.x = 0 - size / 2;
        }
    }

    public void heroActions() {
        if (moveRight == true) {
            heroX += 1.5;
        }
        if (moveLeft == true) {
            heroX -= 1.5;
        }
        if (moveUp == true) {
            heroY -= 1.5;
        }
        if (moveDown == true) {
            heroY += 1.5;
        }
        // that's so the hero can move

        if (heroX > 385) {
            gameOver = true;
        } else if (heroX < 15) {
            gameOver = true;
        } else if (heroY > 285) {
            gameOver = true;
        } else if (heroY < 15) {
            gameOver = true;

        }
        // and this defines its borders

    }

    public void enemyMove() {
        if (keyCode == RIGHT) {
            moveUp = false;
        }
        avoid1.x = random(200);
        avoid1.y = random(150, 300);
        size1 = random(40, 65);

        avoid2.x = random(200, 400);
        avoid2.y = random(80, 150);
        size2 = random(40, 65);

        avoid3.x = random(90, 210);
        avoid3.y = random(70, 170);
        size3 = random(40, 65);

        avoid4.x = random(200, 400);
        avoid4.y = random(150, 280);
        size4 = random(40, 70);
        // this creates the enemies
    }

    public void heroMove() {
        if (keyCode == RIGHT) {
            moveRight = true;
        }
        if (keyCode == LEFT) {
            moveLeft = true;
        }
        if (keyCode == DOWN) {
            moveDown = true;
        }
        if (keyCode == UP) {
            moveUp = true;
        }
        // this keeps the movment smooth
    }

    public void heroStop() {
        if (keyCode == RIGHT) {
            moveRight = false;
        }
        if (keyCode == LEFT) {
            moveLeft = false;
        }
        if (keyCode == UP) {
            moveUp = false;
        }
        if (keyCode == DOWN) {
            moveDown = false;
        }
        // this is more smooth movment
    }

    public void keyPressed() {
        if (key == 'q') {
            hittable = !hittable;
            System.out.println("cheating!!!");
            System.out.println(hittable);
        }

        if (key == ' ') {
            if (gameOver == true || successScreen == true) {
                gameOver = false;
                gameStarted = false;
                successScreen = false;
                heroX = 20;
                heroY = 20;
                sizeHero = 30;
                score = 0;
                attempt++;
                background(random(255), random(255), random(255));
            } else if (gameStarted == false) {
                gameStarted = true;
            }
        }
        // so you can try again
        heroMove();

        success();

        // so you can win
    }

    public void keyReleased() {
        heroStop();
    }

    public void successScreen() {
        if (successScreen = true) {
            fill(255);
            textSize(30);
            text("Success!", 150, 100);
            text("Press SPACE to continue!", 60, 150);
        } else {
            successScreen = false;
        }
    }

    public void success() {
        dbase = dist(heroX, heroY, goalX, goalY);
        if(dbase<=30){
            heroX = 20;
            heroY = 20;
            score++;
            speed++;
            attempt = 0;
            fill(255);
            successScreen = true;
            enemyMove();
            timeLeft = 660;
            // so you can win
        }
    }

}
