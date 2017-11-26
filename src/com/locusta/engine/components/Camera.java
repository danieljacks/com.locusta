package com.locusta.engine.components;

import com.locusta.engine.core.*;

public class Camera extends GameComponent {
	private Matrix4f m_projection;

	public Camera(Matrix4f projection) {
		this.m_projection = projection;
	}

	public Matrix4f getViewProjection() {
		Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
		Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);

		Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(),
				cameraPos.getZ());

		return m_projection.mul(cameraRotation.mul(cameraTranslation));
	}

	@Override
	public void addToEngine(CoreEngine engine) {
		engine.getRenderingEngine().addCamera(this);
	}
}
