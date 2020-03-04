package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.Madness;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class VoidHeretic extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(VoidHeretic.class.getSimpleName());
    public static final String IMG = makeCardPath("VoidHeretic.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 3;


    public VoidHeretic() {
        super(
                ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 10;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new Madness(p, 8), 8));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new VulnerablePower(p, 12, false), 12));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new FrailPower(p, 12,false), 12));
    }


    public AbstractCard makeCopy() {
        return new VoidHeretic();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
            initializeDescription();
        }
    }
}
