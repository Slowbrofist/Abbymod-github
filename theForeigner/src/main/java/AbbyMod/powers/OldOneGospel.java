package AbbyMod.powers;

import AbbyMod.AbbyMod;
import AbbyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static AbbyMod.AbbyMod.makePowerPath;

public class OldOneGospel extends AbstractPower {
    public static final String POWER_ID = AbbyMod.makeID("OldOneGospel");
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("OldOneGospel84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("OldOneGospel32.png"));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public OldOneGospel(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = AbstractPower.PowerType.DEBUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        action.exhaustCard = true;
    }
}