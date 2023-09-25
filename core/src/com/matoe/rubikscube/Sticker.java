package com.matoe.rubikscube;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Sticker {
    ModelInstance instance;
    char typeID;
    int pieceID;
    int rotID;
    BoundingBox bb = new BoundingBox();
    Vector3 center = new Vector3();
    float radius;
    public Sticker(ModelInstance instance, char type, int piece, int rot){
        this.instance = instance;
        typeID = type;
        pieceID = piece;
        rotID = rot;
        this.instance.calculateBoundingBox(bb);
        bb.getCenter(center);
        radius = bb.getDimensions(new Vector3()).len()/2f;

    }
    public void updateCenter(){
        center.add(instance.transform.getTranslation(new Vector3()));
    }
    public void setColor(RubiksCube cube, SColor color){
        cube.setColor(color, typeID, pieceID, rotID);
        updateColor(cube);
    }
    public void updateColor(RubiksCube cube){
        switch(cube.getColor(typeID, pieceID, rotID)){
            case WHITE:
                instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.WHITE));
                break;
            case RED:
                instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.FIREBRICK));
                break;
            case GREEN:
                instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.GREEN));
                break;
            case YELLOW:
                instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.GOLD));
                break;
            case ORANGE:
                instance.materials.get(0).set(ColorAttribute.createDiffuse(1f, .4f, 0f, 1));
                break;
            case BLUE:
                instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.BLUE));
                break;
        }
    }
}
