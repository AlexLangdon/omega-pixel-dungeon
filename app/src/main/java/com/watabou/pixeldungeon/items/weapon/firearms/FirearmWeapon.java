package com.watabou.pixeldungeon.items.weapon.firearms;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.weapon.missiles.MissileWeapon;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.windows.WndOptions;

import java.util.ArrayList;

/**
 * Created by Alex on 25/06/2015.
 */

public class FirearmWeapon extends MissileWeapon {

    private static final String TXT_MISSILES = "Firearm";
    private static final String TXT_YES = "Yes, I know what I'm doing";
    private static final String TXT_NO = "No, I changed my mind";
    private static final String TXT_R_U_SURE = "Do you really want to equip it as a melee weapon?";
    protected int projectileImage = 129;

    {
        stackable = true;
        levelKnown = true;
        defaultAction = Item.AC_FIRE;
    }

    public int getProjectileImage() {
        return projectileImage;
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.add(AC_FIRE);
        return actions;
    }

    //TODO Allow thorwing gun
    @Override
    protected void onThrow(int cell) {
        Char enemy = Actor.findChar(cell);
        if (enemy == null || enemy == Item.curUser || !Item.curUser.throwMissile(enemy, this)) {
            miss(cell);
        }
    }


    protected void miss(int cell) {
        //super.miss(cell);
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
    public boolean doEquip(final Hero hero) {
        GameScene.show(
                new WndOptions(TXT_MISSILES, TXT_R_U_SURE, TXT_YES, TXT_NO) {
                    @Override
                    protected void onSelect(int index) {
                        if (index == 0) {
                            FirearmWeapon.super.doEquip(hero);
                        }
                    }

                    ;
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
}
