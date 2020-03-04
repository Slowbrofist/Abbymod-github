package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class HolidayWishes extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(HolidayWishes.class.getSimpleName());
    public static final String IMG = makeCardPath("HolidayWishes.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 1;
    public HolidayWishes() {
        super(ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.COMMON,
                CardTarget.SELF);
        GraveField.grave.set(this, true);
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));

    }

    public AbstractCard makeCopy() {
        return new HolidayWishes();
    }
}