package Worlds;

import Game.Entities.Dynamic.Player;
import Game.Entities.Dynamic.Tail;
import Game.Entities.Static.Apple;
import Main.Handler;

import java.awt.*;
import java.util.LinkedList;


/**
 * Created by AlexVR on 7/2/2018.
 */
public abstract class WorldBase {

    //How many pixels are from left to right
    //How many pixels are from top to bottom
    //Must be equal
    public int GridWidthHeightPixelCount;

    //automatically calculated, depends on previous input.
    //The size of each box, the size of each box will be GridPixelsize x GridPixelsize.
    public int GridPixelsize;

    public Player player;

    protected Handler handler;


    public Boolean appleOnBoard;
    protected Apple apple;
    public Boolean[][] appleLocation;


    public Boolean[][] playerLocation;

    public LinkedList<Tail> body = new LinkedList<>();


    public WorldBase(Handler handler){
        this.handler = handler;

        appleOnBoard = false;


    }
    public void tick(){

    }
    public void render(Graphics g){
        for (int i = 0; i <= 780; i = i + GridPixelsize) {
            g.setColor(new Color(160,160,160));
            g.drawLine(0, i, handler.getWidth() -110 , i);
            g.drawLine(i,0,i,handler.getHeight());

        }

//    	public void render(Graphics g1) {
//		String n = toString(player.Score()); 
//		Graphics2D g1 = (Graphics2D) g1;
//		g1.setColor(Color.GREEN); 
//		g1.drawString("SCORE: " + n, 800, 390);
//	}
//		


    }

}
