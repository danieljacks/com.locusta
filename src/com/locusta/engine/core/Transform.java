package com.locusta.engine.core;

public class Transform {
	private Transform m_parent;
	private Matrix4f m_parentMatrix;

	private Vector3f m_pos;
	private Quaternion m_rot;
	private Vector3f m_scale;

	private Vector3f m_oldPos;
	private Quaternion m_oldRot;
	private Vector3f m_oldScale;

	public Transform() {
		m_pos = new Vector3f(0, 0, 0);
		m_rot = new Quaternion(0, 0, 0, 1);
		m_scale = new Vector3f(1, 1, 1);

		m_parentMatrix = new Matrix4f().initIdentity();
	}

	public void update() {
		if (m_oldPos != null) {
			m_oldPos.set(m_pos);
			m_oldRot.set(m_rot);
			m_oldScale.set(m_scale);
		} else {
			m_oldPos = new Vector3f(0, 0, 0).set(m_pos).add(1.0f);
			m_oldRot = new Quaternion(0, 0, 0, 0).set(m_rot).mul(0.5f);
			m_oldScale = new Vector3f(0, 0, 0).set(m_scale).add(1.0f);
		}
	}

	public void rotate(Vector3f axis, float angle) {
		m_rot = new Quaternion(axis, angle).mul(m_rot).normalized();
	}

	public void lookAt(Vector3f point, Vector3f up) {
		m_rot = getLookAtRotation(point, up);
	}

	public Quaternion getLookAtRotation(Vector3f point, Vector3f up) {
		return new Quaternion(new Matrix4f().initRotation(point.sub(m_pos).normalized(), up));
	}

	public boolean hasChanged() {
		if (m_parent != null && m_parent.hasChanged())
			return true;

		if (!m_pos.equals(m_oldPos))
			return true;

		if (!m_rot.equals(m_oldRot))
			return true;

		if (!m_scale.equals(m_oldScale))
			return true;

		return false;
	}

	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().initTranslation(m_pos.getX(), m_pos.getY(), m_pos.getZ());
		Matrix4f rotationMatrix = m_rot.toRotationMatrix();
		Matrix4f scaleMatrix = new Matrix4f().initScale(m_scale.getX(), m_scale.getY(), m_scale.getZ());

		return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
	}

	private Matrix4f getParentMatrix() {
		if (m_parent != null && m_parent.hasChanged())
			m_parentMatrix = m_parent.getTransformation();

		return m_parentMatrix;
	}

	public void setParent(Transform parent) {
		this.m_parent = parent;
	}

	public Vector3f getTransformedPos() {
		return getParentMatrix().transform(m_pos);
	}

	public Quaternion getTransformedRot() {
		Quaternion parentRotation = new Quaternion(0, 0, 0, 1);

		if (m_parent != null)
			parentRotation = m_parent.getTransformedRot();

		return parentRotation.mul(m_rot);
	}

	public Vector3f getPos() {
		return m_pos;
	}

	public void setPos(Vector3f pos) {
		this.m_pos = pos;
	}

	public Quaternion getRot() {
		return m_rot;
	}

	public void setRot(Quaternion rotation) {
		this.m_rot = rotation;
	}

	public Vector3f getScale() {
		return m_scale;
	}

	public void setScale(Vector3f scale) {
		this.m_scale = scale;
	}
}
