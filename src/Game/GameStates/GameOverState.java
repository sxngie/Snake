//package Game.GameStates;
//
//import Main.Handler;
//import Resources.Images;
//import UI.UIImageButton;
//import UI.UIManager;
//
//import java.awt.*;
//
/////**
//// * Created by AlexVR on 7/1/2018.
//// */
//public class GameOverState extends State {
//
//    private int count = 0;
//    private UIManager uiManager;
//
//    public GameOverState(Handler handler) {
//        super(handler);
//        uiManager = new UIManager(handler);
//        handler.getMouseManager().setUimanager(uiManager);
//        
//        uiManager.addObjects(new UIImageButton(56, 223,128, 64, Images.butstart, new ClickListlener() 
//        		{
//        	
//        	@Override
//        	public void onClick() {
//        		handler.getMouseManager().setUimanager(null);
//        		handler.getGame().reStart();
//       		State.setState(handler.getGame().gameState);
//        	}
//        }));
//        	
//        uiManager.addObjects(new UIImageButton(56, (223+(64+16))+(64+16), 128, 64, Images.BTitle, () -> {
//            handler.getMouseManager().setUimanager(null);
//            State.setState(handler.getGame().menuState);
//        }));
//    }
////}
//////handler.getworld().body.getlast
////    }
////
////    public void checkCollisionAndMove(){
////        handler.getWorld().playerLocation[xCoord][yCoord]=false;
////        int x = xCoord; 
////        int y = yCoord;
////        switch (direction){
////            case "Left":
////                if(xCoord==0){
////                    kill();
////                }else{
////                    xCoord--;
////                }
////                break;
////            case "Right":
////                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
////                    kill();
////                }else{
////                    xCoord++;
////                }
////                break;
////            case "Up":
////                if(yCoord==0){
////                    kill();
////                }else{
////                    yCoord--;
////                }
////                break;
////            case "Down":
////                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
////                    kill();
////                }else{
////                    yCoord++;
////                }
////                break;
////        }
////
////
////    
////    public void kill(){
////        lenght = 0;
////        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
////            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
////
////                handler.getWorld().playerLocation[i][j]=false;
////
////            }
////        }
////    }
////    
//
//	@Override
//	public void tick() {
//		handler.getMouseManager().setUimanager(uiManager);
//		uiManager.tick();
//		count += 1;
//		if (count >= 30 ) {
//			count = 30;
//		}
//		if (handler.getKeyManager().pbutt && count >= 30) {
//			count = 0;
//			
//			State.setState(handler.getGame().gameState);
//		}
//	}
//	
//    @Override
//    public void render(Graphics g) {
//        g.drawImage(Images.GameOver,0,0,800,800,null);
//        uiManager.Render(g);
//
//    }
//}
