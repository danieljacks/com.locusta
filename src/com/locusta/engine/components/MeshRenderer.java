package com.locusta.engine.components;

import com.locusta.engine.rendering.Material;
import com.locusta.engine.rendering.Mesh;
import com.locusta.engine.rendering.RenderingEngine;
import com.locusta.engine.rendering.Shader;

public class MeshRenderer extends GameComponent {
	private Mesh m_mesh;
	private Material m_material;

	public MeshRenderer(Mesh mesh, Material material) {
		this.m_mesh = mesh;
		this.m_material = material;
	}

	@Override
	public void render(Shader shader, RenderingEngine renderingEngine) {
		shader.bind();
		shader.updateUniforms(getTransform(), m_material, renderingEngine);
		m_mesh.draw();
	}
}
