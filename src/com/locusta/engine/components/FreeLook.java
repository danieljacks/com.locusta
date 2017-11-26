package com.locusta.engine.components;

import com.locusta.engine.core.Input;
import com.locusta.engine.core.Vector2f;
import com.locusta.engine.core.Vector3f;
import com.locusta.engine.rendering.Window;

public class FreeLook extends GameComponent {
	private static final Vector3f Y_AXIS = new Vector3f(0, 1, 0);

	private boolean m_mouseLocked = false;
	private float m_sensitivity;
	private int m_unlockMouseKey;

	public FreeLook(float sensitivity) {
		this(sensitivity, Input.KEY_ESCAPE);
	}

	public FreeLook(float sensitivity, int unlockMouseKey) {
		this.m_sensitivity = sensitivity;
		this.m_unlockMouseKey = unlockMouseKey;
	}

	@Override
	public void input(float delta) {
		Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

		if (Input.getKey(m_unlockMouseKey)) {
			Input.setCursor(true);
			m_mouseLocked = false;
		}
		if (Input.getMouseDown(0)) {
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			m_mouseLocked = true;
		}

		if (m_mouseLocked) {
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);

			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;

			if (rotY)
				getTransform().rotate(Y_AXIS, (float) Math.toRadians(deltaPos.getX() * m_sensitivity));
			if (rotX)
				getTransform().rotate(getTransform().getRot().getRight(),
						(float) Math.toRadians(-deltaPos.getY() * m_sensitivity));

			if (rotY || rotX)
				Input.setMousePosition(centerPosition);
		}
	}
}
