package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class AttoSecond extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(AttoSecond.class.getSimpleName());
    public static final String IMG = makeCardPath("AttoSecond.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 2;
    private static final int  DMG = 20;



    public AttoSecond() {
        super(
                ID,
                IMG,
                COST,
                CardType.ATTACK,
                COLOR,
                CardRarity.COMMON,
                CardTarget.ENEMY
        );
        this.baseDamage = DMG;
        ExhaustiveVariable.setBaseValue(this, 3);

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.modifyCostForCombat(-1);
    }

    public AbstractCard makeCopy() {
        return new AttoSecond();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            initializeDescription();
        }
    }
}
