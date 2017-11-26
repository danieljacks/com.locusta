package com.locusta.engine.components;

import com.locusta.engine.core.CoreEngine;
import com.locusta.engine.core.GameObject;
import com.locusta.engine.rendering.RenderingEngine;
import com.locusta.engine.core.Transform;
import com.locusta.engine.rendering.Shader;

public abstract class GameComponent {
	private GameObject m_parent;

	public void input(float delta) {
	}

	public void update(float delta) {
	}

	public void render(Shader shader, RenderingEngine renderingEngine) {
	}

	public void setParent(GameObject parent) {
		this.m_parent = parent;
	}

	public Transform getTransform() {
		return m_parent.getTransform();
	}

	public void addToEngine(CoreEngine engine) {
	}
}
