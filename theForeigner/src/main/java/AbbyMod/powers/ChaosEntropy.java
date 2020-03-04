package AbbyMod.powers;

import AbbyMod.AbbyMod;
import AbbyMod.actions.SanityCheckAction;
import AbbyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static AbbyMod.AbbyMod.makePowerPath;

public class ChaosEntropy extends AbstractPower implements NonStackablePower{
    public static final String POWER_ID = AbbyMod.makeID("ChaosEntropy");
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("ChaosEntropy84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("ChaosEntropy32.png"));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ChaosEntropy(AbstractCreature owner, int amt) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amt;
        this.type = AbstractPower.PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.updateDescription();
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + "." ;
    }

    public void atStartOfTurn() {
        if(AbstractDungeon.player.drawPile.size() == 0)
            AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
        AbstractDungeon.actionManager.addToBottom(new SanityCheckAction(AbstractDungeon.player, this.amount));
    }
}
