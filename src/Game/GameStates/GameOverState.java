//package Game.GameStates;
//
//import Main.Handler;
//import Resources.Images;
//import UI.UIImageButton;
//import UI.UIManager;
//
//import java.awt.*;
//
//import Game.Entities.Dynamic.Tail;
//
//
////// * Created by AlexVR on 7/1/2018.
//
//public class GameOverState extends State {
//
//    private int count = 0;
//    public int lenght;
//    private UIManager uiManager; {
//
////    	for 'Game Over' screen when crashing against tail or wall
//    if (handler.getWorld().playerLocation == handler.getWorld().body() ) {
//    	kill();
//    }
//  
//
//
//    }
//
//    @Override
//    public void tick() {
//        handler.getMouseManager().setUimanager(uiManager);
//        uiManager.tick();
//        count++;
//        if( count>=30){
//            count=30;
//        }
//        if(handler.getKeyManager().pbutt && count>=30){
//            count=0;
//
//            State.setState(handler.getGame().gameState);
//        }
//
//
//    }
//    
//    public void kill(){
//        lenght  = 0;
//        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
//            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
//
//                handler.getWorld().playerLocation[i][j]=false;
//
//            }
//        }
//    }
//
//@Override
//    public void render(Graphics g) {
//        g.drawImage(Images.GameOver,0,0,800,600,null);
//        uiManager.Render(g);
//        }
//}
