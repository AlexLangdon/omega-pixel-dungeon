package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by Alex on 02/08/2015.
 */
public class EnergyBlade extends MeleeWeapon
{
    {
        name = "energy blade";
        image = ItemSpriteSheet.ENERGYBLADE;
    }

    public EnergyBlade()
    {
        super(1, 1.2f, 1f);
    }

    @Override
    public String desc()
    {
        return "Resembling a medieval sword, this weapon generates superheated plasma from a " +
                "seemingly impossible power source. " +
                "Few have seen the energy blade, let alone used it. ";
    }
}