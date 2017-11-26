package com.locusta.engine.components;

import com.locusta.engine.core.Vector3f;
import com.locusta.engine.rendering.Attenuation;
import com.locusta.engine.rendering.Shader;

public class SpotLight extends PointLight {
	private float m_cutoff;

	public SpotLight(Vector3f color, float intensity, Attenuation attenuation, float cutoff) {
		super(color, intensity, attenuation);
		this.m_cutoff = cutoff;
		setShader(new Shader("forward-spot"));
	}

	public Vector3f getDirection() {
		return getTransform().getTransformedRot().getForward();
	}

	public float getCutoff() {
		return m_cutoff;
	}

	public void setCutoff(float cutoff) {
		this.m_cutoff = cutoff;
	}
}
