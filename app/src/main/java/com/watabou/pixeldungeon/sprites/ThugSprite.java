package com.watabou.pixeldungeon.sprites;

/**
 * Created by Alex on 18/07/2015.
 */

import com.watabou.noosa.TextureFilm;
import com.watabou.pixeldungeon.Assets;

public class ThugSprite extends MobSprite {

    public ThugSprite() {
        super();

        texture(Assets.THUGS);
        TextureFilm film = new TextureFilm(texture, 16, 16);

        idle = new Animation(3, true);
        idle.frames(film, 4, 4, 4, 4, 4, 4, 18, 19, 20, 21, 20, 19, 18, 4);

        run = new Animation(18, false);
        run.frames(film, 5, 6, 7, 8, 9, 10);

        die = new Animation(4, false);
        die.frames(film, 0, 1, 2, 3);

        attack = new Animation(10, false);
        attack.frames(film, 11, 12, 13, 14, 15);

        idle();
    }
}

