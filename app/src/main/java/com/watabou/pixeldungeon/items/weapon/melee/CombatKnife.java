package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by Alex on 02/08/2015.
 */
public class CombatKnife extends MeleeWeapon
{
    {
        name = "combat knife";
        image = ItemSpriteSheet.COMBATKNIFE;
    }

    public CombatKnife()
    {
        super(1, 1.2f, 1f);
    }

    @Override
    public String desc()
    {
        return "Standard issue military close combat equipment. " +
                "Versatile both as a handheld weapon and a bayonet attachment.";
    }
}
