package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.actions.SanityCheckAction;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SanityCheck extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(SanityCheck.class.getSimpleName());
    public static final String IMG = makeCardPath("SanityCheck.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 2;

    public SanityCheck() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
        AbstractDungeon.actionManager.addToBottom(new SanityCheckAction(p,2));
    }

    public AbstractCard makeCopy() {
        return new SanityCheck();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}