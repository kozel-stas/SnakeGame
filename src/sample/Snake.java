package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Snake extends Pane{
    public PartofBody Head,Tail;
    public static Dir dir=Dir.Up;
    ArrayList <Rectangle> rect=new ArrayList<>();

    public Snake (){
        Random perm=new Random();
        PartofBody a1=new PartofBody();
        PartofBody a2=new PartofBody();
        PartofBody a3=new PartofBody();
        Head=a1;
        Tail=a3;
        a1.setTop(null);
        a1.setBottom(a2);
        a2.setTop(a1);
        a2.setBottom(a3);
        a3.setTop(a2);
        a3.setBottom(null);
        Head.setX(perm.nextInt((Main.Height/Main.SizeCub-9))+5);
        Head.setY(perm.nextInt((Main.Width/Main.SizeCub-9))+5);
        a2.setX(Head.getX()+1);
        a2.setY(Head.getY());
        a3.setX(a2.getX()+1);
        a3.setY(a2.getY());
        Rectangle a0=new Rectangle(a1.getY()*20,a1.getX()*20,Main.SizeCub,Main.SizeCub);
        a0.setFill(Color.DARKBLUE);
        Rectangle a01=new Rectangle(a2.getY()*20,a2.getX()*20,Main.SizeCub,Main.SizeCub);
        a01.setFill(Color.GREEN);
        Rectangle a02=new Rectangle(a3.getY()*20,a3.getX()*20,Main.SizeCub,Main.SizeCub);
        a02.setFill(Color.GREEN);
        getChildren().addAll(a0);
        getChildren().addAll(a01);
        getChildren().addAll(a02);
        rect.add(a0);
        rect.add(a01);
        rect.add(a02);
    }

    private void rewrite(){
        PartofBody a = Head.getBottom();
        int x = Head.getX(), y = Head.getY();
        while (a != null) {
            int z = a.getX(), f = a.getY();
            a.setX(x);
            a.setY(y);
            x = z;
            y = f;
            a = a.getBottom();
        }
    }

    public boolean Have(int x, int y){
        PartofBody a =Head;
        while (a!=null) {
            if (a.getX() == x && a.getY() == y)
                return true;
            a=a.getBottom();
        }
        return false;
    }

    public void Growth(){
        if (dir==Dir.Up){
            PartofBody a=new PartofBody();
            a.setX(Tail.getX()+1);
            a.setY(Tail.getY());
            a.setBottom(null);
            a.setTop(Tail);
            Tail.setBottom(a);
            Tail=a;
            Rectangle a0=new Rectangle(a.getY()*20,a.getX()*20,Main.SizeCub,Main.SizeCub);
            a0.setFill(Color.GREEN);
            getChildren().addAll(a0);
            rect.add(a0);
        }
        if (dir==Dir.Down){
            PartofBody a=new PartofBody();
            a.setX(Tail.getX()-1);
            a.setY(Tail.getY());
            a.setBottom(null);
            a.setTop(Tail);
            Tail.setBottom(a);
            Tail=a;
            Rectangle a0=new Rectangle(a.getY()*20,a.getX()*20,Main.SizeCub,Main.SizeCub);
            a0.setFill(Color.GREEN);
            getChildren().addAll(a0);
            rect.add(a0);
        }
        if (dir==Dir.Right){
            PartofBody a=new PartofBody();
            a.setX(Tail.getX());
            a.setY(Tail.getY()+1);
            a.setBottom(null);
            a.setTop(Tail);
            Tail.setBottom(a);
            Tail=a;
            Rectangle a0=new Rectangle(a.getY()*20,a.getX()*20,Main.SizeCub,Main.SizeCub);
            a0.setFill(Color.GREEN);
            getChildren().addAll(a0);
            rect.add(a0);
        }
        if (dir==Dir.Left){
            PartofBody a=new PartofBody();
            a.setX(Tail.getX());
            a.setY(Tail.getY()-1);
            a.setBottom(null);
            a.setTop(Tail);
            Tail.setBottom(a);
            Tail=a;

            Rectangle a0=new Rectangle(a.getY()*20,a.getX()*20,Main.SizeCub,Main.SizeCub);
            a0.setFill(Color.GREEN);
            getChildren().addAll(a0);
            rect.add(a0);
        }
    }

    public boolean HaveApple(int x,int y){
        for (int i=0;i<Apple.list.size();i++){
            if (Apple.list.get(i).getX()==x && Apple.list.get(i).getY()==y){
                Growth();
                Apple.rect.get(i).setVisible(false);
                Apple.rect.remove(i);
                Apple.addApple();
                Apple.list.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean MoveTop(){
        if (Head.getX()!=0 && Have(Head.getX() - 1,Head.getY())==false){
            HaveApple(Head.getX() - 1,Head.getY());
            PartofBody a;
            rewrite();
            Head.setX(Head.getX() - 1);
            a = Head;
            for (int i = 0; i < rect.size(); i++) {
                rect.get(i).setX(a.getY() * 20);
                rect.get(i).setY(a.getX() * 20);
                a = a.getBottom();
            }
        }
        else System.exit(0);
        return false;
    }

    public boolean MoveLeft(){
        try{
            if (Head.getY() != 0 && Have(Head.getX(), Head.getY() - 1) == false) {
                HaveApple(Head.getX(), Head.getY() - 1);
                PartofBody a;
                rewrite();
                Head.setY(Head.getY() - 1);
                a = Head;
                for (int i = 0; i < rect.size(); i++) {
                    rect.get(i).setX(a.getY() * 20);
                    rect.get(i).setY(a.getX() * 20);
                    a = a.getBottom();
                }
            } else System.exit(0);
            return false;
        }
        catch (java.lang.NullPointerException e){
            for (int i=0;i<rect.size();i++);
        }
        return false;
    }

    public boolean MoveRight(){
        if (Head.getY()!=Main.Width/Main.SizeCub-1 && Have(Head.getX(),Head.getY()+1)==false) {
            HaveApple(Head.getX(),Head.getY()+1);
            PartofBody a;
            rewrite();
            Head.setY(Head.getY() + 1);
            a = Head;
            for (int i = 0; i < rect.size(); i++) {
                rect.get(i).setX(a.getY() * 20);
                rect.get(i).setY(a.getX() * 20);
                a = a.getBottom();
            }
        }
        else System.exit(0);
        return false;
    }

    public boolean MoveBottom(){
        if (Head.getX()!=Main.Height/Main.SizeCub-1 && Have(Head.getX() + 1,Head.getY())==false) {
            HaveApple(Head.getX() + 1,Head.getY());
            PartofBody a;
            rewrite();
            Head.setX(Head.getX() + 1);
            a = Head;
            for (int i = 0; i < rect.size(); i++) {
                rect.get(i).setX(a.getY() * 20);
                rect.get(i).setY(a.getX() * 20);
                a = a.getBottom();
            }
        }
        else System.exit(0);
        return false;
    }

    public void Move(){
        if (dir==Dir.Up) MoveTop();
        if (dir==Dir.Left) MoveLeft();
        if (dir==Dir.Right) MoveRight();
        if (dir==Dir.Down) MoveBottom();
    }
}
