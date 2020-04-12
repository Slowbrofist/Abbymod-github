package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import AbbyMod.powers.ChaosEntropy;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static AbbyMod.AbbyMod.makeCardPath;

public class FatalEntropy extends AbstractDynamicCard implements StartupCard {

    public static final String ID = AbbyMod.makeID(FatalEntropy.class.getSimpleName());
    public static final String IMG = makeCardPath("FatalEntropy.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 5;

    public FatalEntropy() {
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p , new DexterityPower(p, 1),1));
    }

    public AbstractCard makeCopy() {
        return new FatalEntropy();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(3);
            initializeDescription();
        }
    }

    @Override
    public boolean atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player , new ChaosEntropy(AbstractDungeon.player, 2),2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player , new DexterityPower(AbstractDungeon.player, -1),-1));
        return true;
    }
}

