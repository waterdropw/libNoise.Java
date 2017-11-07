package net.xbwee.libnoise.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import net.xbwee.libnoise.example.worm.WormsManager;


public class WormsApp extends ApplicationAdapter {
	Texture texture;
	Camera camera;


	@Override
	public void create () {
		texture = new Texture(Gdx.files.internal("assets/worm.bmp"), true);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Gdx.input.setInputProcessor(new InputHandler());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.15f, 0.3f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update(false);

		Gdx.gl.glDisable (GL20.GL_DEPTH_TEST);
		Gdx.gl.glDisable (GL20.GL_CULL_FACE);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		Gdx.gl.glEnable (GL20.GL_TEXTURE_2D);
		Gdx.gl.glTexParameteri (GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL20.GL_REPEAT);
		Gdx.gl.glTexParameteri (GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_EDGE);
		Gdx.gl.glTexParameteri (GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER,
				GL20.GL_LINEAR_MIPMAP_LINEAR);
		Gdx.gl.glTexParameteri (GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_LINEAR);

		texture.bind();

		WormsManager.newInstance().drawWorms(camera);
		WormsManager.newInstance().updateWorms();

	}
	
	@Override
	public void dispose () {
		if (texture != null)
			texture.dispose();
	}


	static {
		System.loadLibrary("libNoiseBase");
		System.loadLibrary("libNoiseModel");
		System.loadLibrary("libNoiseModule");
		System.loadLibrary("libNoiseUtils");
	}
}
