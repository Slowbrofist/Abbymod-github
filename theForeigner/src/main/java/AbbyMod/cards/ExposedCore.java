package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

import static AbbyMod.AbbyMod.makeCardPath;

public class ExposedCore extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(ExposedCore.class.getSimpleName());
    public static final String IMG = makeCardPath("ExposedCore.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 0;
    private static final int MAGIC = 2;

    public ExposedCore() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,3));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new FrailPower(p,this.magicNumber,false),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ExposedCore();
    }
}
