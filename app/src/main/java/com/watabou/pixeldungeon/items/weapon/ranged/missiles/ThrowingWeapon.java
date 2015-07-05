/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.items.weapon.ranged.missiles;

import java.util.ArrayList;

import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.hero.HeroClass;
import com.watabou.pixeldungeon.items.EquipableItem;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.weapon.ranged.RangedWeapon;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.windows.WndOptions;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;

public class ThrowingWeapon extends RangedWeapon {

    protected static final String TXT_MISSILES = "Missile Weapon";
    protected static final String TXT_YES = "Yes, I know what I'm doing";
    protected static final String TXT_NO = "No, I changed my mind";
    protected static final String TXT_R_U_SURE = "Do you really want to equip it as a melee weapon?";

    {
        defaultAction = Item.AC_THROW;
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        if (hero.heroClass != HeroClass.HUNTRESS && hero.heroClass != HeroClass.ROGUE) {
            actions.remove(EquipableItem.AC_EQUIP);
            actions.remove(EquipableItem.AC_UNEQUIP);
        }
        return actions;
    }

    @Override
    public void onThrow(int cell) {
        Char enemy = Actor.findChar(cell);
        if (enemy == null || enemy == Item.curUser) {
            super.onThrow(cell);
        } else {
            if (!Item.curUser.throwMissile(enemy, this)) {
                miss(cell);
            }
        }
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {

        super.proc(attacker, defender, damage);

        Hero hero = (Hero) attacker;
        if (hero.rangedWeapon == null && stackable) {
            if (quantity == 1) {
                doUnequip(hero, false, false);
            } else {
                detach(null);
            }
        }
    }

    @Override
    protected void miss(int cell) {
        super.onThrow(cell);
    }

    @Override
    public boolean doEquip(final Hero hero) {
        GameScene.show(
                new WndOptions(TXT_MISSILES, TXT_R_U_SURE, TXT_YES, TXT_NO) {
                    @Override
                    protected void onSelect(int index) {
                        if (index == 0) {
                            ThrowingWeapon.super.doEquip(hero);
                        }
                    }
                }
        );

        return false;
    }

    @Override
    public Item random() {
        return this;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }
}
