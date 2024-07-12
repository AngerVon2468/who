package wiiu.mavity.who.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

import net.minecraft.world.World;

public interface ITardisComponent extends ComponentV3 {
    int getTardisIds();
    World getWorld();
    void setTardisIds(int value);
}