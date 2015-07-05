package com.watabou.pixeldungeon.items.weapon.ranged;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.weapon.Weapon;

/**
 * Created by Alex on 25/06/2015.
 */

public abstract class RangedWeapon extends Weapon
{
    {
        stackable = true;
        levelKnown = true;
    }

    //TODO Strength should only play a limited part in gun wielding
    @Override
    public String info() {

        StringBuilder info = new StringBuilder(desc());

        info.append("\n\nAverage damage of this weapon equals to " + (MIN + (MAX - MIN) / 2) + " points per hit. ");

        if (Dungeon.hero.belongings.backpack.items.contains(this)) {
            if (STR > Dungeon.hero.STR()) {
                info.append(
                        "Because of your inadequate strength the accuracy and speed " +
                                "of your attack with this " + name + " is decreased.");
            }
            if (STR < Dungeon.hero.STR()) {
                info.append(
                        "Because of your excess strength the damage " +
                                "of your attack with this " + name + " is increased.");
            }
        }

        if (isEquipped(Dungeon.hero)) {
            info.append("\n\nYou hold the " + name + " at the ready.");
        }

        return info.toString();
    }

    protected abstract void miss(int cell);
}
