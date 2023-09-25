package com.matoe.rubikscube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

class MoveProcessor implements InputProcessor {
    public CubeSolver game;
    public MoveProcessor(CubeSolver game){
        this.game = game;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            primeMoves(keycode);
        } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.CAPS_LOCK)){
            doubleMoves(keycode);
        }else{
            normalMoves(keycode);
        }
        game.updateStickers();
        return false;
    }
    void normalMoves(int keycode){
        switch(keycode){
            case Input.Keys.Q:
                game.cube.move(Move.F);
                break;
            case Input.Keys.W:
                game.cube.move(Move.R);
                break;
            case Input.Keys.E:
                game.cube.move(Move.U);
                break;
            case Input.Keys.A:
                game.cube.move(Move.B);
                break;
            case Input.Keys.S:
                game.cube.move(Move.L);
                break;
            case Input.Keys.D:
                game.cube.move(Move.D);
                break;
            case Input.Keys.Z:
                game.cube.move(Move.S);
                break;
            case Input.Keys.X:
                game.cube.move(Move.M);
                break;
            case Input.Keys.C:
                game.cube.move(Move.E);
                break;
            case Input.Keys.UP:
                game.cube.move(Move.x);
                break;
            case Input.Keys.DOWN:
                game.cube.move(Move.xPRIME);
                break;
            case Input.Keys.LEFT:
                game.cube.move(Move.y);
                break;
            case Input.Keys.RIGHT:
                game.cube.move(Move.yPRIME);
                break;
            case Input.Keys.COMMA:
                game.cube.move(Move.zPRIME);
                break;
            case Input.Keys.PERIOD:
                game.cube.move(Move.z);
                break;
        }
    }
    void primeMoves(int keycode){
        switch(keycode) {
            case Input.Keys.Q:
                game.cube.move(Move.FPRIME);
                break;
            case Input.Keys.W:
                game.cube.move(Move.RPRIME);
                break;
            case Input.Keys.E:
                game.cube.move(Move.UPRIME);
                break;
            case Input.Keys.A:
                game.cube.move(Move.BPRIME);
                break;
            case Input.Keys.S:
                game.cube.move(Move.LPRIME);
                break;
            case Input.Keys.D:
                game.cube.move(Move.DPRIME);
                break;
            case Input.Keys.Z:
                game.cube.move(Move.SPRIME);
                break;
            case Input.Keys.X:
                game.cube.move(Move.MPRIME);
                break;
            case Input.Keys.C:
                game.cube.move(Move.EPRIME);
                break;
            case Input.Keys.UP:
                game.cube.move(Move.x);
                break;
            case Input.Keys.DOWN:
                game.cube.move(Move.xPRIME);
                break;
            case Input.Keys.LEFT:
                game.cube.move(Move.y);
                break;
            case Input.Keys.RIGHT:
                game.cube.move(Move.yPRIME);
                break;
            case Input.Keys.COMMA:
                game.cube.move(Move.zPRIME);
                break;
            case Input.Keys.PERIOD:
                game.cube.move(Move.z);
                break;
        }
    }
    void doubleMoves(int keycode){
        switch(keycode) {
            case Input.Keys.Q:
                game.cube.move(Move.F2);
                break;
            case Input.Keys.W:
                game.cube.move(Move.R2);
                break;
            case Input.Keys.E:
                game.cube.move(Move.U2);
                break;
            case Input.Keys.A:
                game.cube.move(Move.B2);
                break;
            case Input.Keys.S:
                game.cube.move(Move.L2);
                break;
            case Input.Keys.D:
                game.cube.move(Move.D2);
                break;
            case Input.Keys.Z:
                game.cube.move(Move.S2);
                break;
            case Input.Keys.X:
                game.cube.move(Move.M2);
                break;
            case Input.Keys.C:
                game.cube.move(Move.E2);
                break;
            case Input.Keys.UP:
                game.cube.move(Move.x);
                break;
            case Input.Keys.DOWN:
                game.cube.move(Move.xPRIME);
                break;
            case Input.Keys.LEFT:
                game.cube.move(Move.y);
                break;
            case Input.Keys.RIGHT:
                game.cube.move(Move.yPRIME);
                break;
            case Input.Keys.COMMA:
                game.cube.move(Move.zPRIME);
                break;
            case Input.Keys.PERIOD:
                game.cube.move(Move.z);
                break;
        }
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
