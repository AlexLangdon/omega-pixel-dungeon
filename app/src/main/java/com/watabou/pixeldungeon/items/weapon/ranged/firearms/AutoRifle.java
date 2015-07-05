package com.watabou.pixeldungeon.items.weapon.ranged.firearms;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

/**
 * Created by Alex on 04/07/2015.
 */
public class AutoRifle extends FirearmWeapon {

    {
        name = "autorifle";
        image = ItemSpriteSheet.AUTORIFLE;
        projectileImage = ItemSpriteSheet.BULLET;
        MIN = 1;
        MAX = 4;
        rateOfFire = 3;
    }

    public AutoRifle() {
        this(1);
    }

    public AutoRifle(int number) {
        super(number);
    }

    @Override
    public String desc() {
        return "A gas operated, magasine fed, fully automatic rifle." +
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

/*
    @Override
    public void castShooter(final Hero user, int dst) {

        final float finalDelay = TIME_TO_THROW;

        final int cell = Ballistica.cast(user.pos, dst, false, true);
        user.sprite.zap(cell);
        user.busy();

        Sample.INSTANCE.play(Assets.SND_MISS, 0.6f, 0.6f, 1.5f);

        Char enemy = Actor.findChar(cell);
        QuickSlot.target(this, enemy);

//        final Callback temp2 = new Callback() {
//            @Override
//            public void call() {
//
//                FirearmWeapon detachWeap =
//                        (FirearmWeapon) AutoRifle.this.detach(user.belongings.backpack);
//                detachWeap.onShoot(cell);
//
//                ((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
//                        reset(user.pos, cell, projectileImage, glowing(), new Callback() {
//                            @Override
//                            public void call() {
//
//                                FirearmWeapon detachWeap =
//                                        (FirearmWeapon) AutoRifle.this.detach(user.belongings.backpack);
//                                detachWeap.onShoot(cell);
//                                user.spendAndNext(finalDelay);
//
//                            }
//                        });
//            }
//        };
//
//
//        Callback temp = new Callback() {
//            @Override
//            public void call() {
//
//                FirearmWeapon detachWeap =
//                        (FirearmWeapon) AutoRifle.this.detach(user.belongings.backpack);
//                detachWeap.onShoot(cell);
//
//                ((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
//                        reset(user.pos, cell, projectileImage, glowing(), temp2);
//
//            }
//        };

       Callback thing = new Callback() {
           @Override
           public void call() {

               if(j < 3) {
                   j++;
                   FirearmWeapon detachWeap =
                           (FirearmWeapon) AutoRifle.this.detach(user.belongings.backpack);
                   detachWeap.onShoot(cell);

                   ((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
                           reset(user.pos, cell, projectileImage, glowing(), this);
               }
               else {
                   user.spendAndNext(finalDelay);
               }
           }
       };


        ((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
                reset(user.pos, cell, projectileImage, glowing(),thing);
    }*/

}



