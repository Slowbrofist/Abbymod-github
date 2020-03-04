package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.KeyholeToSomewhere;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Ab_SwirlOfMadness extends AbstractDynamicCard {
    public static final String ID = AbbyMod.makeID(Ab_SwirlOfMadness.class.getSimpleName());
    public static final String IMG = makeCardPath("SwirlOfMadness.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 4;
    private static final int ATTACK_DMG = 4;


    public Ab_SwirlOfMadness() {
        super(ID,
                IMG,
                COST,
                CardType.ATTACK,
                COLOR,
                CardRarity.COMMON,
                CardTarget.ALL_ENEMY);
        this.isMultiDamage= true;
        this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (p.hasPower(KeyholeToSomewhere.POWER_ID)) {
            for (int i = 0; i < p.getPower(KeyholeToSomewhere.POWER_ID).amount; i++){
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Ab_SwirlOfMadness();
    }
}
