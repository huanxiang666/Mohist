/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.v1_19_R1.block.impl;

import org.bukkit.craftbukkit.v1_19_R1.block.data.CraftBlockData;

public final class CraftMangroveLeaves extends CraftBlockData implements org.bukkit.block.data.type.Leaves {

    public CraftMangroveLeaves() {
        super();
    }

    public CraftMangroveLeaves(net.minecraft.world.level.block.state.BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftLeaves

    private static final net.minecraft.world.level.block.state.properties.IntegerProperty DISTANCE = getInteger(net.minecraft.world.level.block.MangroveLeavesBlock.class, "distance");
    private static final net.minecraft.world.level.block.state.properties.BooleanProperty PERSISTENT = getBoolean(net.minecraft.world.level.block.MangroveLeavesBlock.class, "persistent");

    @Override
    public boolean isPersistent() {
        return get(PERSISTENT);
    }

    @Override
    public void setPersistent(boolean persistent) {
        set(PERSISTENT, persistent);
    }

    @Override
    public int getDistance() {
        return get(DISTANCE);
    }

    @Override
    public void setDistance(int distance) {
        set(DISTANCE, distance);
    }
}