package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import AbbyMod.powers.ChaosEntropy;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class FatalEntropy extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(FatalEntropy.class.getSimpleName());
    public static final String IMG = makeCardPath("FatalEntropy.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 6;

    public FatalEntropy() {
        super(
                ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new ChaosEntropy(p, 2),2));
    }

    public AbstractCard makeCopy() {
        return new FatalEntropy();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(5);
            initializeDescription();
        }
    }
}

