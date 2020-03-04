package AbbyMod.relics;

import AbbyMod.AbbyMod;
import AbbyMod.util.TextureLoader;
import static AbbyMod.AbbyMod.makeRelicOutlinePath;
import static AbbyMod.AbbyMod.makeRelicPath;
import AbbyMod.powers.KeyholeToSomewhere;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class YogBlessing extends CustomRelic {
    public static final String ID = AbbyMod.makeID("YogBlessing");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("YogBlessing_s.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("YogBlessing_s.png"));

    public YogBlessing() {
        super(
                ID,
                IMG,
                OUTLINE,
                RelicTier.BOSS,
                LandingSound.MAGICAL
        );
    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public void atPreBattle() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KeyholeToSomewhere(AbstractDungeon.player,1),1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player,-1),-1));
    }
    public AbstractRelic makeCopy() {
        return new YogBlessing();
    }
}
