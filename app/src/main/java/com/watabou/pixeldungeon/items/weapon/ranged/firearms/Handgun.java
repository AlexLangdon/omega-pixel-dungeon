package com.watabou.pixeldungeon.items.weapon.ranged.firearms;

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
        rateOfFire = 1;
        MIN = 1;
        MAX = 4;
    }

    public Handgun() {
        this(1);
    }

    public Handgun(int number) {
        super(number);
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