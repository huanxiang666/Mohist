package org.bukkit.craftbukkit.v1_19_R3.entity;

import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Turtle;

public class CraftTurtle extends CraftAnimals implements Turtle {

    public CraftTurtle(CraftServer server, net.minecraft.world.entity.animal.Turtle entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.Turtle getHandle() {
        return (net.minecraft.world.entity.animal.Turtle) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftTurtle";
    }

    @Override
    public EntityType getType() {
        return EntityType.TURTLE;
    }

    @Override
    public boolean hasEgg() {
        return getHandle().hasEgg();
    }

    @Override
    public boolean isLayingEgg() {
        return getHandle().isLayingEgg();
    }
}