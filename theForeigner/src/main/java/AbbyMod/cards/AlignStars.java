package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.CosmicEvent;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AlignStars extends AbstractDynamicCard {
    public static final String ID = AbbyMod.makeID(AlignStars.class.getSimpleName());
    public static final String IMG = makeCardPath("AlignStars.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 1;
    private static final int MAGIC = 4;


    public AlignStars() {
        super(
                ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber= this.magicNumber = MAGIC;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CosmicEvent(p, this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new AlignStars();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }
}
