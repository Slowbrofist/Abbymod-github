package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

public class LifeSentence extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(LifeSentence.class.getSimpleName());
    public static final String IMG = makeCardPath("LifeSentence.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 6;
    private static final int ATTACK_DMG = 10;
    public LifeSentence() {
        super(ID,
                IMG,
                COST,
                CardType.ATTACK,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY);
        this.isMultiDamage= true;
        this.baseDamage = ATTACK_DMG;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBaseCost(5);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 99, false), 99));
        }
    }

    public AbstractCard makeCopy() {
        return new LifeSentence();
    }
}