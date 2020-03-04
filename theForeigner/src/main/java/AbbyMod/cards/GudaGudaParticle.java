package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.actions.GudaGudaParticleAction;
import AbbyMod.characters.AbbyChar;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class GudaGudaParticle extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(GudaGudaParticle.class.getSimpleName());
    public static final String IMG = makeCardPath("GudaGudaParticle.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;

    public GudaGudaParticle() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        ExhaustiveVariable.setBaseValue(this, 2);

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
        AbstractDungeon.actionManager.addToBottom(new GudaGudaParticleAction(p));
    }

    public AbstractCard makeCopy() {
        return new GudaGudaParticle();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            ExhaustiveVariable.setBaseValue(this, 3);
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}

