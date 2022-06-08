package org.bukkit.craftbukkit.v1_19_R1.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.bukkit.Art;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_19_R1.CraftArt;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Painting;

public class CraftPainting extends CraftHanging implements Painting {

    public CraftPainting(CraftServer server, net.minecraft.world.entity.decoration.Painting entity) {
        super(server, entity);
    }

    @Override
    public Art getArt() {
        Holder<PaintingVariant> art = getHandle().getVariant();
        return CraftArt.NotchToBukkit(art);
    }

    @Override
    public boolean setArt(Art art) {
        return setArt(art, false);
    }

    @Override
    public boolean setArt(Art art, boolean force) {
        net.minecraft.world.entity.decoration.Painting painting = this.getHandle();
        Holder<PaintingVariant> oldArt = painting.getVariant();
        painting.setVariant(CraftArt.BukkitToNotch(art));
        painting.setDirection(painting.getDirection());
        if (!force && !getHandle().generation && !painting.survives()) {
            // Revert painting since it doesn't fit
            painting.setVariant(oldArt);
            painting.setDirection(painting.getDirection());
            return false;
        }
        this.update();
        return true;
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        if (super.setFacingDirection(face, force)) {
            update();
            return true;
        }

        return false;
    }

    @Override
    public net.minecraft.world.entity.decoration.Painting getHandle() {
        return (net.minecraft.world.entity.decoration.Painting) entity;
    }

    @Override
    public String toString() {
        return "CraftPainting{art=" + getArt() + "}";
    }

    @Override
    public EntityType getType() {
        return EntityType.PAINTING;
    }
}
