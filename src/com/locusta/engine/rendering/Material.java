package com.locusta.engine.rendering;

import com.locusta.engine.rendering.resourceManagement.MappedValues;

import java.util.HashMap;

public class Material extends MappedValues {
	private HashMap<String, Texture> m_textureHashMap;

	public Material(Texture diffuse, float specularIntensity, float specularPower, Texture normal, Texture dispMap,
			float dispMapScale, float dispMapOffset) {
		super();
		m_textureHashMap = new HashMap<String, Texture>();
		addTexture("diffuse", diffuse);
		addFloat("specularIntensity", specularIntensity);
		addFloat("specularPower", specularPower);
		addTexture("normalMap", normal);
		addTexture("dispMap", dispMap);

		float baseBias = dispMapScale / 2.0f;
		addFloat("dispMapScale", dispMapScale);
		addFloat("dispMapBias", -baseBias + baseBias * dispMapOffset);
	}

	public void addTexture(String name, Texture texture) {
		m_textureHashMap.put(name, texture);
	}

	public Texture getTexture(String name) {
		Texture result = m_textureHashMap.get(name);
		if (result != null) {
			return result;
		}
		return new Texture("test.png");
	}
}
