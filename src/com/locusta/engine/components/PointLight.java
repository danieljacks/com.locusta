package com.locusta.engine.components;

import com.locusta.engine.core.Vector3f;
import com.locusta.engine.rendering.Attenuation;
import com.locusta.engine.rendering.Shader;

public class PointLight extends BaseLight {
	private static final int COLOR_DEPTH = 256;

	private Attenuation m_attenuation;
	private float m_range;

	public PointLight(Vector3f color, float intensity, Attenuation attenuation) {
		super(color, intensity);
		this.m_attenuation = attenuation;

		float a = attenuation.getExponent();
		float b = attenuation.getLinear();
		float c = attenuation.getConstant() - COLOR_DEPTH * getIntensity() * getColor().max();

		this.m_range = (float) ((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));

		setShader(new Shader("forward-point"));
	}

	public float getRange() {
		return m_range;
	}

	public void setRange(float range) {
		this.m_range = range;
	}

	public Attenuation getAttenuation() {
		return m_attenuation;
	}
}
