package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.InMyHead;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class VoidWhispers extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(VoidWhispers.class.getSimpleName());
    public static final String IMG = makeCardPath("VoidWhispers.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 4;


    public VoidWhispers() {
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new InMyHead(p,1),1));
    }


    public AbstractCard makeCopy() {
        return new VoidWhispers();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(3);
            initializeDescription();
        }
    }
}
