package AbbyMod.powers;
import static AbbyMod.AbbyMod.makePowerPath;

import AbbyMod.AbbyMod;
import AbbyMod.cards.Abby_NP;
import AbbyMod.relics.YogBlessing;
import AbbyMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Madness extends AbstractPower{
    public static final String POWER_ID = AbbyMod.makeID("Ab_Madness");
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Ab_Madness84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Ab_Madness32.png"));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int ACT_STACK = 10;
    private static final int IMPR_STACK = 8;
    private int stc;

    public Madness(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        getDivider();
        updateDescription();
        while(this.amount >= this.stc){
            flash();
            this.amount-=this.stc;
            AbstractCard abbynp = new Abby_NP();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(abbynp, 1, false));
        }
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    @Override
    public void stackPower(int stackAmount) {
            this.fontScale = 8.0F;
            this.amount += stackAmount;
            getDivider();
            while(this.amount >= this.stc){
                flash();
                this.amount-=this.stc;
                AbstractCard abbynp = new Abby_NP();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(abbynp, 1, false));
        }
            this.updateDescription();
    }

    public void updateDescription() {
            this.description = (DESCRIPTIONS[0] + this.stc + DESCRIPTIONS[1]);
    }
    private void getDivider() {
        if (AbstractDungeon.player.hasRelic(YogBlessing.ID)) {
            this.stc = IMPR_STACK;
        } else {
            this.stc = ACT_STACK;
        }
    }
}
