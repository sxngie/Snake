package Game.Entities.Dynamic;

import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

	public int lenght;
	public boolean justAte;
	private Handler handler;

	public int xCoord;
	public int yCoord;

	public int moveCounter;
	public int speedCounter;
	
	public int score;

	public String direction;// is your first name one?

	public Player(Handler handler) {
		this.handler = handler;
		xCoord = 0;
		yCoord = 0;
		moveCounter = 0;
		direction = "Right";
		justAte = false;
		lenght = 1;
		speedCounter = 3;

	}

	public void tick() {
		suicide();
		moveCounter++;
		if (moveCounter >= speedCounter) {
			checkCollisionAndMove();
			moveCounter = 0;
		}

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
			if (direction != "Up" && direction != "Left" && direction != "Right" && lenght >= 1) {
				direction = "Down";
			} else {
				direction = "Up";

			}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
			if (direction != "Down" && direction != "Left" && direction != "Right" && lenght >= 1) {
				direction = "Up";
			} else {
				direction = "Down";

			}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
			if (direction != "Left" && direction != "Up" && direction != "Down" && lenght >= 1) {
				direction = "Right";
			} else {
				direction = "Left";

			}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
			if (direction != "Right" && direction != "Up" && direction != "Down" && lenght >= 1) {
				direction = "Left";
			} else {
				direction = "Right";

			}
		}

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)) {
			speedCounter--;
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {
			speedCounter++;
		}

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
			lenght++;
			Tail tail = new Tail(this.xCoord-1, this.yCoord-1, handler);
			handler.getWorld().body.addLast(tail);
		}

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
			State.setState(handler.getGame().pauseState);
		}
	}

	public void checkCollisionAndMove() {
		handler.getWorld().playerLocation[xCoord][yCoord] = false;
		int x = xCoord;
		int y = yCoord;
		switch (direction) {
		case "Left":
			if (xCoord == 0) {
				kill();
			} else {
				xCoord--;
			}
			break;
		case "Right":
			if (xCoord == handler.getWorld().GridWidthHeightPixelCount - 1) {
				kill();
			} else {
				xCoord++;
			}
			break;
		case "Up":
			if (yCoord == 0) {
				kill();
			} else {
				yCoord--;
			}
			break;
		case "Down":
			if (yCoord == handler.getWorld().GridWidthHeightPixelCount - 1) {
				kill();
			} else {
				yCoord++;
			}
			break;
		}
		handler.getWorld().playerLocation[xCoord][yCoord] = true;

		if (handler.getWorld().appleLocation[xCoord][yCoord]) {
			Eat();
		}

		if (!handler.getWorld().body.isEmpty()) {
			handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body
					.getLast().y] = false;
			handler.getWorld().body.removeLast();
			handler.getWorld().body.addFirst(new Tail(x, y, handler));
		}

	}
	
	public void suicide(){
		for (Tail tail : handler.getWorld().body) {
			if (xCoord == tail.x && yCoord == tail.y) {
				kill();
			}
		}
	}

	public void render(Graphics g, Boolean[][] playeLocation) {
		Random r = new Random();
		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
				g.setColor(new Color(0,102,0));

				if (playeLocation[i][j]) {
					g.fillRect((i * handler.getWorld().GridPixelsize), (j * handler.getWorld().GridPixelsize),
							handler.getWorld().GridPixelsize, handler.getWorld().GridPixelsize);
				}if(handler.getWorld().appleLocation[i][j]){
					g.setColor(Color.white);
					g.fillRect((i * handler.getWorld().GridPixelsize), (j * handler.getWorld().GridPixelsize),
							handler.getWorld().GridPixelsize, handler.getWorld().GridPixelsize);
				}

			}
		}

	}

	public void Eat() {
		lenght++;
		Tail tail = null;
		handler.getWorld().appleLocation[xCoord][yCoord] = false;
		handler.getWorld().appleOnBoard = false;
		switch (direction) {
		case "Left":
			if (handler.getWorld().body.isEmpty()) {
				if (this.xCoord != handler.getWorld().GridWidthHeightPixelCount - 1) {
					tail = new Tail(this.xCoord + 1, this.yCoord, handler);
				} else {
					if (this.yCoord != 0) {
						tail = new Tail(this.xCoord, this.yCoord - 1, handler);
					} else {
						tail = new Tail(this.xCoord, this.yCoord + 1, handler);
					}
				}
			} else {
				if (handler.getWorld().body.getLast().x != handler.getWorld().GridWidthHeightPixelCount - 1) {
					tail = new Tail(handler.getWorld().body.getLast().x + 1, this.yCoord, handler);
				} else {
					if (handler.getWorld().body.getLast().y != 0) {
						tail = new Tail(handler.getWorld().body.getLast().x, this.yCoord - 1, handler);
					} else {
						tail = new Tail(handler.getWorld().body.getLast().x, this.yCoord + 1, handler);

					}
				}

			}
			break;
		case "Right":
			if (handler.getWorld().body.isEmpty()) {
				if (this.xCoord != 0) {
					tail = new Tail(this.xCoord - 1, this.yCoord, handler);
				} else {
					if (this.yCoord != 0) {
						tail = new Tail(this.xCoord, this.yCoord - 1, handler);
					} else {
						tail = new Tail(this.xCoord, this.yCoord + 1, handler);
					}
				}
			} else {
				if (handler.getWorld().body.getLast().x != 0) {
					tail = (new Tail(handler.getWorld().body.getLast().x - 1, this.yCoord, handler));
				} else {
					if (handler.getWorld().body.getLast().y != 0) {
						tail = (new Tail(handler.getWorld().body.getLast().x, this.yCoord - 1, handler));
					} else {
						tail = (new Tail(handler.getWorld().body.getLast().x, this.yCoord + 1, handler));
					}
				}

			}
			break;
		case "Up":
			if (handler.getWorld().body.isEmpty()) {
				if (this.yCoord != handler.getWorld().GridWidthHeightPixelCount - 1) {
					tail = (new Tail(this.xCoord, this.yCoord + 1, handler));
				} else {
					if (this.xCoord != 0) {
						tail = (new Tail(this.xCoord - 1, this.yCoord, handler));
					} else {
						tail = (new Tail(this.xCoord + 1, this.yCoord, handler));
					}
				}
			} else {
				if (handler.getWorld().body.getLast().y != handler.getWorld().GridWidthHeightPixelCount - 1) {
					tail = (new Tail(handler.getWorld().body.getLast().x, this.yCoord + 1, handler));
				} else {
					if (handler.getWorld().body.getLast().x != 0) {
						tail = (new Tail(handler.getWorld().body.getLast().x - 1, this.yCoord, handler));
					} else {
						tail = (new Tail(handler.getWorld().body.getLast().x + 1, this.yCoord, handler));
					}
				}

			}
			break;
		case "Down":
			if (handler.getWorld().body.isEmpty()) {
				if (this.yCoord != 0) {
					tail = (new Tail(this.xCoord, this.yCoord - 1, handler));
				} else {
					if (this.xCoord != 0) {
						tail = (new Tail(this.xCoord - 1, this.yCoord, handler));
					} else {
						tail = (new Tail(this.xCoord + 1, this.yCoord, handler));
					}
					System.out.println("Tu biscochito");
				}
			} else {
				if (handler.getWorld().body.getLast().y != 0) {
					tail = (new Tail(handler.getWorld().body.getLast().x, this.yCoord - 1, handler));
				} else {
					if (handler.getWorld().body.getLast().x != 0) {
						tail = (new Tail(handler.getWorld().body.getLast().x - 1, this.yCoord, handler));
					} else {
						tail = (new Tail(handler.getWorld().body.getLast().x + 1, this.yCoord, handler));
					}
				}

			}
			break;
		}
		handler.getWorld().body.addLast(tail);
		handler.getWorld().playerLocation[tail.x][tail.y] = true;
		score +=5;
		handler.getGame().Score = score +"";
	}
	
	public void renderScore(Graphics g){
		Graphics2D g1 = (Graphics2D) g;
		for(int i=0; i <= 780; i = i + handler.getWorld().GridPixelsize){
			Font stringFont = new Font("Dialog",Font.PLAIN, 20);
			g1.setFont(stringFont);
			g1.setColor(Color.white);
			g1.drawString("Score " + Integer.toString(this.score),800, 380);
			g1.drawLine(0, i, handler.getWidth()-110, i);
			g1.drawLine(i, 0, i, handler.getHeight());
			
			
		}
		
	}
	
	public void kill() {
		lenght = 0;
		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
				handler.getWorld().playerLocation[i][j] = false;
				State.setState(handler.getGame().gameOverState);
			}
		}
	}

	public boolean isJustAte() {
		return justAte;
	}

	public void setJustAte(boolean justAte) {
		this.justAte = justAte;
	}
}
