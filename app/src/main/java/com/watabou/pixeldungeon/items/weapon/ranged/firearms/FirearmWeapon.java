package com.watabou.pixeldungeon.items.weapon.ranged.firearms;

import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.bags.Bag;
import com.watabou.pixeldungeon.items.weapon.ranged.RangedWeapon;
import com.watabou.pixeldungeon.mechanics.Ballistica;
import com.watabou.pixeldungeon.scenes.CellSelector;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.sprites.MissileSprite;
import com.watabou.pixeldungeon.ui.QuickSlot;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Callback;

import java.util.ArrayList;

/**
 * Created by Alex on 25/06/2015.
 */

public class FirearmWeapon extends RangedWeapon {

    private static final String TXT_NO_AMMO = "the firearm does not fire;" +
            " it must have run out of ammunition";

    protected int projectileImage = 129;
    protected int rateOfFire = 1;
    private int shotsFired = 0;

    public FirearmWeapon(int number) {
            quantity = number;
        defaultAction = Item.AC_SHOOT;
    }

    public void doShoot(Hero hero) {
        
        if (quantity <= 0) {
            GLog.w(TXT_NO_AMMO);
            return;
        }

        GameScene.selectCell(shooter);
    }

    @Override
    public void execute(Hero hero, String action) {

        curUser = hero;
        curItem = this;

        if (action.equals(AC_SHOOT) && isEquipped(hero)) {
            doShoot(hero);
        } else {
            super.execute(hero, action);
        }
    }

    @Override
    public Item detach(Bag container) {

        if (quantity <= 0) {
            return null;
        } else {
            quantity--;
            updateQuickslot();
            try {
                Item detached = getClass().newInstance();
                detached.onDetach();
                return detached;
            } catch (Exception e) {
                return null;
            }
        }

    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        if (isEquipped(hero)) {
            actions.add(AC_SHOOT);
        }
        return actions;
    }

    public void onShoot(int cell) {

        Char enemy = Actor.findChar(cell);
        if (enemy == null || enemy == Item.curUser || !Item.curUser.fireFirearm(enemy, this)) {
            miss(cell);
        }
    }

    @Override
    protected void miss(int cell) {
        //Do nothing
    }

    @Override
    public void cast(final Hero user, int dst) {

        if (isEquipped(user) && !this.doUnequip(user, false, false)) {
            return;
        }

        final int cell = Ballistica.cast(user.pos, dst, false, true);
        user.sprite.zap(cell);
        user.busy();

        Sample.INSTANCE.play(Assets.SND_MISS, 0.6f, 0.6f, 1.5f);

        Char enemy = Actor.findChar(cell);
        QuickSlot.target(this, enemy);

        final float finalDelay = TIME_TO_THROW;

        /*
        // FIXME!!!
        float delay = TIME_TO_THROW;
        if (this instanceof ThrowingWeapon) {
            delay *= ((ThrowingWeapon) this).speedFactor(user);
            if (enemy != null) {
                SnipersMark mark = user.buff(SnipersMark.class);
                if (mark != null) {
                    if (mark.object == enemy.id()) {
                        delay *= 0.5f;
                    }
                    user.remove(mark);
                }
            }
        }
        final float finalDelay = delay;
        */

        ((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
                reset(user.pos, cell, this, new Callback() {
                    @Override
                    public void call() {
                        FirearmWeapon.this.detachAll(user.belongings.backpack).onThrow(cell);
                        user.spendAndNext(finalDelay);
                    }
                });
    }

    public void castShooter(final Hero user, int dst) {

        final int cell = Ballistica.cast(user.pos, dst, false, true);
        user.sprite.zap(cell);
        user.busy();

        Sample.INSTANCE.play(Assets.SND_MISS, 0.6f, 0.6f, 1.5f);

        Char enemy = Actor.findChar(cell);
        QuickSlot.target(this, enemy);

        final float finalDelay = TIME_TO_THROW;

        /*
        // FIXME!!!
        if (this instanceof ThrowingWeapon) {
            delay *= ((ThrowingWeapon) this).speedFactor(user);
            if (enemy != null) {
                SnipersMark mark = user.buff(SnipersMark.class);
                if (mark != null) {
                    if (mark.object == enemy.id()) {
                        delay *= 0.5f;
                    }
                    user.remove(mark);
                }
            }
        }
        final float finalDelay = delay;
        */

        shotsFired = 0;

        fireShots(user,cell,finalDelay);
    }

    private void fireShots(final Hero user,final int cell,final float finalDelay) {

        if(quantity <= 0) {
            user.spendAndNext(finalDelay);
            return;
        }

        FirearmWeapon detachWeap =
                (FirearmWeapon) FirearmWeapon.this.detach(user.belongings.backpack);
        shotsFired++;

        detachWeap.onShoot(cell);

        ((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
                reset(user.pos, cell, projectileImage, glowing(), new Callback() {
                    @Override
                    public void call() {

                        if (shotsFired < rateOfFire) {
                            fireShots(user,cell,finalDelay);
                        } else {
                            user.spendAndNext(finalDelay);
                        }
                    }

                });

    }

    protected CellSelector.Listener shooter = new CellSelector.Listener() {
        @Override
        public void onSelect(Integer target) {
            if (target != null) {
                castShooter(curUser, target);
            }
        }

        @Override
        public String prompt() {
            return "Choose direction of shot.";
        }
    };
}