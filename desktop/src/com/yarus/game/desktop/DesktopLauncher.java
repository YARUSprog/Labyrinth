package com.yarus.game.desktop;

		import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
		import com.yarus.game.Labyrinth;
		import static com.yarus.game.Constants.SCREEN_WIDTH;
		import static com.yarus.game.Constants.SCREEN_HEIGHT;
		import static com.yarus.game.Constants.SCREEN_NAME;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = SCREEN_NAME;
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;
		new LwjglApplication(new Labyrinth(), config);
	}
}
