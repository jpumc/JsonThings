package gigaherz.jsonthings.things.parsers;

import com.google.gson.JsonObject;
import gigaherz.jsonthings.things.ThingRegistries;
import gigaherz.jsonthings.things.builders.BlockMaterialBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.material.MaterialColor;

public class BlockMaterialParser extends ThingParser<BlockMaterialBuilder>
{
    public BlockMaterialParser()
    {
        super(GSON, "block_material");
    }

    @Override
    public void finishLoading()
    {
        getBuilders().forEach(thing -> Registry.register(ThingRegistries.BLOCK_MATERIALS, thing.getRegistryName(), thing.build()));
    }

    @Override
    public BlockMaterialBuilder processThing(ResourceLocation key, JsonObject data)
    {
        int color = GsonHelper.getAsInt(data, "color_index");
        if (color < 0 || color >= 64)
        {
            throw new RuntimeException("'color_index' must be between 0 and 63 (both inclusive).");
        }

        BlockMaterialBuilder builder = BlockMaterialBuilder.begin(key, MaterialColor.MATERIAL_COLORS[color]);

        if (GsonHelper.getAsBoolean(data, "liquid", false)) builder = builder.liquid();
        if (GsonHelper.getAsBoolean(data, "flammable", false)) builder = builder.flammable();
        if (GsonHelper.getAsBoolean(data, "replaceable", false)) builder = builder.replaceable();
        if (!GsonHelper.getAsBoolean(data, "solid", true)) builder = builder.nonSolid();
        if (!GsonHelper.getAsBoolean(data, "blocks_motion", true)) builder = builder.noCollider();
        if (!GsonHelper.getAsBoolean(data, "solid_blocking", true)) builder = builder.notSolidBlocking();

        String pushReaction = GsonHelper.getAsString(data, "push_reaction", "normal");
        switch(pushReaction)
        {
            case "block" -> builder = builder.notPushable();
            case "destroy" -> builder = builder.destroyOnPush();
            case "normal" -> {}
            default -> throw new IllegalStateException("'push_reaction' must be one of: block, destroy, normal");
        }

        return builder;
    }
}
