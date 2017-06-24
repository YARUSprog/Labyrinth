package com.yarus.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import static com.yarus.game.Constants.*;

public class MainScreen implements Screen {

	private int[][] newWalls2 = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0},
			{0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
			{1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
			{1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
			{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0},
			{0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0},
			{0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0},
			{0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0},
			{0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0},
			{0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0},
			{1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0},
			{1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	private OrthographicCamera camera;
	SpriteBatch batch;
	private Texture flowerImg;
	private Texture newWallImg;
	private Texture finishImg;

	private Rectangle flower;
	private Rectangle finish;

	private Vector3 touchPos;
	private ArrayList<Rectangle> walls;
	private Labyrinth game;
	private boolean gameOver;


	public boolean isCollisionwithWalls(Rectangle rec){
		for(Rectangle wall: walls){
			if(rec.overlaps(wall)){
				return true;
			}
		}
		return false;
	}

	public void drawWall(){
		for(Rectangle wall : walls){
			game.batch.draw(newWallImg, wall.x, wall.y);
		}

	}

	public void buildLabyrinth(){
		Rectangle rec;
		for(int i = 0; i < CELLS_COUNT_X; i++){
			for(int j = 0; j < CELLS_COUNT_Y; j++){
				if(newWalls2[i][j] == 0){
					rec = new Rectangle();
					rec.width = CELL_SIZE;
					rec.height = CELL_SIZE;
					rec.x = i*CELL_SIZE;
					rec.y = j*CELL_SIZE;
					walls.add(rec);
				}
			}
		}
		flower.x = CELL_SIZE*6;
		flower.y = 0;
		flower.width = CELL_SIZE;
		flower.height = CELL_SIZE;

		finish.x = CELL_SIZE*20;
		finish.y = 0;
		finish.width = CELL_SIZE;
		finish.height = CELL_SIZE;
	}

	public MainScreen (Labyrinth game) {
		this.game = game;
		walls = new ArrayList<Rectangle>();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		batch = new SpriteBatch();
		flowerImg = new Texture("flower.png");
		newWallImg = new Texture("newWall.png");
		finishImg = new Texture("finish.png");
		gameOver = false;

		flower = new Rectangle();
		finish = new Rectangle();
		touchPos = new Vector3();

		buildLabyrinth();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.5f, 0.2f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		drawWall();
		game.batch.draw(flowerImg, flower.x, flower.y);
		game.batch.draw(finishImg, finish.x, finish.y);
		if(gameOver){
			game.font.draw(game.batch, "YOU WIN", 500, 500);
		}
		if(gameOver && Gdx.input.isTouched()){
			game.setScreen(new MainMenuScreen(game));
		}
		game.batch.end();

		if(Gdx.input.isTouched()){
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			Rectangle tempFlower = new Rectangle(flower);
			if(touchPos.x  - (CELL_SIZE/2)> tempFlower.x)
				tempFlower.x += 10;
			if(touchPos.x  - (CELL_SIZE/2)< tempFlower.x)
				tempFlower.x -= 10;

			if(!isCollisionwithWalls(tempFlower)){
				flower.x = tempFlower.x;
			}
			tempFlower = new Rectangle(flower);
			if(touchPos.y  - (CELL_SIZE/2)> tempFlower.y)
				tempFlower.y += 10;
			if(touchPos.y  - (CELL_SIZE/2)< tempFlower.y)
				tempFlower.y -= 10;

			if(!isCollisionwithWalls(tempFlower)){
				flower.y = tempFlower.y;
			}

			if(tempFlower.overlaps(finish)){
				gameOver = true;
			}
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		flowerImg.dispose();
	}
}
