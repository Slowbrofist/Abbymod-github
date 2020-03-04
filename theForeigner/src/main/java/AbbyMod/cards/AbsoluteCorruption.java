package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawPower;

public class AbsoluteCorruption extends AbstractDynamicCard {
    public static final String ID = AbbyMod.makeID(AbsoluteCorruption.class.getSimpleName());
    public static final String IMG = makeCardPath("AbsoluteCorruption.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 4;


    public AbsoluteCorruption() {
        super(
                ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawPower(p,1),1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ConfusionPower(p)));
    }


    public AbstractCard makeCopy() {
        return new AbsoluteCorruption();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(3);
            initializeDescription();
        }
    }
}