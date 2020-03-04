package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.FleshFeast;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConsumeFlesh extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(ConsumeFlesh.class.getSimpleName());
    public static final String IMG = makeCardPath("ConsumeFlesh.png");
    public static final AbstractCard.CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 2;
    private static final int MAGIC = 1;

    public ConsumeFlesh() {
        super(
                ID,
                IMG,
                COST,
                AbstractCard.CardType.POWER,
                COLOR,
                AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.SELF
        );

        this.magicNumber = this.baseMagicNumber = MAGIC;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new FleshFeast(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ConsumeFlesh();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}