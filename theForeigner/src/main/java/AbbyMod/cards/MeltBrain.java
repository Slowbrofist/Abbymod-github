package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;

import AbbyMod.powers.KeyholeToSomewhere;
import AbbyMod.powers.Madness;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class MeltBrain extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(MeltBrain.class.getSimpleName());
    public static final String IMG = makeCardPath("MeltBrain.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 0;

    public MeltBrain() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.exhaust = true;
        AlwaysRetainField.alwaysRetain.set(this, true);
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p,this.magicNumber));
        if(p.hasPower(WeakPower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p,this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1));
        }
        if(p.hasPower(VulnerablePower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p,this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1));
        }
        if(p.hasPower(FrailPower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p,this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1));
        }
    }

    public AbstractCard makeCopy() {
        return new MeltBrain();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
           }
    }
}