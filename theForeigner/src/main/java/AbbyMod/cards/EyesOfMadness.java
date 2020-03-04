package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EyesOfMadness extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(EyesOfMadness.class.getSimpleName());
    public static final String IMG = makeCardPath("EyesOfMadness.png");
    public static final String SFWIMG = makeCardPath("SFWEyesOfMadness.png");
    public static final AbstractCard.CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int MAGIC = 2;
    private static final int COST = 0;

    public EyesOfMadness() {
        super(
                ID,
                (AbbyMod.enableSFW ? SFWIMG : IMG),
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.exhaust=true;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new EyesOfMadness();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            AlwaysRetainField.alwaysRetain.set(this, true);
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}