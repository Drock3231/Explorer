package com.detell.explorer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.detell.explorer.Screens.ScreenGame;

public class ExplorerGame extends Game {
	
	@Override
	public void create () {
		setScreen(new ScreenGame(this));
	}
}
