package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by Alex on 02/08/2015.
 */
public class AugFist extends MeleeWeapon
{
    {
        name = "Aug-Fist";
        image = ItemSpriteSheet.AUGFIST;
    }

    public AugFist()
    {
        super(1, 1.2f, 1f);
    }

    @Override
    public String desc()
    {
        return "Far more than an armoured glove. " +
                "The Augmented Fist or Aug-Fist generator amplifies even the smallest jab into a " +
                "pulverising deathblow. " +
                "However, the sheer size and weight of the glove make it slow and unwieldy for any " +
                "natural human.";
    }
}
