package AbbyMod.powers;

import AbbyMod.AbbyMod;
import AbbyMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;

import static AbbyMod.AbbyMod.makePowerPath;

public class OldLimbPower extends AbstractPower implements CloneablePowerInterface, OnReceivePowerPower {
    public AbstractCreature source;

    public static final String POWER_ID = AbbyMod.makeID("OldLimbPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("OldLimbPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("OldLimbPower32.png"));

    public OldLimbPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


    @Override
    public AbstractPower makeCopy() {
        return new OldLimbPower(owner, source, amount);
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if ((power.ID.equals(WeakPower.POWER_ID) ||
                power.ID.equals(VulnerablePower.POWER_ID)) && owner.hasPower(FrailPower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    this.owner,this.owner,new NextTurnBlockPower(this.owner, this.amount), this.amount));
            return false;
        }
        return true;
    }
}