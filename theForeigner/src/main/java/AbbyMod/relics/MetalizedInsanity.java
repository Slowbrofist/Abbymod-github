package AbbyMod.relics;

import AbbyMod.AbbyMod;
import AbbyMod.util.TextureLoader;
import static AbbyMod.AbbyMod.makeRelicOutlinePath;
import static AbbyMod.AbbyMod.makeRelicPath;

import AbbyMod.powers.KeyholeToSomewhere;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MetalizedInsanity extends CustomRelic {
    public static final String ID = AbbyMod.makeID("MetalizedInsanity");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MetalizedInsanity_s.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MetalizedInsanity_s.png"));

    public MetalizedInsanity() {
        super(
                ID,
                IMG,
                OUTLINE,
                RelicTier.UNCOMMON,
                LandingSound.HEAVY
        );
    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hasPower(KeyholeToSomewhere.POWER_ID)) {
            this.flash();
            this.stopPulse();
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(KeyholeToSomewhere.POWER_ID).amount * 2));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }
    public AbstractRelic makeCopy() {
        return new MetalizedInsanity();
    }
}