import processing.core.*;

public class App extends PApplet {
    float heroX = 20;
    float heroY = 20;
    float avoidX1 = 100;
    float avoidY1 = 200;
    float avoidX2 = 300;
    float avoidY2 = 100;
    float avoidX3 = 70;
    float avoidY3 = 70;
    float avoidX4 = 300;
    float avoidY4 = 200;
    float size1 = random(20, 70);
    float size2 = random(20, 70);
    float size3 = random(20, 70);
    float size4 = random(20, 70);
    float d1 = dist(heroX, heroY, avoidX1, avoidY1);
    float d2 = dist(heroX, heroY, avoidX2, avoidY2);
    float d3 = dist(heroX, heroY, avoidX3, avoidY3);
    float d4 = dist(heroX, heroY, avoidX4, avoidY4);
    float dbase = dist(heroX, heroY, 385, 285);
    float speed=1;

    PImage startScreen;
    PImage endScreen;

    int highscore=0;

    boolean gameStarted=false;
    boolean gameOver=false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(150);
        startScreen=loadImage("src/Data/RetroImage.jpg");
        startScreen.resize(width, height);

        endScreen=loadImage("src/Data/Death.jpg");
        endScreen.resize(width,height);

    }

    public void settings() {
        size(400, 300);
    }

    public void draw() {
    float d1 = dist(heroX, heroY, avoidX1, avoidY1);   
    float d2 = dist(heroX,heroY,avoidX2,avoidY2);
    float d3 = dist(heroX,heroY,avoidX3,avoidY3);
    float d4 = dist(heroX,heroY,avoidX4,avoidY4);



    if(gameStarted==false){
        background(startScreen);
        // image(startScreen,0,0);
        textSize(30);
        text("Press SPACE to start", 80,100);
    }else{

    
        background(105, 206, 235);
        
        fill(250);
        strokeWeight(0);
        ellipse(heroX, heroY, 30, 30);

        fill(255);
        strokeWeight(2);
        stroke(0, 200, 0);
        ellipse(385, 285, 26, 26);

        fill(0);
        strokeWeight(2);
        stroke(12,24,48);
        circle(avoidX1,avoidY1,size1);
        avoidX1=avoidX1+speed;
       if (avoidX1>=400){
        avoidX1=0;
    }
    
        

        fill(0);
        strokeWeight(2);
        stroke(12,24,48);
        circle(avoidX2,avoidY2,size2);
        avoidX2=avoidX2+speed;
       if (avoidX2>=400){
        avoidX2=0;
       }

        fill(0);
        strokeWeight(2);
        stroke(12,24,48);
        circle(avoidX3,avoidY3,size3);
        avoidY3=avoidY3+speed;
       if (avoidY3>=300){
        avoidY3=0;
       }

        fill(0);
        strokeWeight(2);
        stroke(12,24,48);
        circle(avoidX4,avoidY4,size4);
        avoidY4=avoidY4+speed;
       if (avoidY4>=300){
        avoidY4=0;}
        
        if(d1<=20+size1/2||d2<=20+size2/2||d3<=20+size3/2||d4<=20+size4/2){
            heroX=20;
            heroY=20;
            speed=1;
            gameOver=true;
            
        }
        if(gameOver==true){
            background(endScreen);
            textSize(20);
            fill(255);
            text("GAME OVER. PRESS SPACE TO TRY AGAIN", 40,100);
            textSize(40);
            text(highscore,200,175);
        }
    }
        
    }
        

    

    public void keyPressed(){
        float dbase=dist(heroX,heroY,385,285);

        if(key==' '){
            gameStarted=true;
            gameOver=false;
        }
    
        if (keyCode == RIGHT) {
            heroX = heroX + 3;
        } else if (keyCode == LEFT) {
            heroX = heroX - 3;
        } else if (keyCode == UP) {
            heroY = heroY - 3;
        } else if (keyCode == DOWN) {
            heroY = heroY + 3;
        }
    
    
       
            if(dbase<=33){
            println("Yay!");
            heroX=20;
            heroY=20;
            highscore++;
            speed++;

            avoidX1=random(200);
            avoidY1=random(150,300);
            size1=random(20,70);
            
            avoidX2=random(200,400);
            avoidY2=random(150);
            size2=random(20,70);
            
            avoidX3=random(90,210);
            avoidY3=random(70,170);
            size3=random(20,70);
            
            avoidX4=random(200,400);
            avoidY4=random(150,280);
            size4=random(20,70);
           }
    }
    
}

