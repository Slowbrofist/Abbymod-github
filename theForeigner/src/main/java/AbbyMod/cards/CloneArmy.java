package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class CloneArmy extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(CloneArmy.class.getSimpleName());
    public static final String IMG = makeCardPath("CloneArmy.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;

    public CloneArmy() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p,2));
        if(upgraded){
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new CloneArmy(),1));
    }else{
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new CloneArmy(),1,true,true));
        }
    }

    public AbstractCard makeCopy() {
        return new CloneArmy();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}