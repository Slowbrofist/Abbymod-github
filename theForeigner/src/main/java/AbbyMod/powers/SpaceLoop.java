package AbbyMod.powers;

import AbbyMod.AbbyMod;
import AbbyMod.actions.TakeuchiAction;
import AbbyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static AbbyMod.AbbyMod.makePowerPath;

public class SpaceLoop extends AbstractPower implements NonStackablePower {
    public static final String POWER_ID = AbbyMod.makeID("SpaceLoop");
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("SpaceLoop84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("SpaceLoop32.png"));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SpaceLoop(AbstractCreature owner, AbstractCreature source) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = AbstractPower.PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] ;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(owner));
    }
}