package com.watabou.pixeldungeon.items.weapon.missiles;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.utils.Random;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by Alex Langdon on 24/06/2015.
 */

public class Handgun extends MissileWeapon {

    {
        name = "Handgun";
        image = ItemSpriteSheet.DART;

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
        return
                "These simple metal spikes are weighted to fly true and " +
                        "sting their prey with a flick of the wrist.";
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
