package com.locusta.engine.core;

import com.locusta.engine.rendering.RenderingEngine;
import com.locusta.engine.rendering.Window;

public class CoreEngine {
	private boolean m_isRunning;
	private Game m_game;
	private RenderingEngine m_renderingEngine;
	private int m_width;
	private int m_height;
	private double m_frameTime;

	public CoreEngine(int width, int height, double framerate, Game game) {
		this.m_isRunning = false;
		this.m_game = game;
		this.m_width = width;
		this.m_height = height;
		this.m_frameTime = 1.0 / framerate;
		game.SetEngine(this);
	}

	public void createWindow(String title) {
		Window.createWindow(m_width, m_height, title);
		this.m_renderingEngine = new RenderingEngine();
	}

	public void start() {
		if (m_isRunning)
			return;
		run();
	}

	public void stop() {
		if (!m_isRunning)
			return;
		m_isRunning = false;
	}

	private void run() {
		m_isRunning = true;

		int frames = 0;
		double frameCounter = 0;

		m_game.init();

		double lastTime = Time.GetTime();
		double unprocessedTime = 0;

		while (m_isRunning) {
			boolean render = false;
			double startTime = Time.GetTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;
			unprocessedTime += passedTime;
			frameCounter += passedTime;

			while (unprocessedTime > m_frameTime) {
				render = true;

				unprocessedTime -= m_frameTime;

				if (Window.IsCloseRequested())
					stop();

				m_game.input((float) m_frameTime);
				Input.update();

				m_game.update((float) m_frameTime);

				if (frameCounter >= 1.0) {
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				m_game.render(m_renderingEngine);
				Window.render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		cleanUp();
	}

	private void cleanUp() {
		Window.dispose();
	}

	public RenderingEngine getRenderingEngine() {
		return m_renderingEngine;
	}
}
