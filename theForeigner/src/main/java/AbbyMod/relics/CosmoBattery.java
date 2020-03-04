package AbbyMod.relics;

import AbbyMod.AbbyMod;
import AbbyMod.util.TextureLoader;
import static AbbyMod.AbbyMod.makeRelicOutlinePath;
import static AbbyMod.AbbyMod.makeRelicPath;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class CosmoBattery extends CustomRelic {
    public static final String ID = AbbyMod.makeID("CosmoBattery");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("CosmoBattery_s.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("CosmoBattery_s.png"));

    public CosmoBattery() {
        super(
                ID,
                IMG,
                OUTLINE,
                RelicTier.RARE,
                LandingSound.HEAVY
        );
    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new CosmoBattery();
    }
}