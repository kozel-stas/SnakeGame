package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

class Apple extends Pane {
    protected static ArrayList <Apple> list=new ArrayList<>();
    public static ArrayList <Rectangle> rect=new ArrayList<>();
    public Apple(){}
    public Apple(int Size){
        for (int i=0;i<Size;i++) {
            Apple c = new Apple();
            Random rand = new Random();
            int a, b;
            do {
                a = rand.nextInt(Main.Height / Main.SizeCub);
                b = rand.nextInt(Main.Width / Main.SizeCub);
            } while (Main.snake.Have(a, b) && Bust(a, b) == false);
            c.setX(a);
            c.setY(b);
            Rectangle s = new Rectangle(c.getY() * Main.SizeCub, c.getX() * Main.SizeCub, Main.SizeCub, Main.SizeCub);
            s.setFill(Color.RED);
            rect.add(s);
            getChildren().addAll(s);
            list.add(c);
        }
    }
    private int x,y;

    public static void addApple(){
        Apple c = new Apple();
        Random rand = new Random();
        int a, b;
        do {
            a = rand.nextInt(Main.Height / Main.SizeCub);
            b = rand.nextInt(Main.Width / Main.SizeCub);
        } while (Main.snake.Have(a, b) && Bust(a, b) == false);
        c.setX(a);
        c.setY(b);
        Rectangle s = new Rectangle(c.getY() * Main.SizeCub, c.getX() * Main.SizeCub, Main.SizeCub, Main.SizeCub);
        s.setFill(Color.RED);
        rect.add(s);
        Main.apple.getChildren().addAll(s);
        list.add(c);
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private static boolean Bust(int x,int y){
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getX()==x && list.get(i).getY()==y)
                return true;
        }
        return false;
    }
}