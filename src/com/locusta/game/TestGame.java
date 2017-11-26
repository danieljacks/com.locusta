package com.locusta.game;

import com.locusta.engine.components.*;
import com.locusta.engine.core.*;
import com.locusta.engine.rendering.*;

public class TestGame extends Game {
	public void init() {
		// meshes
		Mesh mesh_plane = new Mesh("plane3.obj");
		Mesh mesh_monkey = new Mesh("monkey3.obj");
		// materials
		Material mat_bricks_white = new Material(new Texture("bricks.jpg"), 1, 8, new Texture("bricks_normal.jpg"),
				new Texture("bricks_disp.png"), 0.03f, -0.5f);

		Material mat_bricks_red = new Material(new Texture("bricks2.jpg"), 1, 8, new Texture("bricks2_normal.png"),
				new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);
		// components
		GameComponent planeComponent = new MeshRenderer(mesh_plane, mat_bricks_white);
		GameComponent directionalLightComponent = new DirectionalLight(new Vector3f(1, 1, 1), 0.4f);
		GameComponent greenPointLightComponent = new PointLight(new Vector3f(0, 1, 0), 0.4f, new Attenuation(0, 0, 1));
		GameComponent spotLightComponent = new SpotLight(new Vector3f(0, 1, 1), 0.4f, new Attenuation(0, 0, 0.1f),
				0.7f);
		GameComponent cameraFollowerComponent = new LookAtComponent();
		GameComponent redMonkeyComponent = new MeshRenderer(mesh_monkey, mat_bricks_red);
		GameComponent freeLookComponent = new FreeLook(0.5f);
		GameComponent freeMoveComponent = new FreeMove(10.0f);
		GameComponent cameraComponent = new Camera(new Matrix4f().initPerspective((float) Math.toRadians(70.0f),
				(float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f));
		GameComponent whiteMonkeyComponent = new MeshRenderer(mesh_monkey, mat_bricks_white);
		// objects
		GameObject planeObject = new GameObject().addComponent(planeComponent);
		GameObject directionalLightObject = new GameObject().addComponent(directionalLightComponent);
		GameObject pointLightObject = new GameObject().addComponent(greenPointLightComponent);
		GameObject spotLightObject = new GameObject().addComponent(spotLightComponent);
		GameObject staticMonkeyObject = new GameObject().addComponent(whiteMonkeyComponent);
		GameObject monkeyFollowerObject = new GameObject().addComponent(cameraFollowerComponent)
				.addComponent(redMonkeyComponent);
		GameObject freeCameraObject = new GameObject().addComponent(freeLookComponent).addComponent(freeMoveComponent)
				.addComponent(cameraComponent);
		// initial setting
		planeObject.getTransform().getPos().set(0, -1, 5);
		planeObject.getTransform().getScale().set(100, 100, 100);
		spotLightObject.getTransform().getPos().set(5, 0, 5);
		spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90.0f)));
		monkeyFollowerObject.getTransform().getPos().set(5, 5, 5);
		monkeyFollowerObject.getTransform()
				.setRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-70.0f)));
		directionalLightComponent.getTransform()
				.setRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(-45)));
		// add objects to scene
		addObject(planeObject);
		addObject(directionalLightObject);
		addObject(pointLightObject);
		addObject(spotLightObject);
		addObject(monkeyFollowerObject);
		addObject(freeCameraObject);
		addObject(staticMonkeyObject);
	}
}
