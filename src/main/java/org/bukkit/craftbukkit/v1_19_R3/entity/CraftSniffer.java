package org.bukkit.craftbukkit.v1_19_R3.entity;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sniffer;

public class CraftSniffer extends CraftAnimals implements Sniffer {

    public CraftSniffer(CraftServer server, net.minecraft.world.entity.animal.sniffer.Sniffer entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.sniffer.Sniffer getHandle() {
        return (net.minecraft.world.entity.animal.sniffer.Sniffer) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftSniffer";
    }

    @Override
    public EntityType getType() {
        return EntityType.SNIFFER;
    }

    @Override
    public Collection<Location> getExploredLocations() {
        return this.getHandle().getExploredPositions().map(blockPosition -> new Location(this.getLocation().getWorld(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ())).collect(Collectors.toList());
    }

    @Override
    public void removeExploredLocation(Location location) {
        Preconditions.checkArgument(location != null, "location cannot be null");
        if (location.getWorld() != getWorld()) {
            return;
        }

        BlockPos blockPosition = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        this.getHandle().getBrain().setMemory(MemoryModuleType.SNIFFER_EXPLORED_POSITIONS, this.getHandle().getExploredPositions().filter(blockPositionExplored -> !blockPositionExplored.equals(blockPosition)).collect(Collectors.toList()));
    }

    @Override
    public void addExploredLocation(Location location) {
        Preconditions.checkArgument(location != null, "location cannot be null");
        if (location.getWorld() != getWorld()) {
            return;
        }

        this.getHandle().storeExploredPosition(new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    @Override
    public Sniffer.State getState() {
        return this.stateToBukkit(this.getHandle().getState());
    }

    @Override
    public void setState(Sniffer.State state) {
        Preconditions.checkArgument(state != null, "state cannot be null");
        this.getHandle().transitionTo(this.stateToNMS(state));
    }

    @Override
    public Location findPossibleDigLocation() {
        return this.getHandle().calculateDigPosition().map(blockPosition -> new Location(this.getLocation().getWorld(), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ())).orElse(null);
    }

    @Override
    public boolean canDig() {
        return this.getHandle().canDig();
    }

    private net.minecraft.world.entity.animal.sniffer.Sniffer.State stateToNMS(Sniffer.State state) {
        return switch (state) {
            case IDLING -> net.minecraft.world.entity.animal.sniffer.Sniffer.State.IDLING;
            case FEELING_HAPPY -> net.minecraft.world.entity.animal.sniffer.Sniffer.State.FEELING_HAPPY;
            case SCENTING -> net.minecraft.world.entity.animal.sniffer.Sniffer.State.SCENTING;
            case SNIFFING -> net.minecraft.world.entity.animal.sniffer.Sniffer.State.SNIFFING;
            case SEARCHING -> net.minecraft.world.entity.animal.sniffer.Sniffer.State.SEARCHING;
            case DIGGING -> net.minecraft.world.entity.animal.sniffer.Sniffer.State.DIGGING;
            case RISING -> net.minecraft.world.entity.animal.sniffer.Sniffer.State.RISING;
        };
    }

    private Sniffer.State stateToBukkit(net.minecraft.world.entity.animal.sniffer.Sniffer.State state) {
        return switch (state) {
            case IDLING -> Sniffer.State.IDLING;
            case FEELING_HAPPY -> Sniffer.State.FEELING_HAPPY;
            case SCENTING -> Sniffer.State.SCENTING;
            case SNIFFING -> Sniffer.State.SNIFFING;
            case SEARCHING -> Sniffer.State.SEARCHING;
            case DIGGING -> Sniffer.State.DIGGING;
            case RISING -> Sniffer.State.RISING;
        };
    }
}
