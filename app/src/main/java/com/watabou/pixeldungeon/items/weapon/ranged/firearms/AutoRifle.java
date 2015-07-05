package com.watabou.pixeldungeon.items.weapon.ranged.firearms;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

/**
 * Created by Alex on 04/07/2015.
 */
public class AutoRifle extends FirearmWeapon {

    {
        name = "auto-rifle";
        image = ItemSpriteSheet.AUTORIFLE;
        projectileImage = ItemSpriteSheet.BULLET;
        MIN = 1;
        MAX = 4;
        rateOfFire = 3;
        stackable = true;
    }

    public AutoRifle() {
        this(1);
    }

    public AutoRifle(int number) {
        super(number);
    }

    @Override
    public String desc() {
        return "A gas operated, magasine fed, fully automatic rifle. " +
                "Benefits from a higher caliber and accuracy than other, smaller firearms.";
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



