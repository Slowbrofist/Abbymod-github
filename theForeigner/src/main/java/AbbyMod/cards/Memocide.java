package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.actions.MemocideAction;
import AbbyMod.characters.AbbyChar;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class Memocide extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(Memocide.class.getSimpleName());
    public static final String IMG = makeCardPath("Memocide.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 2;

    public Memocide() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 10;
        this.exhaust = true;
        this.isInnate = true;

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ScryAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new MemocideAction());
    }

    public AbstractCard makeCopy() {
        return new Memocide();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}