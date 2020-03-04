package AbbyMod.powers;

import AbbyMod.AbbyMod;
import AbbyMod.relics.CosmoBattery;
import AbbyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static AbbyMod.AbbyMod.makePowerPath;

public class KeyholeToSomewhere extends AbstractPower{
    public static final String POWER_ID = AbbyMod.makeID("KeyholeToSomewhere");
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("KeyholeToSomewhere84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("KeyholeToSomewhere32.png"));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int ACT_STACK = 3;
    private static final int IMPR_STACK = 4;
    private int stc;
    public KeyholeToSomewhere(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        getLimit();
        if (this.amount > this.stc) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner,(this.amount - this.stc)),(this.amount - this.stc)));
            this.amount = this.stc;
        }
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        getLimit();
        if (this.amount > this.stc) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner,(this.amount - this.stc)),(this.amount - this.stc)));
            this.amount = this.stc;
        }
            updateDescription();
        }
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.stc + DESCRIPTIONS[2]);
    }
    @Override
    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.player.gainEnergy(this.amount);
        }
    private void getLimit() {
        if (AbstractDungeon.player.hasRelic(CosmoBattery.ID)) {
            this.stc = IMPR_STACK;
        } else {
            this.stc = ACT_STACK;
        }
    }
}