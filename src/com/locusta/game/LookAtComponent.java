package com.locusta.game;

import com.locusta.engine.components.GameComponent;
import com.locusta.engine.core.*;
import com.locusta.engine.rendering.RenderingEngine;
import com.locusta.engine.rendering.Shader;

public class LookAtComponent extends GameComponent {
	private RenderingEngine m_renderingEngine;

	@Override
	public void update(float delta) {
		if (m_renderingEngine != null) {
			Quaternion newRot = getTransform().getLookAtRotation(
					m_renderingEngine.getMainCamera().getTransform().getTransformedPos(), new Vector3f(0, 1, 0));
			// GetTransform().GetRot().GetUp());

			getTransform().setRot(getTransform().getRot().nLerp(newRot, delta * 1.0f, true));
			// GetTransform().SetRot(GetTransform().GetRot().SLerp(newRot, delta
			// * 5.0f, true));
		}
	}

	@Override
	public void render(Shader shader, RenderingEngine renderingEngine) {
		this.m_renderingEngine = renderingEngine;
	}
}
