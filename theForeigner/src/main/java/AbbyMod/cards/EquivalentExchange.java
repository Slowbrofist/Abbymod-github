package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class EquivalentExchange extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(EquivalentExchange.class.getSimpleName());
    public static final String IMG = makeCardPath("EquivalentExchange.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 0;

    public EquivalentExchange() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.purgeOnUse = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.drawPile.isEmpty()){
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(p.drawPile.getTopCard(),p.drawPile,true));
            AbstractDungeon.actionManager.addToBottom(new ExhumeAction(false));
        }
    }

    public AbstractCard makeCopy() {
        return new EquivalentExchange();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.purgeOnUse = false;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}