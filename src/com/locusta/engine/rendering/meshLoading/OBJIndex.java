package com.locusta.engine.rendering.meshLoading;

public class OBJIndex {
	private int m_vertexIndex;
	private int m_texCoordIndex;
	private int m_normalIndex;

	public int getVertexIndex() {
		return m_vertexIndex;
	}

	public int getTexCoordIndex() {
		return m_texCoordIndex;
	}

	public int getNormalIndex() {
		return m_normalIndex;
	}

	public void setVertexIndex(int val) {
		m_vertexIndex = val;
	}

	public void setTexCoordIndex(int val) {
		m_texCoordIndex = val;
	}

	public void setNormalIndex(int val) {
		m_normalIndex = val;
	}

	@Override
	public boolean equals(Object obj) {
		OBJIndex index = (OBJIndex) obj;

		return m_vertexIndex == index.m_vertexIndex && m_texCoordIndex == index.m_texCoordIndex
				&& m_normalIndex == index.m_normalIndex;
	}

	@Override
	public int hashCode() {
		final int BASE = 17;
		final int MULTIPLIER = 31;

		int result = BASE;

		result = MULTIPLIER * result + m_vertexIndex;
		result = MULTIPLIER * result + m_texCoordIndex;
		result = MULTIPLIER * result + m_normalIndex;

		return result;
	}
}
