package com.locusta.engine.components;

import com.locusta.engine.core.CoreEngine;
import com.locusta.engine.core.Vector3f;
import com.locusta.engine.rendering.Shader;

public class BaseLight extends GameComponent {
	private Vector3f m_color;
	private float m_intensity;
	private Shader m_shader;

	public BaseLight(Vector3f color, float intensity) {
		this.m_color = color;
		this.m_intensity = intensity;
	}

	@Override
	public void addToEngine(CoreEngine engine) {
		engine.getRenderingEngine().addLight(this);
	}

	public void setShader(Shader shader) {
		this.m_shader = shader;
	}

	public Shader getShader() {
		return m_shader;
	}

	public Vector3f getColor() {
		return m_color;
	}

	public void setColor(Vector3f color) {
		this.m_color = color;
	}

	public float getIntensity() {
		return m_intensity;
	}

	public void setIntensity(float intensity) {
		this.m_intensity = intensity;
	}
}
