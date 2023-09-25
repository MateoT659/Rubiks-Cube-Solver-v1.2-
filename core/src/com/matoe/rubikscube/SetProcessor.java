package com.matoe.rubikscube;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;

public class SetProcessor implements InputProcessor {
    CubeSolver game;
    Vector3 curr;
    float[] disList;
    Sticker[] stickerList;
    public SetProcessor(CubeSolver game){
        this.game = game;
        curr = new Vector3();
    }
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.NUM_1:
                game.settingColor = SColor.RED;
                break;
            case Input.Keys.NUM_2:
                game.settingColor = SColor.ORANGE;
                break;
            case Input.Keys.NUM_3:
                game.settingColor = SColor.YELLOW;
                break;
            case Input.Keys.NUM_4:
                game.settingColor = SColor.GREEN;
                break;
            case Input.Keys.NUM_5:
                game.settingColor = SColor.BLUE;
                break;
            case Input.Keys.NUM_6:
                game.settingColor = SColor.WHITE;
                break;
        }
        return false;
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
        Ray ray = game.camera.getPickRay(screenX, screenY);
        boolean stickerTouched = false;
        disList = new float[10];
        stickerList = new Sticker[10];
        int i = 0;
        BoundingBox bb = new BoundingBox();
        for(Sticker s: game.stickers){
            s.instance.calculateBoundingBox(bb);
            bb.mul(s.instance.transform);
//            intersectRaySphere(ray,s.center,.625f, curr)
            if(Intersector.intersectRayBounds(ray, bb, curr)){
                stickerTouched = true;
                disList[i] = curr.dst2(game.camera.position);
                stickerList[i++] = s;
            }
        }
        if(stickerTouched) {
            int mindex = 0;
            for (int j = 1; j < i; j++) {
                if (disList[j] < disList[mindex]) {
                    mindex = j;
                }
            }
            stickerList[mindex].setColor(game.cube, game.settingColor);
        }
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
