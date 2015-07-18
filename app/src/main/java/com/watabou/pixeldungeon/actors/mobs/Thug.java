package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Terror;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.CharSprite;
import com.watabou.pixeldungeon.sprites.ThugSprite;
import com.watabou.utils.Random;

/**
 * Created by Alex on 18/07/2015.
 */
public class Thug extends Mob {

    public Item item;

    {
        name = "Deranged Thug";
        spriteClass = ThugSprite.class;

        HP = HT = 12;
        defenseSkill = 4;

        EXP = 2;
        maxLvl = 8;

        loot = Gold.class;
        lootChance = 0.5f;

        FLEEING = new Fleeing();
    }

    private static final String ITEM = "item";

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(2, 5);
    }

    @Override
    public int attackSkill(Char target) {
        return 11;
    }

    @Override
    public int dr() {
        return 2;
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
    }

    @Override
    public int attackProc(Char enemy, int damage) {
        return damage;
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        if (HP < HT/4) {
            state = FLEEING;
        }

        return damage;
    }

    @Override
    public String description() {
        String desc =
                "This is a thug.";

        return desc;
    }

    private class Fleeing extends Mob.Fleeing {
        @Override
        protected void nowhereToRun() {
            if (buff(Terror.class) == null) {
                sprite.showStatus(CharSprite.NEGATIVE, TXT_RAGE);
                state = HUNTING;
            } else {
                super.nowhereToRun();
            }
        }
    }
}
