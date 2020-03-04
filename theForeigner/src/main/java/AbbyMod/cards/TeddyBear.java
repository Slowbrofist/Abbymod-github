package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;

import AbbyMod.powers.Madness;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TeddyBear extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(TeddyBear.class.getSimpleName());
    public static final String IMG = makeCardPath("TeddyBear.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int MAGIC = 10;
    private static final int COST = 2;

    public TeddyBear() {
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
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        p.heal(this.magicNumber);
        if (p.hasPower(Madness.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, Madness.POWER_ID));
        }
    }

    public AbstractCard makeCopy() {
        return new TeddyBear();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            this.isInnate = true;
            initializeDescription();
        }
    }
}