package dev.gigaherz.jsonthings.things;

import dev.gigaherz.jsonthings.things.events.IEventRunner;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;

import javax.annotation.Nullable;
import java.util.List;

public interface IFlexItem extends IEventRunner
{
    default Item self()
    {
        return (Item) this;
    }

    void setUseAction(UseAnim useAction);

    UseAnim getUseAction();

    void setUseTime(int useTicks);

    int getUseTime();

    void setUseFinishMode(CompletionMode onComplete);

    CompletionMode getUseFinishMode();

    void addCreativeStack(StackContext stack, Iterable<CreativeModeTab> tabs);

    void addAttributeModifier(@Nullable EquipmentSlot slot, Attribute attribute, AttributeModifier modifier);

    void setLore(List<MutableComponent> lore);
}
