package com.locusta.engine.components;

import com.locusta.engine.core.Vector3f;
import com.locusta.engine.rendering.Shader;

public class DirectionalLight extends BaseLight {
	public DirectionalLight(Vector3f color, float intensity) {
		super(color, intensity);
		setShader(new Shader("forward-directional"));
	}

	public Vector3f getDirection() {
		return getTransform().getTransformedRot().getForward();
	}
}
