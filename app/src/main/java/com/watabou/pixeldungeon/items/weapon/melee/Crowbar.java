package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by Alex on 02/08/2015.
 */
public class Crowbar extends MeleeWeapon
{
    {
        name = "crowbar";
        image = ItemSpriteSheet.CROWBAR;
    }

    public Crowbar()
    {
        super(1, 1.2f, 1f);
    }

    @Override
    public String desc()
    {
        return "A simple cast iron bar with a curved fork and wedge at either end.";
    }
}
