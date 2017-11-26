package com.locusta.game;

import com.locusta.engine.core.CoreEngine;

public class Main {
	public static void main(String[] args) {
		CoreEngine engine = new CoreEngine(800, 600, 6000, new TestGame());
		engine.createWindow("Locusta");
		engine.start();
	}
}
