package com.matoe.rubikscube;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class CubeSolver extends ApplicationAdapter {
	public enum States{
		MOVING,
		FREECAM,
		SETTING,
		SETTING_FC,
		SOLVING,
		SOLVED,
		INVALID,
		PRESOLVED
	}
	States currstate;
	SColor settingColor;
	BitmapFont font;
	ModelBatch batch3D;
	SpriteBatch textBatch;
	Model modelCube;
	ModelInstance instanceCube;
	MoveProcessor moveProcessor;
	SetProcessor setProcessor;
	Model modelUD;
	Model modelRL;
	Model modelFB;
	ArrayList<Move> currentSolution;
	Sticker[] stickers;
	PerspectiveCamera camera;
	Environment environment;
	CameraInputController camController;
	RubiksCube cube;
	@Override
	public void create () {
		currstate = States.MOVING;
		settingColor = SColor.WHITE;
		cube = new RubiksCube();

		font = new BitmapFont();
		font.getData().setScale(2f);

		ScreenUtils.clear(.05f,.1f,.1f,1);
		camera = new PerspectiveCamera(30f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(10f,10f,10f);
		camera.near = 1f;
		camera.far = 300f;
		camera.lookAt(0,0,0);

		camController = new CameraInputController(camera);

		moveProcessor = new MoveProcessor(this);
		Gdx.input.setInputProcessor(moveProcessor);

		setProcessor = new SetProcessor(this);

		ModelBuilder builder = new ModelBuilder();
		modelCube = builder.createBox(4f, 4f, 4f, new Material(ColorAttribute.createDiffuse(.1f,.1f,.1f,1)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		instanceCube = new ModelInstance(modelCube);

		modelUD = builder.createBox(1.15f, .14f, 1.15f, new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		modelFB =  builder.createBox(1.15f, 1.15f, .14f, new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		modelRL =  builder.createBox(.14f, 1.15f, 1.15f, new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

		stickers = new Sticker[54];
		initStickers();

		batch3D = new ModelBatch();
		textBatch = new SpriteBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.createDiffuse(.5f,.5f,.5f,1)));
		environment.add(new DirectionalLight().set(1f,1f,1f,-1f,-.8f,.5f));
		environment.add(new DirectionalLight().set(.8f,.8f,.8f,1,.8f,-.5f));

		updateStickers();
	}
	void initStickers(){
		int[] neg = new int[]{1,1,-1,-1};
		int[] edg = new int[]{0,1,0,-1};
		int i;
		for(i = 0; i<8; i++){
			stickers[i] = new Sticker(new ModelInstance(modelUD), 'c', i, 0);
			if(i<4){
				stickers[i].instance.transform.setToTranslation((float)1.33*neg[(i+3)%4],2,(float)1.33*neg[(i+2)%4]);
			}else{
				stickers[i].instance.transform.setToTranslation((float)1.33*neg[(i+3)%4],-2,(float)1.33*neg[(i)%4]);
			}
		}
		for(; i<20; i++){
			if(i-8>=4 && i-8<=7){
				stickers[i] = new Sticker(new ModelInstance(modelFB), 'e', i-8, 0);
				if(i>13){
					stickers[i].instance.transform.setToTranslation((float)1.33*edg[1+2*((i)%2)],0, -2);
				}else{
					stickers[i].instance.transform.setToTranslation((float)1.33*edg[3-2*((i)%2)],0, 2);
				}
			}else{
				stickers[i] = new Sticker(new ModelInstance(modelUD), 'e', i-8, 0);
				if(i<15){
					stickers[i].instance.transform.setToTranslation((float)1.33*edg[(i)%4],2,(float)1.33*edg[(i+3)%4]);
				}else{
					stickers[i].instance.transform.setToTranslation((float)1.33*edg[(i)%4],-2,(float)1.33*edg[(i+1)%4]);
				}
			}
		}
		stickers[i] = new Sticker(new ModelInstance(modelUD), 'm', 0, 0);
		stickers[i].instance.transform.setToTranslation(0,2,0);

		stickers[++i] = new Sticker(new ModelInstance(modelUD), 'm', 5, 5);
		stickers[i].instance.transform.setToTranslation(0,-2,0);

		stickers[++i] = new Sticker(new ModelInstance(modelFB), 'm', 1, 1);
		stickers[i].instance.transform.setToTranslation(0,0,2);

		stickers[++i] = new Sticker(new ModelInstance(modelFB), 'm', 3, 3);
		stickers[i].instance.transform.setToTranslation(0,0,-2);

		stickers[++i] = new Sticker(new ModelInstance(modelRL), 'm', 2, 2);
		stickers[i].instance.transform.setToTranslation(2,0,0);

		stickers[++i] = new Sticker(new ModelInstance(modelRL), 'm', 4, 4);
		stickers[i].instance.transform.setToTranslation(-2,0,0);
		for(int j = 0; j<8; j++){
			stickers[++i] = new Sticker(new ModelInstance(modelFB), 'c', j, 1+(j+1)%2);
			if(j>=2 && j<=5){
				stickers[i].instance.transform.setToTranslation(1.33f * neg[(3+j%4)%4],1.33f * neg[(2+j%4)%4],2);
			}else{
				stickers[i].instance.transform.setToTranslation(1.33f * neg[(3+j%4)%4],1.33f * neg[j%4],-2);
			}
		}
		for(int j = 0; j<8; j++){
			stickers[++i] = new Sticker(new ModelInstance(modelRL), 'c', j, 1+(j)%2);
			if((j>=1 && j<=2) || (j>=5 && j<=6)){
				stickers[i].instance.transform.setToTranslation(2,1.33f * neg[(j/2)%4],1.33f * neg[(3+(j/2))%4]);
			}else{
				stickers[i].instance.transform.setToTranslation(-2, 1.33f * neg[(j/2)%4],1.33f * neg[(3+(j/2))%4]);
			}
		}
		for(int j = 0; j<12; j++){
			if(j==0 || j==2 || j==8 || j==10){
				stickers[++i] = new Sticker(new ModelInstance(modelFB), 'e', j, 1);
			}else{
				stickers[++i] = new Sticker(new ModelInstance(modelRL), 'e', j, 1);
			}
		}
		int[] order = {42, 44, 50, 52};
		for(int o = 0; o<4; o++){
			stickers[order[o]].instance.transform.setToTranslation(0, 1.33f*neg[o],2*neg[(o+3)%4]);
		}
		order = new int[]{43, 47, 51, 48};
		for(int o = 0; o<4; o++){
			stickers[order[o]].instance.transform.setToTranslation(2, 1.33f * edg[(o+1)%4], 1.33f * edg[o]);
		}
		order = new int[]{45, 46, 53, 49};
		for(int o = 0; o<4; o++){
			stickers[order[o]].instance.transform.setToTranslation(-2, 1.33f * edg[(o+1)%4], 1.33f * edg[o]);
		}
		for(Sticker s:stickers){
			s.updateCenter();
		}
	}
	public void updateStickers(){
		for(Sticker s:stickers){
			s.updateColor(cube);
		}
	}
	public void orientCam(){
		camera.position.set(10f,10f,10f);
		camController.target = new Vector3(0,0,0);
		camera.lookAt(0,0,0);
		camera.up.set(0,Math.abs(camera.up.y), 0);
		camera.normalizeUp();

	}
	@Override
	public void render () {
		camController.update();
		camera.update();
		Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//3d rendering
		batch3D.begin(camera);
		batch3D.render(instanceCube,environment);
		for(Sticker s: stickers){
			batch3D.render(s.instance, environment);
		}
		batch3D.end();

		switch(currstate){
			case MOVING:
				renderMoving();
				break;
			case FREECAM:
				renderFreeCam();
				break;
			case SETTING_FC:
				renderSettingFC();
				break;
			case SETTING:
				renderSetting();
				break;
			case SOLVING:
				renderSolving();
				break;
			case SOLVED:
				renderSolved();
				break;
			case INVALID:
				renderInvalid();
				break;
			case PRESOLVED:
				renderPreSolved();
		}
	}

	void renderMoving(){
		textBatch.begin();
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			font.draw(textBatch, UIText.primeControls, 3, Gdx.graphics.getHeight()-5);
		}else if(Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.CAPS_LOCK)){
			font.draw(textBatch, UIText.doubleControls, 3, Gdx.graphics.getHeight()-5);
		}else{
			font.draw(textBatch, UIText.moveControls, 3, Gdx.graphics.getHeight()-5);
		}
		font.draw(textBatch, UIText.moveModes, 0,110);
		font.draw(textBatch, UIText.moveStates, Gdx.graphics.getWidth()-305, Gdx.graphics.getHeight()-5, 300, 2, false);
		textBatch.end();

		if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
			Gdx.input.setInputProcessor(camController);
			currstate = States.FREECAM;
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
			Gdx.input.setInputProcessor(camController);
			currstate = States.SETTING_FC;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			Gdx.input.setInputProcessor(camController);
			currstate = States.SOLVING;
		}
	}
	public void renderFreeCam(){
		textBatch.begin();
		font.draw(textBatch, UIText.freecamControls, 3, Gdx.graphics.getHeight()-5);
		font.draw(textBatch, UIText.freecamStates, Gdx.graphics.getWidth()-305, Gdx.graphics.getHeight()-5, 300, 2, false);
		textBatch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			orientCam();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.input.setInputProcessor(moveProcessor);
			orientCam();
			currstate = States.MOVING;
		}
	}
	public void renderSettingFC(){
		textBatch.begin();
		font.draw(textBatch, UIText.settingControls, 3, Gdx.graphics.getHeight()-5);
		font.draw(textBatch, UIText.settingStates, Gdx.graphics.getWidth()-305, Gdx.graphics.getHeight()-5, 300, 2, false);
		textBatch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
			settingColor = SColor.RED;
			Gdx.input.setInputProcessor(setProcessor);
			currstate = States.SETTING;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
			settingColor = SColor.ORANGE;
			Gdx.input.setInputProcessor(setProcessor);
			currstate = States.SETTING;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
			settingColor = SColor.YELLOW;
			Gdx.input.setInputProcessor(setProcessor);
			currstate = States.SETTING;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
			settingColor = SColor.GREEN;
			Gdx.input.setInputProcessor(setProcessor);
			currstate = States.SETTING;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)){
			settingColor = SColor.BLUE;
			Gdx.input.setInputProcessor(setProcessor);
			currstate = States.SETTING;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)){
			settingColor = SColor.WHITE;
			Gdx.input.setInputProcessor(setProcessor);
			currstate = States.SETTING;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.input.setInputProcessor(moveProcessor);
			orientCam();
			currstate = States.MOVING;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			orientCam();
		}
	}
	public void renderSetting(){
		textBatch.begin();
		font.draw(textBatch, UIText.settingControls, 3, Gdx.graphics.getHeight()-5);
		font.draw(textBatch, UIText.settingStates, Gdx.graphics.getWidth()-305, Gdx.graphics.getHeight()-5, 300, 2, false);
		font.draw(textBatch, UIText.getSettingMode(settingColor), 3, 156);
		textBatch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
			cube.setTo(new RubiksCube());
			updateStickers();
		}if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
			cube.setToColor(settingColor);
			updateStickers();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
			Gdx.input.setInputProcessor(camController);
			currstate = States.SETTING_FC;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.input.setInputProcessor(moveProcessor);
			orientCam();
			currstate = States.MOVING;
		}
	}
	public void renderSolving(){
		textBatch.begin();
		if(Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
			if (cube.isSolved()) {
				currstate = States.PRESOLVED;

			}else if(!cube.isValid()) {
				currstate = States.INVALID;
			}else{
				font.draw(textBatch, UIText.solvingProcess, Gdx.graphics.getWidth() / 2f - 150, Gdx.graphics.getHeight()-30, 300, 1, false);
				solving = true;
				currstate = States.SOLVED;
			}
			orientCam();

		}else if(Gdx.input.isKeyJustPressed(Input.Keys.N)){
			Gdx.input.setInputProcessor(moveProcessor);
			currstate = States.MOVING;
			orientCam();
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			orientCam();
		}else{
			font.draw(textBatch, UIText.solvingPrompt, camera.viewportWidth/2f-150, Gdx.graphics.getHeight()-5, 300, 1, false);
		}
		textBatch.end();
	}
	public void renderSolved(){
		if(solving) {
			currentSolution = SolveMain.solveCubePreCompute(cube);
			updateStickers();
			solving = false;
		}
		textBatch.begin();
		font.draw(textBatch, UIText.getSolution(currentSolution), camera.viewportWidth/2f-600, Gdx.graphics.getHeight()-5, 1200, 1, true);
		textBatch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			cube.doAlgorithm(currentSolution);
			updateStickers();
			orientCam();
			currstate = States.MOVING;
			Gdx.input.setInputProcessor(moveProcessor);
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			orientCam();
			currstate = States.MOVING;
			Gdx.input.setInputProcessor(moveProcessor);
		}
	}
	boolean solving;
	public void renderInvalid(){
		textBatch.begin();
		font.draw(textBatch, UIText.invalid, camera.viewportWidth/2f-150, Gdx.graphics.getHeight()-5, 300, 1, false);
		textBatch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			orientCam();
			currstate = States.MOVING;
			Gdx.input.setInputProcessor(moveProcessor);
		}
	}
	public void renderPreSolved(){
		textBatch.begin();
		font.draw(textBatch, UIText.presolved, camera.viewportWidth/2f-150, Gdx.graphics.getHeight()-5, 300, 1, false);
		textBatch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			orientCam();
			currstate = States.MOVING;
			Gdx.input.setInputProcessor(moveProcessor);
		}
	}
	@Override
	public void dispose () {
		batch3D.dispose();
		modelCube.dispose();
		modelFB.dispose();
		modelRL.dispose();
		modelUD.dispose();
	}
}
