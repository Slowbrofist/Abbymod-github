package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class ImpossibleGeometry extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(ImpossibleGeometry.class.getSimpleName());
    public static final String IMG = makeCardPath("ImpossibleGeometry.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 5;
    private static final int MAGIC = 5;

    public ImpossibleGeometry() {
        super(
                ID,
                IMG,
                COST,
                AbstractCard.CardType.POWER,
                COLOR,
                AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardTarget.SELF
        );

        this.magicNumber = this.baseMagicNumber = MAGIC;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new ThornsPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ImpossibleGeometry();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
            initializeDescription();
        }
    }
}