package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.AngelPrayer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PrayerOfFaith extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(PrayerOfFaith.class.getSimpleName());
    public static final String IMG = makeCardPath("PrayerOfFaith.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 6;

    public PrayerOfFaith() {
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new AngelPrayer(p, 1), 1));
    }

    public AbstractCard makeCopy() {
        return new PrayerOfFaith();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(4);
            initializeDescription();
        }
    }
}