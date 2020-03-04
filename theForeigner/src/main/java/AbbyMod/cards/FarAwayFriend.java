package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import AbbyMod.powers.KeyholeToSomewhere;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class FarAwayFriend extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(FarAwayFriend.class.getSimpleName());
    public static final String IMG = makeCardPath("FarAwayFriend.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 0;
    private static final int MAGIC = 2;

    public FarAwayFriend() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );

        this.baseMagicNumber = this.magicNumber = MAGIC;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(KeyholeToSomewhere.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(p,p,p.getPower(KeyholeToSomewhere.POWER_ID).amount));
        }
            AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(p,p,this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new FarAwayFriend();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}