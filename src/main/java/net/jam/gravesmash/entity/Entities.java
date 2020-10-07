package net.jam.gravesmash.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Entities {

    public static final EntityType<JackEntity> JACK = Registry.register(
            Registry.ENTITY_TYPE, new Identifier("gravesmash", "jack"),
            FabricEntityTypeBuilder.<JackEntity>create(SpawnGroup.MISC, JackEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public void register() {

    }

}
