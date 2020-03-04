package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;

import AbbyMod.powers.KeyholeToSomewhere;
import AbbyMod.powers.Madness;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class Ygna extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(Ygna.class.getSimpleName());
    public static final String IMG = makeCardPath("Ygna.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 5;
    private static final int BLOCK = 10;

    public Ygna() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );

        this.baseBlock = BLOCK;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p , this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new DexterityPower(p, 2), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new Madness(p, 3), 3));
        if (p.hasPower(KeyholeToSomewhere.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, KeyholeToSomewhere.POWER_ID));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(p.getPower(KeyholeToSomewhere.POWER_ID).amount));
        }
    }

    public AbstractCard makeCopy() {
        return new Ygna();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(4);
            initializeDescription();
        }
    }
}