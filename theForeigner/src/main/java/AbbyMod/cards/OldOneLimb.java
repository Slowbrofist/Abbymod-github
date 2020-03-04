package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class OldOneLimb extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(OldOneLimb.class.getSimpleName());
    public static final String IMG = makeCardPath("OldOneLimb.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 2;


    public OldOneLimb() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 2;
        this.exhaust=true;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
        AbstractDungeon.player.decreaseMaxHealth(this.magicNumber);
    }

    public AbstractCard makeCopy() {
        return new OldOneLimb();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }
}
