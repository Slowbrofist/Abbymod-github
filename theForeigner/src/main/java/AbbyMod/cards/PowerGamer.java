package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.Madness;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PowerGamer extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(PowerGamer.class.getSimpleName());
    public static final String IMG = makeCardPath("PowerGamer.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 1;
    private static final int MAGIC = 12;

    public PowerGamer() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, AbstractDungeon.player.hand.size(), true));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Madness(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new PowerGamer();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(4);
            initializeDescription();
        }
    }
}