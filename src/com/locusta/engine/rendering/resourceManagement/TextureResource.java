package com.locusta.engine.rendering.resourceManagement;

import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;

public class TextureResource {
	private int m_id;
	private int m_refCount;

	public TextureResource() {
		this.m_id = glGenTextures();
		this.m_refCount = 1;
	}

	@Override
	protected void finalize() {
		glDeleteBuffers(m_id);
	}

	public void addReference() {
		m_refCount++;
	}

	public boolean removeReference() {
		m_refCount--;
		return m_refCount == 0;
	}

	public int getId() {
		return m_id;
	}
}
