package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;

import AbbyMod.powers.SpaceLoop;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WormHole extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(WormHole.class.getSimpleName());
    public static final String IMG = makeCardPath("WormHole.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 5;

    public WormHole() {
        super(
                ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new SpaceLoop(p, p)));
    }

    public AbstractCard makeCopy() {
        return new WormHole();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(4);
            initializeDescription();
        }
    }
}
