package AbbyMod.powers;

import AbbyMod.AbbyMod;
import AbbyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static AbbyMod.AbbyMod.makePowerPath;

public class InMyHead extends AbstractPower {
    public static final String POWER_ID = AbbyMod.makeID("InMyHead");
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("InMyHead84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("InMyHead32.png"));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public InMyHead(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        updateDescription();
    }
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount);
    }

    public void atStartOfTurnPostDraw() {
        int MAD = EnergyPanel.totalCount * this.amount;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new Madness(this.owner,MAD),MAD));
    }
}
