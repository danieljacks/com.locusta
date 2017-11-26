package com.locusta.engine.core;

import com.locusta.engine.components.GameComponent;
import com.locusta.engine.rendering.RenderingEngine;
import com.locusta.engine.rendering.Shader;

import java.util.ArrayList;

public class GameObject {
	private ArrayList<GameObject> m_children;
	private ArrayList<GameComponent> m_components;
	private Transform m_transform;
	private CoreEngine m_engine;

	public GameObject() {
		m_children = new ArrayList<GameObject>();
		m_components = new ArrayList<GameComponent>();
		m_transform = new Transform();
		m_engine = null;
	}

	public GameObject addChild(GameObject child) {
		m_children.add(child);
		child.setEngine(m_engine);
		child.getTransform().setParent(m_transform);
		return this;
	}

	public GameObject addComponent(GameComponent component) {
		m_components.add(component);
		component.setParent(this);

		return this;
	}

	public void inputAll(float delta) {
		input(delta);

		for (GameObject child : m_children)
			child.inputAll(delta);
	}

	public void updateAll(float delta) {
		update(delta);

		for (GameObject child : m_children)
			child.updateAll(delta);
	}

	public void renderAll(Shader shader, RenderingEngine renderingEngine) {
		render(shader, renderingEngine);

		for (GameObject child : m_children)
			child.renderAll(shader, renderingEngine);
	}

	public void input(float delta) {
		m_transform.update();

		for (GameComponent component : m_components)
			component.input(delta);
	}

	public void update(float delta) {
		for (GameComponent component : m_components)
			component.update(delta);
	}

	public void render(Shader shader, RenderingEngine renderingEngine) {
		for (GameComponent component : m_components)
			component.render(shader, renderingEngine);
	}

	public ArrayList<GameObject> getAllAttached() {
		ArrayList<GameObject> result = new ArrayList<GameObject>();

		for (GameObject child : m_children)
			result.addAll(child.getAllAttached());

		result.add(this);
		return result;
	}

	public Transform getTransform() {
		return m_transform;
	}

	public void setEngine(CoreEngine engine) {
		if (this.m_engine != engine) {
			this.m_engine = engine;
			for (GameComponent component : m_components) {
				component.addToEngine(engine);
			}
			for (GameObject child : m_children) {
				child.setEngine(engine);
			}
		}
	}
}
