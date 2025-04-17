package com.keremyurekli.happyghastbuilding.weirdstuff;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class BoxWithData extends Box {
    public BoxWithData(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(x1, y1, z1, x2, y2, z2);
    }
    /**
     * Creates a box of the given positions as corners.
     */
    public BoxWithData(Vec3d pos1, Vec3d pos2) {
        this(pos1.x, pos1.y, pos1.z, pos2.x, pos2.y, pos2.z);
    }


    public UUID ghastUUID;
    public OnClick action;
}
