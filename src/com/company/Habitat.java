package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Habitat extends JPanel implements Runnable{
    public boolean going= true;//,go=true;
    private Singleton singleton;
    private ConcreteFactory factory= new ConcreteFactory();
    public AbstractBall ball;
    public AbstractPlatform platform;
    private int sizeX=50,sizeY=25;
    public Interface anInterface;
    private Game game;

    public Habitat(Singleton singleton,Interface anInterface,Game game) {
        this.singleton = singleton;
        new Thread(this).start();
        platform = new Platform(380, 600, 100, 25,this);
        ball = platform.getBall();
        new Thread(platform).start();
        this.anInterface=anInterface;
        setupHabitat();
        this.game=game;
    }

    public void paint(Graphics graphics) {//отрисовывает все объекты
        super.paintComponent(graphics);
        for(int i=0;i<singleton.getVector().size();i++){
            singleton.getVector().get(i).painting(graphics);
            if(check(singleton.getVector().get(i))){//проверка на сталкивание объектов
                removeBlock(singleton.getVector().get(i));//удаление блока которого касается шарик
            }
        }
        platform.painting(graphics);//рисуется платформа
        if (going)
            ball.painting(graphics);//Рисуется шарик
    }

    public boolean check(AbstractActor actor){//проверка с какой стороной шарик столкнулся действует для стены,блоков и панели
        int direct;//для определния в какую сторону должен отталкиваться
        //столкновение верхней стороной шарика с нижней стороной объекта
        if(actor.down>=ball.up&& ball.centerX >= actor.left && ball.centerX <= actor.right&&ball.down>=actor.down)
        {
            direct=3;
            ball.onCollision(actor,direct);
            return true;
        }
        //столкновение левой стороны шарика с правой стороной объекта
        if(actor.right>=ball.left&&actor.right<=ball.right&&ball.centerY >= actor.up && ball.centerY <= actor.down){
            direct=1;
            ball.onCollision(actor,direct);
            return true;
        }
        //столкновение правой стороны шарика с левой стороной объекта
        if(actor.left<=ball.right&&actor.right>=ball.right&&ball.centerY >= actor.up && ball.centerY <= actor.down){
            direct=2;
            ball.onCollision(actor,direct);
            return true;
        }
        //столкновение нижней стороны шарика с верхней стороной объекта
        if(actor.up<=ball.down&&actor.right>=ball.centerX&&actor.left<=ball.centerX&&ball.up<=actor.up)
        {

            direct=4;
            ball.onCollision(actor,direct);
            return true;
        }
        //столкновение правым нижним углом объекта
        if(Math.sqrt(Math.pow(ball.centerX-actor.right,2)+Math.pow(ball.centerY-actor.down,2))<=ball.getSizeX()/2){
            direct=5;
            ball.onCollision(actor,direct);
            return true;
        }
        //столкновение правым вверхним углом объекта
        if(Math.sqrt(Math.pow(actor.right-ball.centerX,2)+Math.pow(actor.up-ball.centerY,2))<=ball.getSizeX()/2)
        {
            direct=6;
            ball.onCollision(actor,direct);
            return true;
        }
        //столкновение левым вверхним углом объекта
        if(Math.sqrt(Math.pow(ball.centerX-actor.left,2)+Math.pow(ball.centerY-actor.up,2))<=ball.getSizeX()/2)
        {
            direct=7;
            ball.onCollision(actor,direct);
            return true;
        }
        //столкновение левым нижним углом объекта
        if(Math.sqrt(Math.pow(actor.left-ball.centerX,2)+Math.pow(actor.down-ball.centerY,2))<=ball.getSizeX()/2){
            direct=8;
            ball.onCollision(actor,direct);
            return true;
        }

        return false;
    }

    public void removeBlock(AbstractActor actor){//удаляет блок которого касается шарик
        if(actor instanceof AbstractBlock)
        {
            singleton.getVector().remove(actor);
            singleton.AddPoints(((AbstractBlock) actor).points);

            //Проблема

            //А если очков на какой-то карте будет не 8000?
            //Нужно, чтобы конец игры запускался при отсутствии неразбитых блоков на карте
            if(singleton.points==8000)// если все шарики убиты, то игра закончилась
            {
                game.GameOver();//завершение работы
            }
        }
    }

    //Bug fix

    //Если шарик перелетает ниже положения платформы, то он уничтожается
    public void BallMissed () {
        singleton.life-=1;
        anInterface.repaint();
        if (singleton.life == 0)//когда не осталось жизней
        {
            game.GameOver();//конец игры
            ball.DestroyBall();//ставит шарик в исходную
            platform.ToggleBallMovement();
        }
        else {
            ball.DestroyBall();
            platform.ToggleBallMovement();
        }
    }

    public void setupHabitat()//создание сетки блоков
    {
        factory.createWall(0,0,10,700);
        factory.createWall(825,0,100,700);
        factory.createWall(0,0,840,10);
        factory.createWall(0,652,850,100);
       for(int i=0;i<10;i++) {
            for (int j = 0; j < 8; j++) {
                factory.createBlock(60 + i*75, 300 - j*35, sizeX, sizeY);
            }
        }
        singleton.getVector().add(platform);
    }

    @Override
    public void run()//поток для работы всех объектов
    {
        while (true) {
            try {
                Thread.sleep(10);
                repaint();//перерисовка объектов
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}