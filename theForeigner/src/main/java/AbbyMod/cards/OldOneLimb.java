package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;

import AbbyMod.powers.OldLimbPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OldOneLimb extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(OldOneLimb.class.getSimpleName());
    public static final String IMG = makeCardPath("OldOneLimb.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 3;
    private static final int MAGIC = 3;


    public OldOneLimb() {
        super(
                ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OldLimbPower(p, p, this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new OldOneLimb();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}
