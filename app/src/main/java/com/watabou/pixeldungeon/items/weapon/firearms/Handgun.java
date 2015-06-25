package com.watabou.pixeldungeon.items.weapon.firearms;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

/**
 * Created by Alex Langdon on 24/06/2015.
 */

public class Handgun extends FirearmWeapon {

    {
        name = "handgun";
        image = ItemSpriteSheet.HANDGUN;
        projectileImage = ItemSpriteSheet.BULLET;

        MIN = 1;
        MAX = 4;
    }

    public Handgun() {
        this(1);
    }

    public Handgun(int number) {
        super();
        quantity = number;
    }

    @Override
    public String desc() {
        return "A reliable, short range, small caliber weapon used by street gangs and private " +
                "security firms alike.";
    }

    @Override
    public Item random() {
        quantity = Random.Int(5, 15);
        return this;
    }

    @Override
    public int price() {
        return quantity * 2;
    }
}


/*
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.ResultDescriptions;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.blobs.Fire;
import com.watabou.pixeldungeon.effects.MagicMissile;
import com.watabou.pixeldungeon.items.wands.Wand;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.pixeldungeon.utils.Utils;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Handgun extends Wand {

    {
        name = "Handgun";
    }

    @Override
    protected void onZap(int cell) {

        Char ch = Actor.findChar(cell);
        if (ch != null) {

            int level = level();

            ch.damage(Random.Int(1, 6 + level * 2), this);
            ch.sprite.burst(0xFF99CCFF, level / 2 + 2);

            if (ch == curUser && !ch.isAlive()) {
                Dungeon.fail(Utils.format(ResultDescriptions.WAND, name, Dungeon.depth));
                GLog.n("You killed yourself with your own Handgun...");
            }
        }
    }

    protected void fx(int cell, Callback callback) {
        MagicMissile.fire(curUser.sprite.parent, curUser.pos, cell, callback);
        Sample.INSTANCE.play(Assets.SND_ZAP);
    }

    @Override
    public String desc() {
        return "A reliable small caliber weapon used by street gangs and private security firms " +
                "alike.";
    }

}
*/