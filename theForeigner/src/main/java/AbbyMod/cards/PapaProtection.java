package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.Madness;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PapaProtection extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(PapaProtection.class.getSimpleName());
    public static final String IMG = makeCardPath("PapaProtection.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 2;
    private static final int MAGIC = 5;
    private static final int BLOCK = 10;

    public PapaProtection() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );

        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.baseBlock = BLOCK;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new Madness(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p , this.block));
    }

    public AbstractCard makeCopy() {
        return new PapaProtection();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeBlock(4);
            initializeDescription();
        }
    }
}